package Habitat;

import Panels.DrawRabbitPanel;
import Factory.AbstractFactory;
import Factory.ConcreteFactory;
import Models.Abstract.BaseRabbit;
import Models.WhiteRabbit;
import Panels.InformationDialog;
import Panels.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Habitat extends JFrame {


    private final AbstractFactory factory;
    private final List<BaseRabbit> rabbits;
    private Integer time;
    private final DrawRabbitPanel drawRabbit;
    private MenuPanel menuPanel;
    private Integer whiteRabbitsAmount;


    public Habitat(String title, Integer normalRabbitBirthTime, Integer whiteRabbitBirthTime, Float normalRabbitBirthProbability, Float rabbitsPercent) {
        super(title);
        int width = 1024;
        int height = 768;
        setSize(width, height);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.time = 0;
        rabbits = new ArrayList<>();
        factory = new ConcreteFactory(normalRabbitBirthTime,whiteRabbitBirthTime,normalRabbitBirthProbability,rabbitsPercent);
        menuPanel = new MenuPanel();
        menuPanel.setBounds(0,0, width,20);
        drawRabbit = new DrawRabbitPanel(rabbits);
        drawRabbit.setBounds(0,21, width, height -21);
        add(menuPanel);
        add(drawRabbit);
        whiteRabbitsAmount=0;
    }

    public void stop(){//остановить отрисовку среды
        InformationDialog informationPanel = new InformationDialog(this,"Информация",time,factory.getAmountOfBirth() - whiteRabbitsAmount,whiteRabbitsAmount);
        informationPanel.viewInformation();
        factory.destroy();
        rabbits.clear();
        time = 0;

        menuPanel.setRabbitsAmount(factory.getAmountOfBirth());
        menuPanel.setTimer(time);
        drawRabbit.repaint();
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
        int xCoordinate = coordinatesRandom.nextInt(drawRabbit.getWidth()-100);
        int yCoordinate = coordinatesRandom.nextInt(drawRabbit.getHeight()-100);

        Point coordinates  = new Point(xCoordinate, yCoordinate);

        try{
            BaseRabbit rabbit = factory.birth(time, coordinates);
            menuPanel.setRabbitsAmount(factory.getAmountOfBirth());
            if(rabbit != null) {
                if (rabbit.getClass() == WhiteRabbit.class)
                    whiteRabbitsAmount++;
                rabbits.add(rabbit);
                drawRabbit.repaint();
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}

