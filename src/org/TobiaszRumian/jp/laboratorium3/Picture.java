package org.TobiaszRumian.jp.laboratorium3;
/*
 * @version 1.0
 * @author Tobiasz Rumian
 * Data: 01 Listopad 2016 r.
 * Indeks: 226131
 * Grupa: śr 13:15 TN
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Iterator;
import java.util.Vector;


class Picture extends JPanel implements KeyListener, MouseListener, MouseMotionListener, Serializable, MouseWheelListener {

    private static final long serialVersionUID = 1L;

    private java.awt.Point lastMousePosition = new java.awt.Point();
    Vector<Figure> figures = new Vector<>();

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Figure f : figures)
            f.draw(g);
    }

    void addFigure(Figure fig) {
        figures.forEach(Figure::unselect);
        fig.select();
        figures.add(fig);
        repaint();
    }

    void moveAllFigures(float dx, float dy) {
        figures.stream().filter(Figure::isSelected).forEach(f -> f.move(dx, dy));
        repaint();
    }

    void scaleAllFigures(float s) {
        figures.stream().filter(Figure::isSelected).forEach(f -> f.scale(s));
        repaint();
    }

    public String toString() {
        String str = "";
        for (Figure f : figures)
            str += f.toString() + "\n         ";
        str += "}";
        return str;
    }

    public void keyPressed(KeyEvent evt) {
        int dist;
        if (evt.isShiftDown()) dist = 10;
        else dist = 1;
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_UP:
                moveAllFigures(0, -dist);
                break;
            case KeyEvent.VK_DOWN:
                moveAllFigures(0, dist);
                break;
            case KeyEvent.VK_LEFT:
                moveAllFigures(-dist, 0);
                break;
            case KeyEvent.VK_RIGHT:
                moveAllFigures(dist, 0);
                break;
            case KeyEvent.VK_DELETE:
                Iterator<Figure> i = figures.iterator();
                while (i.hasNext()) {
                    Figure f = i.next();
                    if (f.isSelected()) {
                        i.remove();
                    }
                }
                repaint();
                break;
        }
    }

    public void keyTyped(KeyEvent evt) {
        switch (evt.getKeyChar()) {
            case 'p':
                addFigure(new Point());
                break;
            case 'c':
                addFigure(new Circle());
                break;
            case 't':
                addFigure(new Triangle());
                break;
            case 'g':
                addFigure(new Star());
                break;
            case 'k':
                addFigure(new Hourglass());
                break;
            case 'f':
                addFigure(new Pentagon());
                break;
            case '+':
                scaleAllFigures(1.1f);
                break;
            case '-':
                scaleAllFigures(0.9f);
                break;
        }
    }

    public void mouseClicked(MouseEvent e) {
        int px = e.getX();
        int py = e.getY();
        for (Figure f : figures) {
            if (!e.isAltDown()) f.unselect();
            if (f.isInside(px, py)) f.select(!f.isSelected());
        }
        repaint();
    }

    public void mousePressed(MouseEvent e) {
        lastMousePosition.setLocation(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        java.awt.Point mousePosition = new java.awt.Point(e.getX(), e.getY());
        float dx = mousePosition.x - lastMousePosition.x;
        float dy = mousePosition.y - lastMousePosition.y;
        figures.stream().filter(Figure::isSelected).forEach(f -> f.move(dx, dy));
        lastMousePosition = mousePosition;
        repaint();
    }

    void savePictureToFile(String fileName) throws Exception {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
        out.writeObject(figures);
        out.close();
    }

    void loadPictureFromFile(String fileName) throws Exception {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
        figures = (Vector<Figure>) in.readObject();
        in.close();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getUnitsToScroll() < 0) figures.stream().filter(Figure::isSelected).forEach(f -> f.scale(1.1f));
        else if (e.getUnitsToScroll() > 0) figures.stream().filter(Figure::isSelected).forEach(f -> f.scale(0.9f));
        repaint();
    }
}
