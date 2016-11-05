package org.TobiaszRumian.jp.laboratorium3;
/*
 * @version 1.0
 * Autor: Pawel Rogalinski
 * Data: 1 pazdziernika 2016 r.
 */
import java.awt.*;
import java.io.Serializable;
import java.util.Random;

abstract class Figure implements Serializable {

    static Random random = new Random();

    private boolean selected = false;
    Color color = new Color(random.nextInt());

    boolean isSelected() {
        return selected;
    }

    void select() {
        selected = true;
    }

    void select(boolean z) {
        selected = z;
    }

    void unselect() {
        selected = false;
    }

    void setColor(Graphics g) {
        if (selected) g.setColor(Color.RED);
        else g.setColor(Color.BLACK);
    }

    public abstract boolean isInside(float px, float py);

    boolean isInside(int px, int py) {
        return isInside((float) px, (float) py);
    }

    abstract String getName();

    abstract float getX();

    abstract float getY();

    abstract float computeArea();

    abstract float computePerimeter();

    abstract void move(float dx, float dy);

    abstract void scale(float s);

    abstract void setsColor(Color color);

    abstract void draw(Graphics g);

    @Override
    public String toString() {
        return getName();
    }

}
