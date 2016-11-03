package org.TobiaszRumian.jp.laboratorium3;

import java.awt.*;

/**
 * Created by Tobiasz Rumian on 28.10.2016.
 */
public class Circle extends Point {
    private float r;

    Circle() {
        super();
        r = random.nextFloat() * 100;
    }

    Circle(float px, float py, float pr) {
        super(px, py);
        r = pr;
    }

    @Override
    public boolean isInside(float px, float py) {
        return (Math.sqrt((x - px) * (x - px) + (y - py) * (y - py)) <= r);
    }

    @Override
    String getName() {
        return "Circle(" + x + ", " + y + ")";
    }

    @Override
    float computeArea() {
        return (float) Math.PI * r * r;
    }

    @Override
    float computePerimeter() {
        return (float) Math.PI * r * 2;
    }

    @Override
    void scale(float s) {
        r *= s;
    }

    @Override
    void draw(Graphics g) {
        g.setColor(color);
        g.drawOval((int) (x - r), (int) (y - r), (int) (2 * r), (int) (2 * r));
        g.fillOval((int) (x - r), (int) (y - r), (int) (2 * r), (int) (2 * r));
    }

    @Override
    public void setsColor(Color color) {
        this.color = color;
    }

}