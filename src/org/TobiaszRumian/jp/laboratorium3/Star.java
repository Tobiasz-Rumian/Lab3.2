package org.TobiaszRumian.jp.laboratorium3;

import java.awt.*;

/**
 * Created by Tobiasz Rumian on 28.10.2016.
 */
public class Star extends Figure {
    private Point[] points = new Point[10];
    private Triangle[] triangles = new Triangle[5];
    private Pentagon pentagon;

    Star() {
        points[0] = new Point(17.5f, 0f);
        points[1] = new Point(21.9f, 12.24f);
        points[2] = new Point(35.28f, 12.24f);
        points[3] = new Point(24.84f, 20.38f);
        points[4] = new Point(28.53f, 32.98f);
        points[5] = new Point(17.5f, 25.55f);
        points[6] = new Point(6.4f, 32.98f);
        points[7] = new Point(10.06f, 20.42f);
        points[8] = new Point(0f, 12.2f);
        points[9] = new Point(13f, 12.24f);
        createStar();
        this.scale(Math.abs(random.nextFloat() * 10));
        this.move(Math.abs(random.nextFloat() * 400), Math.abs(random.nextFloat() * 400));
    }


    Star(Point p1, Point p2, Point p3, Point p4, Point p5, Point p6, Point p7, Point p8, Point p9, Point p10) {
        points[0] = p1;
        points[1] = p2;
        points[2] = p3;
        points[3] = p4;
        points[4] = p5;
        points[5] = p6;
        points[6] = p7;
        points[7] = p8;
        points[8] = p9;
        points[9] = p10;
        createStar();
    }

    @Override
    public boolean isInside(float px, float py) {
        for (Triangle t : triangles) if (t.isInside(px, py)) return true;
        return pentagon.isInside(px, py);
    }

    @Override
    String getName() {
        return "Star{" + points[0].toStringXY() + points[2].toStringXY() + points[4].toStringXY() + points[6].toStringXY() + points[8].toStringXY() + "}";
    }

    @Override
    float getX() {
        return pentagon.getX();
    }

    @Override
    float getY() {
        return pentagon.getX();
    }

    @Override
    float computeArea() {
        float x = 0;
        for (Triangle t : triangles) x += t.computeArea();
        x += pentagon.computeArea();
        return x;
    }

    @Override
    float computePerimeter() {
        float x = 0;
        for (Triangle t : triangles) x += t.computePerimeter();
        x += pentagon.computePerimeter();
        return x;
    }

    @Override
    void move(float dx, float dy) {
        points[0].move(dx, dy);
        points[2].move(dx, dy);
        points[4].move(dx, dy);
        points[6].move(dx, dy);
        points[8].move(dx, dy);
        pentagon.move(dx, dy);
    }

    @Override
    void scale(float s) {
        Point sr1 = new Point(pentagon.getX(), pentagon.getY());
        for (Point p : points) {
            p.x *= s;
            p.y *= s;
        }
        pentagon.getCenterPoint().x *= s;
        pentagon.getCenterPoint().y *= s;
        Point sr2 = new Point(pentagon.getX(), pentagon.getY());
        for (Point p : points) p.move(sr1.x - sr2.x, sr1.y - sr2.y);
        pentagon.getCenterPoint().move(sr1.x - sr2.x, sr1.y - sr2.y);
    }

    @Override
    void draw(Graphics g) {
        for (Triangle t : triangles) {
            t.setsColor(color);
            t.draw(g);
        }
        pentagon.setsColor(color);
        pentagon.draw(g);
    }

    private void createStar() {
        triangles[0] = new Triangle(points[9], points[0], points[1]);
        triangles[1] = new Triangle(points[1], points[2], points[3]);
        triangles[2] = new Triangle(points[3], points[4], points[5]);
        triangles[3] = new Triangle(points[5], points[6], points[7]);
        triangles[4] = new Triangle(points[7], points[8], points[9]);
        pentagon = new Pentagon(points[9], points[1], points[3], points[5], points[7]);
    }

    @Override
    public void setsColor(Color color) {
        this.color = color;
    }
}