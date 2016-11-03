package org.TobiaszRumian.jp.laboratorium3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Tobiasz Rumian on 28.10.2016.
 */
public class Picture extends JPanel implements KeyListener, MouseListener, MouseMotionListener, Serializable {

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
        for (Figure f : figures) {
            f.unselect();
        }
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
        String str = "Rysunek{ ";
        for (Figure f : figures)
            str += f.toString() + "\n         ";
        str += "}";
        return str;
    }


    /*
     *  Impelentacja interfejsu KeyListener - obs�uga zdarze� generowanych
     *  przez klawiatur� gdy focus jest ustawiony na ten obiekt.
     */
    public void keyPressed(KeyEvent evt)
    //Virtual keys (arrow keys, function keys, etc) - handled with keyPressed() listener.
    {
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

    public void keyReleased(KeyEvent evt) {
    }

    public void keyTyped(KeyEvent evt)
    //Characters (a, A, #, ...) - handled in the keyTyped() listener.
    {
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


    /*
     * Implementacja interfejsu MouseListener - obs�uga zdarze� generowanych przez myszk�
     * gdy kursor myszki jest na tym panelu
     */
    public void mouseClicked(MouseEvent e)
    // Invoked when the mouse button has been clicked (pressed and released) on a component.
    {
        int px = e.getX();
        int py = e.getY();
        for (Figure f : figures) {
            if (!e.isAltDown()) f.unselect();
            if (f.isInside(px, py)) f.select(!f.isSelected());
        }
        repaint();
    }

    public void mouseEntered(MouseEvent e)
    //Invoked when the mouse enters a component.
    {
    }

    public void mouseExited(MouseEvent e)
    //Invoked when the mouse exits a component.
    {
    }


    public void mousePressed(MouseEvent e)
    // Invoked when a mouse button has been pressed on a component.
    {
        lastMousePosition.setLocation(e.getX(), e.getY());
    }

    public void mouseReleased(MouseEvent e)
    //Invoked when a mouse button has been released on a component.
    {
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

    @Override
    public void mouseMoved(MouseEvent e) {

    }


    void savePictureToFile(String fileName) throws Exception {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
        out.writeObject(figures);
        out.close();
    }

    /**
     * Wczytuje dane z pliku.
     * @param fileName nazwa pliku.
     * @throws Exception Zwraca wyjątek gdy nie udało się wczytać.
     */
    void loadPictureFromFile(String fileName) throws Exception {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
        figures = (Vector<Figure>)in.readObject();
        in.close();
    }
}
