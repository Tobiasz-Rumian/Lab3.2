package org.TobiaszRumian.jp.laboratorium3;
/*
 *  Program GraphicEditor
 *  Pozwala rysować przy użyciu dostępnych figur.
 *  Pozwala także na zmianę ich rozmiaru, przemieszczanie za pomocą myszy jak i klawiatury.
 *  Pozwala zmienić rozmiar figury za pomocą rolki myszy.
 *  Pozwala zapisać utworzony obraz jak i go wczytać.
 *  implementuje 6 figur: punkt, koło, trójkąt, klepsydrę, gwiazę oraz pięciokąt.
 *  Pozwala zmienić kolor figury w czasie rzeczywistym.
 *  Pozwala stworzyć dozwolone figury w podanych przez urzytkownika rozmiarach.
 *
 *  @author Tobiasz Rumian
 *  @version 1.0
 *   Data: 01 Listopad 2016 r.
 *   Indeks: 226131
 *   Grupa: śr 13:15 TN
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class GraphicEditor extends JFrame implements ActionListener {

    private static final long serialVersionUID = 3727471814914970170L;

    private final String FILENAME = "PICTURE.BIN";

    private final String DESCRIPTION = "OPIS PROGRAMU\n\n" +
            "Aktywna klawisze:\n" +
            "   strzalki ==> przesuwanie figur\n" +
            "   SHIFT + strzalki ==> szybkie przesuwanie figur\n" +
            "   +,-  ==> powiekszanie, pomniejszanie\n" +
            "   DEL  ==> kasowanie figur\n" +
            "   p  ==> dodanie nowego punktu\n" +
            "   c  ==> dodanie nowego kola\n" +
            "   t  ==> dodanie nowego trojkata\n" +
            "   g  ==> dodanie nowej gwiazdy\n" +
            "   k  ==> dodanie nowej klepsydry\n" +
            "   f  ==> dodanie nowego pięciokąta\n" +
            "\nOperacje myszka:\n" +
            "   klik ==> zaznaczanie figur\n" +
            "   ALT + klik ==> zmiana zaznaczenia figur\n" +
            "   przeciaganie ==> przesuwanie figur\n" +
            "   Kółko myszki ==> Zmiana rozmiaru figury";

    private final Picture picture;

    private JMenu[] menu = {new JMenu("Figury"), new JMenu("Edytuj"), new JMenu("Pomoc")};
    private JMenu[] menu1 = {new JMenu("Punkt"), new JMenu("Koło"), new JMenu("Trójkąt"), new JMenu("Gwiazda"), new JMenu("Klepsydra"), new JMenu("Pięciokąt")};
    private JMenuItem[] items = {new JMenuItem("Losowy Punkt"),//0
            new JMenuItem("Zadany Punkt"),//1
            new JMenuItem("Losowe Koło"),//2
            new JMenuItem("Zadane Koło"),//3
            new JMenuItem("Losowy Trójkąt"),//4
            new JMenuItem("Zadany Trójkąt"),//5
            new JMenuItem("Losowa Gwiazda"),//6
            new JMenuItem("Zadana Gwiazda"),//7
            new JMenuItem("Losowa Klepsydra"),//8
            new JMenuItem("Zadana Klepsydra"),//9
            new JMenuItem("Losowy Pięciokąt"),//10
            new JMenuItem("Zadany Pięciokąt"),//11
            new JMenuItem("Wypisz wszystkie"),//12
            new JMenuItem("Przesun w gore"),//13
            new JMenuItem("Przesun w dol"),//14
            new JMenuItem("Powieksz"),//15
            new JMenuItem("Pomniejsz"),//16
            new JMenuItem("Autor"),//17
            new JMenuItem("Opis programu"),//18
            new JMenuItem("Zapisz obraz"),//19
            new JMenuItem("Odczytaj obraz")//20
    };

    private JButton buttonPoint = new JButton("Punkt");
    private JButton buttonCircle = new JButton("Kolo");
    private JButton buttonTriangle = new JButton("Trojkat");
    private JButton buttonStar = new JButton("Gwiazda");
    private JButton buttonHourglass = new JButton("Klepsydra");
    private JButton buttonPentagon = new JButton("Pięciokąt");

    private GraphicEditor() {
        super("Edytor graficzny");
        setSize(800, 800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        for (JMenuItem item : items) item.addActionListener(this);
        menu[0].add(menu1[0]);
        menu[0].add(menu1[1]);
        menu[0].add(menu1[2]);
        menu[0].add(menu1[3]);
        menu[0].add(menu1[4]);
        menu[0].add(menu1[5]);
        menu[0].addSeparator();
        menu[0].add(items[6]);

        menu1[0].add(items[0]);
        menu1[0].add(items[1]);
        menu1[1].add(items[2]);
        menu1[1].add(items[3]);
        menu1[2].add(items[4]);
        menu1[2].add(items[5]);
        menu1[3].add(items[6]);
        menu1[3].add(items[7]);
        menu1[4].add(items[8]);
        menu1[4].add(items[9]);
        menu1[5].add(items[10]);
        menu1[5].add(items[11]);
        menu[0].add(items[12]);
        menu[1].addSeparator();
        menu[1].add(items[13]);
        menu[1].add(items[14]);
        menu[1].add(items[15]);
        menu[1].add(items[16]);

        menu[2].add(items[17]);
        menu[2].add(items[18]);
        menu[2].add(items[19]);
        menu[2].add(items[20]);
        JMenuBar menuBar = new JMenuBar();
        for (JMenu aMenu : menu) menuBar.add(aMenu);
        setJMenuBar(menuBar);

        picture = new Picture();
        picture.addKeyListener(picture);
        picture.setFocusable(true);
        picture.addMouseListener(picture);
        picture.addMouseMotionListener(picture);
        picture.addMouseWheelListener(picture);
        picture.setLayout(new FlowLayout());

        buttonPoint.addActionListener(this);
        buttonCircle.addActionListener(this);
        buttonTriangle.addActionListener(this);
        buttonStar.addActionListener(this);
        buttonHourglass.addActionListener(this);
        buttonPentagon.addActionListener(this);
        JButton buttonChangeColor = new JButton("Zmień kolor");
        buttonChangeColor.addActionListener(new ImmediateListener());

        picture.add(buttonPoint);
        picture.add(buttonCircle);
        picture.add(buttonTriangle);
        picture.add(buttonStar);
        picture.add(buttonHourglass);
        picture.add(buttonPentagon);
        picture.add(buttonChangeColor);

        setContentPane(picture);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource();
        if (source == buttonPoint) picture.addFigure(new Point());
        else if (source == buttonCircle) picture.addFigure(new Circle());
        else if (source == buttonTriangle) picture.addFigure(new Triangle());
        else if (source == buttonStar) picture.addFigure(new Star());
        else if (source == buttonPentagon) picture.addFigure(new Pentagon());
        else if (source == buttonHourglass) picture.addFigure(new Hourglass());
        else if (source == items[0]) picture.addFigure(new Point());
        else if (source == items[1]) setFigure("Point");
        else if (source == items[2]) picture.addFigure(new Circle());
        else if (source == items[3]) setFigure("Circle");
        else if (source == items[4]) picture.addFigure(new Triangle());
        else if (source == items[5]) setFigure("Triangle");
        else if (source == items[6]) picture.addFigure(new Star());
        else if (source == items[7]) setFigure("Star");
        else if (source == items[8]) picture.addFigure(new Hourglass());
        else if (source == items[9]) setFigure("Hourglass");
        else if (source == items[10]) picture.addFigure(new Pentagon());
        else if (source == items[11]) setFigure("Pentagon");
        else if (source == items[12]) {
            ShowAllFigures showAllFigures = new ShowAllFigures(this);
            showAllFigures.setVisible(true);
        } else if (source == items[13]) picture.moveAllFigures(0, -10);
        else if (source == items[14]) picture.moveAllFigures(0, 10);
        else if (source == items[15]) picture.scaleAllFigures(1.1f);
        else if (source == items[16]) picture.scaleAllFigures(0.9f);
        else if (source == items[17]) {
            About about;
            try {
                about = new About(this);
                about.setVisible(true);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } else if (source == items[18]) JOptionPane.showMessageDialog(null, DESCRIPTION);
        else if (source == items[19]) {
            try {
                picture.savePictureToFile(FILENAME);
                System.out.println("Plik zostal zapisany");
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            repaint();
        } else if (source == items[20]) {
            try {
                picture.loadPictureFromFile(FILENAME);
                System.out.println("Plik zostal odczytany");
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            repaint();
        }

        picture.requestFocus();
        repaint();
    }

    private void setFigure(String name) {
        float x, y, s;
        Point p1, p2, p3;
        Figure figure = null;
        if (!Objects.equals(name, "Triangle")) {
            SetDialog dialog = new SetDialog(this);
            dialog.setVisible(true);
            x = dialog.getX1();
            y = dialog.getY1();
            s = dialog.getS1();
            if (Objects.equals(name, "Point")) figure = new Point(x, y);
            else if (Objects.equals(name, "Circle")) figure = new Circle(x, y, s);
            else if (Objects.equals(name, "Star")) figure = new Star(x, y, s);
            else if (Objects.equals(name, "Hourglass")) figure = new Hourglass(x, y, s);
            else if (Objects.equals(name, "Pentagon")) figure = new Pentagon(x, y, s);
        } else if (Objects.equals(name, "Triangle")) {
            SetTriangleDialog dialog = new SetTriangleDialog(this);
            dialog.setVisible(true);
            p1 = dialog.getPoint(1);
            p2 = dialog.getPoint(2);
            p3 = dialog.getPoint(3);
            figure = new Triangle(p1, p2, p3);
        }

        picture.addFigure(figure);
    }

    private class ImmediateListener implements ActionListener {
        private JDialog dialog;
        private JColorChooser chooser;

        ImmediateListener() {
            chooser = new JColorChooser();
            chooser.getSelectionModel().addChangeListener(event -> {
                picture.figures.stream().filter(Figure::isSelected).forEach(f -> f.setsColor(chooser.getColor()));
                repaint();
            });
            dialog = new JDialog((Frame) null, false);
            dialog.add(chooser);
            dialog.pack();
        }

        public void actionPerformed(ActionEvent event) {
            chooser.setColor(getBackground());
            dialog.setVisible(true);
        }
    }

    private class SetDialog extends JDialog {
        JTextField x1, y1, s1;

        SetDialog(JFrame owner) {
            super(owner, "Tworzenie figury", true);
            add(new JLabel("Wpisz rozmiary figury"), BorderLayout.NORTH);
            x1 = new JTextField("Wprowadź x");
            y1 = new JTextField("Wprowadź y");
            s1 = new JTextField("Wprowadź rozmiar");
            JPanel panel = new JPanel();
            JButton ok = new JButton("ok");
            ok.addActionListener(e -> setVisible(false));
            panel.add(x1);
            panel.add(y1);
            panel.add(s1);
            add(panel, BorderLayout.CENTER);
            add(ok, BorderLayout.SOUTH);
            setSize(250, 250);
        }

        float getX1() {
            return Float.parseFloat(x1.getText());
        }

        float getY1() {
            return Float.parseFloat(y1.getText());
        }

        float getS1() {
            return Float.parseFloat(s1.getText());
        }

    }

    private class SetTriangleDialog extends JDialog {
        JTextField[] textFields = new JTextField[6];

        SetTriangleDialog(JFrame owner) {
            super(owner, "Tworzenie figury", true);
            add(new JLabel("Wpisz rozmiary figury"), BorderLayout.NORTH);
            textFields[0] = new JTextField("Wprowadź x pierwszego punktu");
            textFields[1] = new JTextField("Wprowadź y pierwszego punktu");
            textFields[2] = new JTextField("Wprowadź x drugiego punktu");
            textFields[3] = new JTextField("Wprowadź y drugiego punktu");
            textFields[4] = new JTextField("Wprowadź x trzeciego punktu");
            textFields[5] = new JTextField("Wprowadź y trzeciego punktu");
            JPanel panel = new JPanel();
            JButton ok = new JButton("ok");
            ok.addActionListener(e -> setVisible(false));
            for (JTextField j : textFields) {
                panel.add(j);
            }
            add(panel, BorderLayout.CENTER);
            add(ok, BorderLayout.SOUTH);
            setSize(250, 250);
        }

        Point getPoint(int x) {
            if (x == 1)
                return new Point(Float.parseFloat(textFields[0].getText()), Float.parseFloat(textFields[1].getText()));
            else if (x == 2)
                return new Point(Float.parseFloat(textFields[2].getText()), Float.parseFloat(textFields[3].getText()));
            else if (x == 3)
                return new Point(Float.parseFloat(textFields[4].getText()), Float.parseFloat(textFields[5].getText()));
            return null;
        }

    }

    private class About extends JDialog {
        About(JFrame owner) throws MalformedURLException {
            super(owner, "O Autorze", true);
            URL url = null;
            try {
                url = new URL("https://media.giphy.com/media/l0HlIKdi4DIEDk92g/giphy.gif");
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            Icon icon = new ImageIcon(url);
            JLabel label = new JLabel(icon);
            add(new JLabel("Autor:\t Tobiasz Rumian\t Indeks: 226131"), BorderLayout.NORTH);
            add(label, BorderLayout.CENTER);
            JButton ok = new JButton("ok");
            ok.addActionListener(e -> setVisible(false));
            add(ok, BorderLayout.SOUTH);
            setSize(400, 400);
        }
    }

    private class ShowAllFigures extends JDialog {
        ShowAllFigures(JFrame owner) {
            super(owner, "Wszystkie figury", true);
            add(new JLabel("Rysunek{"), BorderLayout.NORTH);
            JTextArea textArea = new JTextArea(8, 40);
            textArea.append(picture.toString());
            JScrollPane scrollPane = new JScrollPane(textArea);
            textArea.setEditable(false);
            JButton ok = new JButton("ok");
            ok.addActionListener(e -> setVisible(false));
            add(scrollPane, BorderLayout.CENTER);
            add(ok, BorderLayout.SOUTH);
            setSize(250, 500);
        }
    }

    public static void main(String[] args) {
        new GraphicEditor();
    }
}
