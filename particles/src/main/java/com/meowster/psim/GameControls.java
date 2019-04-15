package com.meowster.psim;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GameControls extends JPanel {
    static final int CTRL_W = 200;
    static final int CTRL_H = 500;
    static final Dimension CTRL_DIM = new Dimension(CTRL_W, CTRL_H);

    static final int BTN_W = 100;
    static final int BTN_H = 40;
    static final Dimension BTN_DIM = new Dimension(BTN_W, BTN_H);

    static final int LABEL_W = 100;
    static final int LABEL_H = 100;
    static final Dimension LABEL_DIM = new Dimension(LABEL_W, LABEL_H);

    static final int LABEL_SIZE = BTN_W;
    static final int LABEL_BORDER = 3;

    static final Font BTN_FONT = new Font("Monospaced", Font.PLAIN, 14);

    List<Particle> availableParticles;
    ButtonListener btnListener = new ButtonListener();
    GameCanvas canvas;
    JLabel toolLabel;

    GameControls(GameCanvas canvas) {
        this.canvas = canvas;
        availableParticles = canvas.availableParticles();

        setPreferredSize(CTRL_DIM);
        setMinimumSize(CTRL_DIM);
        add(createControls());
        setBackground(Parameters.CONTROLS_BG_COLOR);
    }

    Box createControls() {
        Box box = Box.createVerticalBox();
        for (Particle p: availableParticles) {
            addAButton(box, p);
        }
        addALabel(box);
        return box;
    }

    void addAButton(Box box, Particle p) {
        box.add(Box.createVerticalStrut(10));
        box.add(createButton(p));
    }

    JButton createButton(Particle p) {
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

    void addALabel(Box box) {
        Particle p = availableParticles.get(0);
        Color c = p.color();
        ColorIcon icon = new ColorIcon(p.color(), LABEL_SIZE, LABEL_BORDER);

        box.add(Box.createVerticalStrut(40));
        toolLabel = new JLabel(icon);
        box.add(toolLabel);
    }

    Icon makeLabelIcon(Particle p) {
        return new ColorIcon(p.color(), LABEL_SIZE, LABEL_BORDER);
    }

    Particle findParticleByName(String name) {
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
            String cmd = event.getActionCommand();
            Particle p = findParticleByName(cmd);
            toolLabel.setIcon(makeLabelIcon(p));
            canvas.setTool(p.type());
        }
    }
}