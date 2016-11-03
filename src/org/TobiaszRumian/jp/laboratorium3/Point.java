package org.TobiaszRumian.jp.laboratorium3;

import java.awt.*;

/**
 * Created by Tobiasz Rumian on 28.10.2016.
 */
public class Point extends Figure {

    float x, y;

    Point() {
        this.x = random.nextFloat() * 400;
        this.y = random.nextFloat() * 400;
    }

    Point(float x, float y) {
        this.x = x; this.y = y;
    }

    @Override
    public boolean isInside(float px, float py) {
        return (Math.sqrt((x - px) * (x - px) + (y - py) * (y - py)) <= 6);
    }

    @Override
    String getName() {
        return "Point(" + x + ", " + y + ")";
    }

    @Override
    float getX() {
        return x;
    }

    @Override
    float getY() {
        return y;
    }

    @Override
    float computeArea() {
        return 0;
    }

    @Override
    float computePerimeter() {
        return 0;
    }

    @Override
    void move(float dx, float dy) {
        x += dx; y += dy;
    }

    @Override
    void scale(float s) {}

    @Override
    void draw(Graphics g) {
        setColor(g);
        g.fillOval((int) (x - 3), (int) (y - 3), 6, 6);
    }

    @Override
    public void setsColor(Color color) {
        this.color = color;
    }


    String toStringXY() {
        return "(" + x + " , " + y + ")";
    }

}
