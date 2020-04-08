package com.nstu.tp.habitat;

import com.nstu.tp.DrawFish.DrawFish;
import com.nstu.tp.factory.AbstractFactory;
import com.nstu.tp.factory.ConcreteFactory;
import com.nstu.tp.models.GoldFish;
import com.nstu.tp.models.abstracts.BaseFish;

import javax.swing.*;
import java.awt.*;


import java.util.Random;

public class Habitat extends JFrame {
        BaseFish fishy[];
        private final JLabel fishAmount;
        private final JLabel timer;
        private final AbstractFactory factory;
        private Integer i=0;
        private Integer Mass = 100;
        private Integer time;
        private final DrawFish drawFish;
        private Integer guppyFishAmount;
        private Integer goldFishAmount;


        public Habitat(String title, Integer goldFishBirth, Integer guppyFishBirth, Float goldFishBirthProbability, Float guppyFishBirthProbability) {
                super(title);
                int width = 1024;
                int height = 768;
                setSize(width, height);
                setResizable(false);
                setLayout(null);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.time = 0;
                fishy = new BaseFish[Mass];
                factory = new ConcreteFactory(goldFishBirth, guppyFishBirth, goldFishBirthProbability, guppyFishBirthProbability);
                fishAmount = new JLabel("Колличество рождённых рыбок:" + factory.getAmountOfBirth());
                timer = new JLabel("Время" + time);
                JPanel label = new JPanel();
                label.setLayout(new GridLayout(1, 2));
                label.add(fishAmount);
                label.add(timer);
                label.setBounds(0, 0, width, 20);
                drawFish = new DrawFish(fishy,Mass);
                drawFish.setBounds(0, 15, width, height - 15);
                add(label);
                add(drawFish);
                goldFishAmount = 0;
                guppyFishAmount = 0;

        }
        public void stop(){
                JLabel timerRes = new JLabel("Время симуляции: " + time);
                timerRes.setFont(new Font("Times New Roman", Font.ITALIC,18));
                timerRes.setForeground(Color.magenta);
                JLabel guppyFishResult = new JLabel("Количество гуппи рыбок: " + (factory.getGuppyFishAmountBirth()));
                guppyFishResult.setFont(new Font("courier new", Font.ITALIC,16));
                guppyFishResult.setForeground(Color.pink);
                JLabel goldFishResult = new JLabel("Количество золотых рыбок: " + (factory.getGoldFishAmountBirth()));
                goldFishResult.setFont(new Font("Comic Sans", Font.ITALIC,14));
                goldFishResult.setForeground(Color.orange);
                JDialog dialog = new JDialog(this, "Информация", true);
                dialog.setLayout(null);
                dialog.setResizable(false);
                dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                dialog.setSize(300, 300);
                dialog.setLayout(new GridLayout(3,1));
                timerRes.setHorizontalAlignment(SwingConstants.CENTER);
                dialog.add(timerRes);
                timerRes.setVisible(true);
                guppyFishResult.setHorizontalAlignment(SwingConstants.CENTER);
                dialog.add(guppyFishResult);
                guppyFishResult.setVisible(true);
                goldFishResult.setHorizontalAlignment(SwingConstants.CENTER);
                dialog.add(goldFishResult);
                goldFishResult.setVisible(true);
                dialog.setVisible(true);
                factory.destroy();
                for (int j=0;j<Mass;j++)
                {fishy[j]=null;}
                 i = 0;
                time = 0;

                fishAmount.setText("Количество рожденных рыбок: " + factory.getAmountOfBirth());
                timer.setText("Таймер: " + time);
                drawFish.repaint();

        }
        public void timerVis(Boolean isShown){//скрыть таймер
                if (isShown)
                        timer.setVisible(false);
                else
                        timer.setVisible(true);
        }

        void update() {//продолжить симуляцию
                time++;
                timer.setText("Таймер: " + time);
                Random coordinatesRandom = new Random();
                int xCoordinate = coordinatesRandom.nextInt(drawFish.getWidth()-100);
                int yCoordinate = coordinatesRandom.nextInt(drawFish.getHeight()-100);
                Point coordinates  = new Point(xCoordinate, yCoordinate);
                try{
                        BaseFish baseFish = factory.birth(time, coordinates);
                        fishAmount.setText("Количество рождённых рыбок: " + factory.getAmountOfBirth());

                        if(baseFish != null) {
                                if (baseFish.getClass() == GoldFish.class)
                                        goldFishAmount++;


                          fishy[i] = baseFish;
                          i++;
                                drawFish.repaint();
                        }

                }
                catch (Exception ex){
                        ex.printStackTrace();
                }
        }
}








