package com.labi.var11.panels;

import javax.swing.*;
import java.awt.*;

public class InformationPanel extends JPanel {
    private JLabel animalAmount;
    private JLabel timer;


    public InformationPanel() {
        setLayout(new GridLayout(1,2));
        timer = new JLabel("timer:0");
        animalAmount = new JLabel("animals:0");
        add(timer);
        add(animalAmount);
        setVisible(true);

    }

    public void setTimer(Integer time){
        timer.setText("timer:"+time);


    }

    public void setAnimalAmount(Integer animals){
        animalAmount.setText("animals:"+animals);
    }

    public void setTimerVisibility(Boolean isVisible){
        timer.setVisible(isVisible);
    }
}
