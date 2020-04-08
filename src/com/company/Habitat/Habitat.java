package com.company.Habitat;

import com.company.Factory.AbstractFactory;
import com.company.Factory.ConcreteFactory;
import com.company.Models.Abstract.BaseBee;
import com.company.Models.Drone;
import com.company.Panels.DrawBee;
import com.company.Panels.Information;
import com.company.Panels.MenuPanel;

import javax.swing.*;

import java.util.Random;

public class Habitat extends JFrame {

    private int i = 0;
    private BaseBee [] bees;
    private int time;
    private AbstractFactory factory;
    private DrawBee drawBee;
    private MenuPanel menuPanel;
    private Integer DronesAmount;

    public Habitat(String title,int timeBirthDrone,int timeBirthWorker, double probabilityJoWorker,double percentAllBees){
        super(title);

        int width = 1200;
        int height = 800;
        setSize(width, height);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.time = 0;
        bees = new BaseBee[500];
        factory = new ConcreteFactory(timeBirthDrone,timeBirthWorker,probabilityJoWorker,percentAllBees);
        menuPanel = new MenuPanel();
        menuPanel.setBounds(0,0, width,30);
        drawBee = new DrawBee(bees);
        drawBee.setBounds(0,21, width, height -21);
        add(menuPanel);
        add(drawBee);
        DronesAmount=0;
    }

    public void stop(){//остановить отрисовку среды
        Information informationPanel = new Information(this,"Информация",time,factory.getAmountOfBirth() - DronesAmount,DronesAmount);
        informationPanel.viewInformation();
        factory.destroy();
        for (int j=0; j<500; j++) // "Обнуление" массива
        { bees[j] = null; }
        i = 0;
        time = 0;
        menuPanel.setBeesAmount(factory.getAmountOfBirth());
        menuPanel.setTimer(time);
        drawBee.repaint();
    }
    public void timerVisibility(Boolean isShown){//скрыть таймер
        if (isShown)
            menuPanel.setTimerVisibility(false);
        else
            menuPanel.setTimerVisibility(true);
    }
    void update() {//продолжить симуляцию
        time++;
        menuPanel.setTimer(time);
        Random coordinatesRandom = new Random();
        int x_cord = coordinatesRandom.nextInt(drawBee.getWidth()-100);
        int y_cord = coordinatesRandom.nextInt(drawBee.getHeight()-100);

        try{
            BaseBee bee = factory.birth(x_cord,y_cord,time);
            menuPanel.setBeesAmount(factory.getAmountOfBirth());
            if(bee != null)
            {
                if (bee.getClass() == Drone.class)
                    DronesAmount++;
                    bees[i] = bee;
                    i++;
                    drawBee.repaint();
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
