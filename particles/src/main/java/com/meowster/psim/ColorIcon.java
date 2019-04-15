package com.meowster.psim;

import javax.swing.*;
import java.awt.*;

public class ColorIcon implements Icon {

    static final int DEFAULT_SIZE = 16;
    static final int DEFAULT_BORDER = 1;

    private Color myColor;
    private int mySize;
    private int myBorder;

    ColorIcon(Color color) {
        this(color, DEFAULT_SIZE, DEFAULT_BORDER);
    }

    ColorIcon(Color color, int size, int border) {
        myColor = color;
        mySize = size;
        myBorder = border;
    }

    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.setColor(Color.black);
        g.fillRect(x, y, mySize, mySize);
        g.setColor(myColor);
        g.fillRect(x + myBorder, y + myBorder,
                mySize - 2 * myBorder, mySize - 2 * myBorder);
    }

    public int getIconWidth() {
        return mySize;
    }

    public int getIconHeight() {
        return mySize;
    }
}
