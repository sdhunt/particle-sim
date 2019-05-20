package com.meowster.psim;

import java.awt.*;

abstract class ColoredParticle extends AbstractParticle {
    private final ColorScale colorScale;
    private ProbDistrib pdistrib;

    private Clock shimmerClock = null;
    private Color currentColor;

    ColoredParticle(Color c1, Color c0, int nColors, int firstN) {
        colorScale = new ColorScale(c1, c0, nColors);
        currentColor = colorScale.fromTop(firstN);
        pdistrib = ProbDistrib.equalDistrib(nColors);
    }

//    void setDistrib(ProbDistrib distrib) {
//        this.pdistrib = distrib;
//    }

    void shimmerOn(int cycleLength) {
        shimmerClock = new Clock(1, cycleLength).loop();
        setColor(nextColor());
    }

    void setColor(Color c) {
        currentColor = c;
    }

    void setColorByIndex(int i) {
        currentColor = colorScale.get(i);
    }

    // allow subclasses to switch color scales if they wish
    public ColorScale colorScale() {
        return colorScale;
    }

    private Color nextColor() {
        return colorScale().get(pdistrib.nextIndex());
    }

    @Override
    public Color color() {
        return currentColor;
    }

    @Override
    public void tick() {
        if (shimmerClock != null && shimmerClock.countdown()) {
            currentColor = nextColor();
        }
    }
}
