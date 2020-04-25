package com.company.Habitat;

import com.company.Factory.AbstractFactory;
import com.company.Factory.ConcreteFactory;
import com.company.Habitat.BeesArray.Singleton;
import com.company.Models.Abstract.BaseBee;
import com.company.Models.Drone;
import com.company.Panels.*;

import javax.swing.*;


import java.awt.*;
import java.util.Random;

public class Habitat extends JFrame {

    private final Singleton bees;
    private int time;
    private AbstractFactory factory;
    private DrawBee drawBee;
    private MenuPanel menuPanel;
    private Buttons button;
    private MenuButtons menuButtons;


    JButton startButton;
    JButton stopButton;

    public Habitat(){


        int width = 1200;
        int height = 800;
        setSize(width, height);
        setResizable(false);

        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bees = Singleton.getInstance();
        this.time = 0;

        factory = new ConcreteFactory();

        menuPanel = new MenuPanel();
        menuPanel.setMaximumSize(new Dimension(width,50));
        drawBee = new DrawBee();
        drawBee.setBounds(0,21, width, height -21);
        menuButtons = new MenuButtons(this);
        menuButtons.setMaximumSize(new Dimension(width, 90));
        button = new Buttons(this);
        button.setMaximumSize(new Dimension(75,50));

        startButton = button.returnStart();
        stopButton = button.returnStop();

        menuButtons.add(button);
        add(drawBee);
        add(menuPanel);
        add(menuButtons);
        setFocusable(true);
    }

    public JButton returnStart(){
        return startButton;
    }

    public JButton returnStop() {
        return stopButton;
    }

    public void stop(){//остановить отрисовку среды

        Boolean b = true;
        Boolean bl = menuButtons.return_chb();

        if (bl) {
            Information informationPanel = new Information(this, "Информация", time, factory.getAmountOfWorkers(), factory.getAmountOfDrones());
            informationPanel.setVisible(true);
            b = informationPanel.return_B();
        }

        if(b) {
            time = 0;
            bees.clear();
            drawBee.repaint();
            factory.destroy();
        }
    }

    public void Timer_show_hide(Boolean bool){
        if(!bool) menuPanel.TimerShowHide(false);
        else menuPanel.TimerShowHide(true);
    }

    public Boolean return_bool(){
        Boolean bool = menuButtons.return_bool();
        menuButtons.return2_bool();
        return bool;
    }

    void update() {//продолжить симуляцию
        time++;
        menuPanel.setTimer(time);
        Random coordinatesRandom = new Random();
        int x_cord = coordinatesRandom.nextInt(drawBee.getWidth()-100);
        int y_cord = coordinatesRandom.nextInt(drawBee.getHeight()-100);

        requestFocus();//фокус на поле

        try{
            BaseBee bee = factory.birth(x_cord,y_cord,time, menuButtons);
            menuPanel.setBeesAmount(factory.getAmountOfBirth());
            if(bee != null) {
                bees.addBees(bee);
                drawBee.repaint();
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}