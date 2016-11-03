package org.TobiaszRumian.jp.laboratorium3;

import java.awt.*;

/**
 * Created by Tobiasz Rumian on 28.10.2016.
 */
public class Hourglass extends Figure {
    private Point[] points = new Point[5];
    private Triangle[] triangles = new Triangle[2];

    Hourglass() {
        points[0] = new Point(0f, 0f);
        points[1] = new Point(6f, 0f);
        points[2] = new Point(3f, 5f);
        points[3] = new Point(0f, 10f);
        points[4] = new Point(6f, 10f);
        createHourglass();
        this.scale(Math.abs(random.nextFloat() * 10));
        this.move(Math.abs(random.nextFloat() * 400), Math.abs(random.nextFloat() * 400));
    }

    Hourglass(Point p1, Point p2, Point p3, Point p4, Point p5) {

        points[0] = p1;
        points[1] = p2;
        points[2] = p3;
        points[3] = p4;
        points[4] = p5;
        createHourglass();
    }

    private void createHourglass() {
        triangles[0] = new Triangle(points[0], points[2], points[1]);
        triangles[1] = new Triangle(points[3], points[2], points[4]);
    }

    @Override
    public boolean isInside(float px, float py) {
        for (Triangle t : triangles) if (t.isInside(px, py)) return true;
        return false;
    }

    @Override
    String getName() {
        StringBuilder sb = new StringBuilder();
        for (Point p : points) sb.append(p.toStringXY());
        return "Hourglass{" + sb.toString() + "}";
    }

    @Override
    float getX() {
        return points[2].x;
    }

    @Override
    float getY() {
        return points[2].y;
    }

    @Override
    float computeArea() {
        float x = 0;
        for (Triangle t : triangles) x += t.computeArea();
        return x;
    }

    @Override
    float computePerimeter() {
        float x = 0;
        for (Triangle t : triangles) x += t.computePerimeter();
        return x;
    }

    @Override
    void move(float dx, float dy) {
        for (Point p : points) p.move(dx, dy);
    }

    @Override
    void scale(float s) {
        Point sr1 = new Point(points[2].x, points[2].y);
        for (Point p : points) {
            p.x *= s;
            p.y *= s;
        }
        Point sr2 = new Point(points[2].x, points[2].y);
        for (Point p : points) p.move(sr1.x - sr2.x, sr1.y - sr2.y);
    }

    @Override
    void draw(Graphics g) {
        for (Triangle t : triangles) {
            t.setsColor(color);
            t.draw(g);
        }
    }

    @Override
    public void setsColor(Color color) {
        this.color = color;
    }

}
