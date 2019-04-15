package com.meowster.psim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

class GameControls extends JPanel {
    private static final int CTRL_W = 200;
    private static final int CTRL_H = 500;
    private static final Dimension CTRL_DIM = new Dimension(CTRL_W, CTRL_H);

    private static final int BTN_W = 90;
    private static final int BTN_H = 30;
    private static final Dimension BTN_DIM = new Dimension(BTN_W, BTN_H);

    private static final int LABEL_SIZE = BTN_W;
    private static final int LABEL_BORDER = 3;

    private static final Font BTN_FONT = new Font("helvetica", Font.PLAIN, 10);
    private static final String BTN_RESET = "RESET";

    private final List<Particle> availableParticles;
    private final Particle emptyParticle;
    private final ButtonListener btnListener = new ButtonListener();
    private final GameCanvas canvas;
    private JLabel toolLabel;

    GameControls(GameCanvas canvas) {
        this.canvas = canvas;
        availableParticles = canvas.availableParticles();
        emptyParticle = findParticleByName("Empty");

        setPreferredSize(CTRL_DIM);
        setMinimumSize(CTRL_DIM);
        add(createControls());
        setBackground(Parameters.CONTROLS_BG_COLOR);
    }

    private Box createControls() {
        Box box = Box.createVerticalBox();
        addResetButton(box);
        for (Particle p: availableParticles) {
            addAButton(box, p);
        }
        addALabel(box);
        return box;
    }

    private void addResetButton(Box box) {
        JButton reset = new JButton(BTN_RESET);
        reset.setFont(BTN_FONT);
        reset.setPreferredSize(BTN_DIM);
        reset.setMinimumSize(BTN_DIM);
        reset.setMaximumSize(BTN_DIM);
        reset.addActionListener(btnListener);
        box.add(reset);
        box.add(Box.createVerticalStrut(6));
    }

    private void addAButton(Box box, Particle p) {
        box.add(Box.createVerticalStrut(6));
        box.add(createButton(p));
    }

    private JButton createButton(Particle p) {
        Icon icon = new ColorIcon(p.color());
        JButton btn = new JButton(p.name(), icon);
        btn.setFont(BTN_FONT);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setPreferredSize(BTN_DIM);
        btn.setMinimumSize(BTN_DIM);
        btn.setMaximumSize(BTN_DIM);
        btn.addActionListener(btnListener);
        return btn;
    }

    private void addALabel(Box box) {
        Particle p = availableParticles.get(0);
        Color c = p.color();
        ColorIcon icon = new ColorIcon(p.color(), LABEL_SIZE, LABEL_BORDER);

        box.add(Box.createVerticalStrut(40));
        toolLabel = new JLabel(icon);
        box.add(toolLabel);
    }

    private Icon makeLabelIcon(Particle p) {
        return new ColorIcon(p.color(), LABEL_SIZE, LABEL_BORDER);
    }

    private Particle findParticleByName(String name) {
        for (Particle p: availableParticles) {
            if (name.equals(p.name())) {
                return p;
            }
        }
        return null;
    }

    // inner class
    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Particle p = emptyParticle;
            String cmd = event.getActionCommand();
//            System.out.println("Button: " + cmd);

            if (BTN_RESET.equals(cmd)) {
                canvas.reset();
            } else {
                p = findParticleByName(cmd);
            }
            toolLabel.setIcon(makeLabelIcon(p));
            canvas.setTool(p.type());
        }
    }
}