package Contr;

import Habit.Habitat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Butt extends JPanel {

    StartStop st;
    private static final JButton button_start =  new JButton("Старт");
    private static final JButton button_stop = new JButton("Стоп");


    public Butt(Habitat habitat) {
        setLayout(new GridLayout(2, 1));

        st = StartStop.getN();

        button_start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                st.StarT(habitat,button_start, button_stop);
            }
        });

        button_stop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                st.StoP(habitat, button_start, button_stop);
            }
        });

        add(button_start);
        add(button_stop);

    }

    public static JButton Return_start() { return button_start; } // Для доступа к этим же кнопкам в Keyboard

    public static JButton Return_stop() { return button_stop; }

}
