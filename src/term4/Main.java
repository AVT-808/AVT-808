package term4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Main {
    private static boolean isTimeVisible = true;
    private static boolean isStopped = false;

    public static void main(String[] args) {
        Habitat habitat = new Habitat();
        System.out.println( habitat.hasFocus());

        //////

//        BorderLayout borderLayout = new BorderLayout();
//        JPanel jPanel = new JPanel(borderLayout);
//        JButton jButton = new JButton("btn");
//        jPanel.add(jButton);
//        habitat.getJFrame().setContentPane(jPanel);




        ////

        Timer timer = new Timer(1000, e -> habitat.update());

        habitat.update();


        habitat.getJFrame().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == 'b' || e.getKeyChar() == 'B'){
                    System.out.println("Pressed B");
                    timer.start();
                }
            }
        });

        habitat.getJFrame().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == 'e' || e.getKeyChar() == 'E'){
                    System.out.println("Pressed E");
                    timer.stop();

                    isStopped = true;
                    habitat.update();

                }
            }
        });

        habitat.getJFrame().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == 't' || e.getKeyChar() == 'T') {
                    System.out.println("Pressed T");

                    isTimeVisible = !isTimeVisible;
                }
            }
        });

        habitat.getMyComponent().getBtnStart().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                timer.start();
                habitat.getMyComponent().getBtnStart().setEnabled(false);
                habitat.getMyComponent().getBtnStop().setEnabled(true);
            }
        });

        habitat.getMyComponent().getBtnStop().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                timer.stop();

                habitat.getMyComponent().getBtnStart().setEnabled(true);
                habitat.getMyComponent().getBtnStop().setEnabled(false);

                isStopped = true;
                habitat.update();

            }
        });

    }

    public static boolean isTimeVisible() { return isTimeVisible; }

    public static boolean isStopped() { return isStopped; }
}
