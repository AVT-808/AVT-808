package com.company.Panels;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    private final JLabel beesAmount;
    private final JLabel timer;


    public MenuPanel()
    {
        setLayout(new GridLayout(1,2));
        beesAmount = new JLabel("Количество рожденных пчел: 0");
        beesAmount.setFont(new Font("Courier New", Font.BOLD,16));
        timer = new JLabel("Таймер: 0");
        timer.setFont(new Font("Courier New", Font.BOLD,16));

        add(beesAmount);
        add(timer);
    }

    public void setBeesAmount(int beesAmount) {
        this.beesAmount.setText("Количество рожденных пчел: " + beesAmount);
    }

    public void setTimer(Integer timer) {
        this.timer.setText("Таймер: " + timer);
    }



    public void TimerShowHide(boolean b) {
        timer.setVisible(b);
    }
}

