package org.TobiaszRumian.jp.laboratorium3;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Color.blue;

/**
 * Created by Tobiasz Rumian on 28.10.2016.
 */
public class GraphicEditor extends JFrame implements ActionListener {

    private static final long serialVersionUID = 3727471814914970170L;


    private final String DESCRIPTION = "OPIS PROGRAMU\n\n" + "Aktywna klawisze:\n"
            + "   strzalki ==> przesuwanie figur\n"
            + "   SHIFT + strzalki ==> szybkie przesuwanie figur\n"
            + "   +,-  ==> powiekszanie, pomniejszanie\n"
            + "   DEL  ==> kasowanie figur\n"
            + "   p  ==> dodanie nowego punktu\n"
            + "   c  ==> dodanie nowego kola\n"
            + "   t  ==> dodanie nowego trojkata\n"
            + "   g  ==> dodanie nowej gwiazdy\n"
            + "   k  ==> dodanie nowej klepsydry\n"
            + "   f  ==> dodanie nowego pięciokąta\n"
            + "\nOperacje myszka:\n" + "   klik ==> zaznaczanie figur\n"
            + "   ALT + klik ==> zmiana zaznaczenia figur\n"
            + "   przeciaganie ==> przesuwanie figur";

    private final String ABOUT = "Autor:\nTobiasz Rumian\nIndeks: 226131";

    protected Picture picture;

    private JMenu[] menu = {
            new JMenu("Figury"),
            new JMenu("Edytuj"),
            new JMenu("Pomoc")};

    private JMenuItem[] items = {
            new JMenuItem("Punkt"),//0
            new JMenuItem("Kolo"),//1
            new JMenuItem("Trojkat"),//2
            new JMenuItem("Gwiazda"),//3
            new JMenuItem("Klepsydra"),//4
            new JMenuItem("Pięciokąt"),//5
            new JMenuItem("Wypisz wszystkie"),//6
            new JMenuItem("Przesun w gore"),//7
            new JMenuItem("Przesun w dol"),//8
            new JMenuItem("Powieksz"),//9
            new JMenuItem("Pomniejsz"),//10
            new JMenuItem("Autor"),//11
            new JMenuItem("Opis programu")//12
    };

    private JButton buttonPoint = new JButton("Punkt");
    private JButton buttonCircle = new JButton("Kolo");
    private JButton buttonTriangle = new JButton("Trojkat");
    private JButton buttonStar = new JButton("Gwiazda");
    private JButton buttonHourglass= new JButton("Klepsydra");
    private JButton buttonPentagon= new JButton("Pięciokąt");
    private JButton immediateButton = new JButton("now");

    public GraphicEditor()
        { super ("Edytor graficzny");
            setSize(800,800);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            for (JMenuItem item : items) item.addActionListener(this);

            // dodanie opcji do menu "Figury"
            menu[0].add(items[0]);
            menu[0].add(items[1]);
            menu[0].add(items[2]);
            menu[0].add(items[3]);
            menu[0].add(items[4]);
            menu[0].add(items[5]);
            menu[0].addSeparator();
            menu[0].add(items[6]);

            // dodanie opcji do menu "Edytuj"
            menu[1].add(items[7]);
            menu[1].add(items[8]);
            menu[1].addSeparator();
            menu[1].add(items[9]);
            menu[1].add(items[10]);


            menu[2].add(items[11]);
            menu[2].add(items[12]);
            // dodanie do okna paska menu
            JMenuBar menuBar = new JMenuBar();
            for (JMenu aMenu : menu) menuBar.add(aMenu);
            setJMenuBar(menuBar);

            picture=new Picture();
            picture.addKeyListener(picture);
            picture.setFocusable(true);
            picture.addMouseListener(picture);
            picture.addMouseMotionListener(picture);
            picture.setLayout(new FlowLayout());

            buttonPoint.addActionListener(this);
            buttonCircle.addActionListener(this);
            buttonTriangle.addActionListener(this);
            buttonStar.addActionListener(this);
            buttonHourglass.addActionListener(this);
            buttonPentagon.addActionListener(this);
            immediateButton.addActionListener(new ImmediateListener());

            picture.add(buttonPoint);
            picture.add(buttonCircle);
            picture.add(buttonTriangle);
            picture.add(buttonStar);
            picture.add(buttonHourglass);
            picture.add(buttonPentagon);
            picture.add(immediateButton);

            setContentPane(picture);
            setVisible(true);
        }
        @Override
    public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource();
        if (source == buttonPoint)
            picture.addFigure(new Point());
        else if (source == buttonCircle)
            picture.addFigure(new Circle());
        else if (source == buttonTriangle)
            picture.addFigure(new Triangle());
        else if(source == buttonStar)
            picture.addFigure(new Star());
        else if(source == buttonPentagon)
            picture.addFigure(new Pentagon());
        else if(source == buttonHourglass)
            picture.addFigure(new Hourglass());
        else if (source == items[0])
            picture.addFigure(new Point());
        else if (source == items[1])
            picture.addFigure(new Circle());
        else if (source == items[2])
            picture.addFigure(new Triangle());
        else if (source == items[3])
            picture.addFigure(new Star());
        else if (source == items[4])
            picture.addFigure(new Hourglass());
        else if (source == items[5])
            picture.addFigure(new Pentagon());
        else if (source == items[6])
            JOptionPane.showMessageDialog(null, picture.toString());
        else if (source == items[7])
            picture.moveAllFigures(0, -10);
        else if (source == items[8])
            picture.moveAllFigures(0, 10);
        else if (source == items[9])
            picture.scaleAllFigures(1.1f);
        else if (source == items[10])
            picture.scaleAllFigures(0.9f);
        else if (source == items[11])
            JOptionPane.showMessageDialog(null, ABOUT);
        else if (source == items[12])
            JOptionPane.showMessageDialog(null, DESCRIPTION);

        picture.requestFocus();
        repaint();
    }
    private class ImmediateListener implements ActionListener
        {
            private JDialog dialog;
            private JColorChooser chooser;
            public ImmediateListener()
                {
                    chooser = new JColorChooser();
                    chooser.getSelectionModel().addChangeListener(new ChangeListener()
                        {
                            @Override
                            public void stateChanged(ChangeEvent event)
                                {
                                    picture.figures.stream().filter(Figure::isSelected).forEach(f -> f.setsColor(chooser.getColor()));
                                    repaint();
                                    //picture.setBackground(chooser.getColor());
                                }
                        });
                    dialog = new JDialog((Frame) null,false);
                    dialog.add(chooser);
                    dialog.pack();
                }
            public void actionPerformed(ActionEvent event)
                {
                    chooser.setColor(getBackground());
                    dialog.setVisible(true);
                }
        }



    public static void main(String[] args)
        { new GraphicEditor();
        }
    }
