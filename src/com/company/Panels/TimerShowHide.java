package com.company.Panels;

import com.company.Habitat.Habitat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerShowHide extends JPanel {
    ButtonGroup buttonGroup;
    JRadioButton show_timer;
    JRadioButton hide_timer;
    Boolean bool = true;

    public TimerShowHide(Habitat habitat){
        buttonGroup = new ButtonGroup();//объединение в группу

        setLayout(new GridLayout(2, 1));

        show_timer = new JRadioButton("Показать таймер",true);
        hide_timer = new JRadioButton("Скрыть таймер",true);

        buttonGroup.add(show_timer);
        buttonGroup.add(hide_timer);
        add(show_timer);
        add(hide_timer);
        setVisible(true);
        show_timer.setFocusable(false);
        hide_timer.setFocusable(false);
        setFocusable(false);

        show_timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                habitat.Timer_show_hide(true);
                bool = true;
                show_timer.setFocusable(false);
            }
        });
        hide_timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                habitat.Timer_show_hide(false);
                bool = false;
                hide_timer.setFocusable(false);
            }
        });

    }

    public Boolean return_bool(){
        return bool;
    }

    public void return2_bool(){
        this.bool =!this.bool;
    }
}
