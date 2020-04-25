package Contr;

import Habit.Habitat;

import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

public class Clock extends JPanel{

    ButtonGroup group;
    JRadioButton  yes_clock;
    JRadioButton no_clock;
    Boolean dd=true;

    public Clock(Habitat habitat) {

        setLayout(new GridLayout(2, 1));

        group = new ButtonGroup(); // Объединение в группу (нажат может быть только 1)

        yes_clock = new JRadioButton("Показывать время", true);
        no_clock = new JRadioButton("Скрывать время", false);

        group.add(yes_clock);
        group.add(no_clock);

        add(yes_clock);
        add(no_clock);

        setVisible(true);

        yes_clock.setFocusable(false);
        no_clock.setFocusable(false);
        setFocusable(false);

        yes_clock.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                habitat.Clock_yes_no(true);
                dd=true;
                yes_clock.setFocusable(false);
            }
        });

        no_clock.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                habitat.Clock_yes_no(false);
                dd=false;
                no_clock.setFocusable(false);
            }
        });
    }

    public Boolean Return_dd(){
        return dd;
    }

    public void Change_dd() {
        this.dd=!this.dd;
    }
}
