package com.meowster.psim;

import javax.swing.*;
import java.awt.*;

public class ColorIcon implements Icon {

    private static final int DEFAULT_SIZE = 16;
    private static final int DEFAULT_BORDER = 1;

    private final Color myColor;
    private final int mySize;
    private final int myBorder;

    ColorIcon(Color color) {
        this(color, DEFAULT_SIZE, DEFAULT_BORDER);
    }

    ColorIcon(Color color, int size, int border) {
        myColor = color;
        mySize = size;
        myBorder = border;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.setColor(Color.black);
        g.fillRect(x, y, mySize, mySize);
        g.setColor(myColor);
        g.fillRect(x + myBorder, y + myBorder,
                mySize - 2 * myBorder, mySize - 2 * myBorder);
    }

    @Override
    public int getIconWidth() {
        return mySize;
    }

    @Override
    public int getIconHeight() {
        return mySize;
    }
}
