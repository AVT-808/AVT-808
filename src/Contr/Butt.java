package Contr;

import Habit.Habitat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Butt extends JPanel {

    StarT_StoP st;
    private final JButton button_start;
    private final JButton button_stop;


    public Butt(Habitat habitat) {
        setLayout(new GridLayout(2, 1));

        button_start = new JButton("Старт");
        button_stop = new JButton("Стоп");

        st = StarT_StoP.getN();

        // присоединение слушателя прямо на месте
        button_start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                st.StarT(habitat,button_start, button_stop);
                setFocusable(false);
            }
        });

        button_stop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                st.StoP(habitat, button_start, button_stop);
                setFocusable(false);
            }
        });


        add(button_start);
        add(button_stop);

        button_start.setFocusable(false);
        button_stop.setFocusable(false);
        setFocusable(false);
    }

    public JButton Return_start(){
        return button_start;
    }

    public JButton Return_stop(){
        return button_stop;
    }
}
