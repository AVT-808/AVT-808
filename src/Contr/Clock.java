package Contr;

import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

public class Clock extends JPanel{

    ButtonGroup group;
    static JRadioButton  yes_clock;
    static JRadioButton no_clock;
    static Boolean dd = true;

    public Clock() {

        setLayout(new GridLayout(2, 1));

        group = new ButtonGroup(); // Объединение в группу (нажат может быть только 1)

        yes_clock = new JRadioButton("Показывать время", true);
        no_clock = new JRadioButton("Скрывать время", false);

        group.add(yes_clock);
        group.add(no_clock);

        add(yes_clock);
        add(no_clock);

        setVisible(true);

        setFocusable(false);

        yes_clock.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Line.Clock(true); // Показать линию
                dd=true;
                yes_clock.setEnabled(false); // Блокировка
                no_clock.setEnabled(true);
                yes_clock.setFocusable(false);
            }
        });

        no_clock.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Line.Clock(false);
                dd=false;
                yes_clock.setEnabled(true);
                no_clock.setEnabled(false);
                no_clock.setFocusable(false);
            }
        });
    }

    public static Boolean Return_dd(){
        return dd;
    } // В клаве получаем инфу отсюда

    public static void Change_dd() { dd=!dd;  } // Изменяя на клаве, изменяется и здесь

    public static void Change_point() {
        if (dd) {
            yes_clock.setSelected(true); // Нажимается "да" само на экране
            yes_clock.setEnabled(false); // Блокировка
            no_clock.setEnabled(true);
        }
        else {
            no_clock.setSelected(true); // Нажимается "нет" само на экране
            yes_clock.setEnabled(true);
            no_clock.setEnabled(false);
        }
    }
}
