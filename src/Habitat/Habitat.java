package Habitat;

import DrawPanel.DrawRabbit;
import Factory.AbstractFactory;
import Factory.ConcreteFactory;
import Models.Abstract.BaseRabbit;
import Models.WhiteRabbit;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Habitat extends JFrame {

    private final JLabel rabbitsAmount;
    private final JLabel timer;
    private final AbstractFactory factory;
    private final List<BaseRabbit> rabbits;
    private Integer time;
    private final DrawRabbit drawRabbit;
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
        rabbitsAmount = new JLabel("Количество рожденных кроликов: " + factory.getAmountOfBirth());
        timer = new JLabel("Таймер: " + time);
        JPanel labels = new JPanel();
        labels.setLayout(new GridLayout(1,2));
        labels.add(rabbitsAmount);
        labels.add(timer);
        labels.setBounds(0,0, width,20);
        drawRabbit = new DrawRabbit(rabbits);
        drawRabbit.setBounds(0,21, width, height -21);
        add(labels);
        add(drawRabbit);
        whiteRabbitsAmount=0;
    }

    public void stop(){//остановить отрисовку среды
        JLabel timerResult = new JLabel("Время симуляции: " + time);
        timerResult.setFont(new Font("Courier New", Font.ITALIC,16));
        timerResult.setForeground(Color.RED);
        JLabel normalRabbitsResult = new JLabel("Количество обычных кроликов: " + (factory.getAmountOfBirth() - whiteRabbitsAmount));
        normalRabbitsResult.setFont(new Font("Calibri", Font.ITALIC,15));
        normalRabbitsResult.setForeground(Color.GREEN);
        JLabel whiteRabbitsResult = new JLabel("Количество кроликов альбиносов: " + whiteRabbitsAmount);
        whiteRabbitsResult.setFont(new Font("Times New Roman", Font.ITALIC,14));
        whiteRabbitsResult.setForeground(Color.BLUE);
        JDialog dialog = new JDialog(this, "Информация", true);
        dialog.setLayout(null);
        dialog.setResizable(false);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setSize(300, 300);
        dialog.setLayout(new GridLayout(3,1));
        timerResult.setHorizontalAlignment(SwingConstants.CENTER);
        dialog.add(timerResult);
        timerResult.setVisible(true);
        normalRabbitsResult.setHorizontalAlignment(SwingConstants.CENTER);
        dialog.add(normalRabbitsResult);
        normalRabbitsResult.setVisible(true);
        whiteRabbitsResult.setHorizontalAlignment(SwingConstants.CENTER);
        dialog.add(whiteRabbitsResult);
        whiteRabbitsResult.setVisible(true);
        dialog.setVisible(true);
        factory.destroy();
        rabbits.clear();
        time = 0;

        rabbitsAmount.setText("Количество рожденных кроликов: " + factory.getAmountOfBirth());
        timer.setText("Таймер: " + time);
        drawRabbit.repaint();
    }
    
    public void timerVisibility(Boolean isShown){//скрыть таймер
        if (isShown)
            timer.setVisible(false);
        else
            timer.setVisible(true);
    }

    void update() {//продолжить симуляцию
        time++;
        timer.setText("Таймер: " + time);
        Random coordinatesRandom = new Random();
        int xCoordinate = coordinatesRandom.nextInt(drawRabbit.getWidth()-100);
        int yCoordinate = coordinatesRandom.nextInt(drawRabbit.getHeight()-100);
        Point coordinates  = new Point(xCoordinate, yCoordinate);
        try{
            BaseRabbit rabbit = factory.birth(time, coordinates);
            rabbitsAmount.setText("Количество рожденных кроликов: " + factory.getAmountOfBirth());
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

