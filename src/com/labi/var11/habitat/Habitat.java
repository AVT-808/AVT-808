package com.labi.var11.habitat;

import com.labi.var11.factory.AbstractFactory;
import com.labi.var11.factory.AnimalFactory;
import com.labi.var11.models.abstracts.BaseAnimal;
import com.labi.var11.panels.DrawAnimalPanel;
import com.labi.var11.panels.InformationPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Habitat extends JFrame implements KeyListener {
    private AbstractFactory animalFactory;
    private Integer catGenTime;
    private Integer dogGenTime;
    private Double catGenProbability;
    private Double dogGenProbability;
    private Integer width;
    private Integer height;
    private Integer time;
    private List<BaseAnimal> animals;
    private DrawAnimalPanel drawAnimalPanel;
    private Boolean isStarted;
    private Timer t;
    private TimerTask tt;
    private InformationPanel informationPanel;
    private Integer animalAmount;
    private Boolean isVisible;


    public Habitat(Integer width, Integer height) {
        super("Лаба");
        this.width = width;
        this.height = height;
        setSize(width, height);
        setResizable(false);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);
        catGenTime = 3;
        dogGenTime = 4;
        catGenProbability = 0.9;
        dogGenProbability = 0.8;
        time = 0;
        animalFactory = new AnimalFactory();
        animals = new ArrayList<BaseAnimal>();
        drawAnimalPanel = new DrawAnimalPanel(animals);
        informationPanel = new InformationPanel();
        informationPanel.setMaximumSize(new Dimension(width, 20));
        drawAnimalPanel.setMaximumSize(new Dimension(width, height - 130));
        add(informationPanel);
        add(drawAnimalPanel);
        isStarted=false;
        animalAmount = 0;
        isVisible = true;
    }

    private void changeTimerVisibility(){
        isVisible =! isVisible;
        informationPanel.setTimerVisibility(isVisible);

    }


    private void stop(){
        time=0;
        informationPanel.setTimer(time);
        animals.clear();
        drawAnimalPanel.repaint();
        animalAmount = 0;
        informationPanel.setAnimalAmount(animalAmount);

    }

    private void update(){
        time++;
        informationPanel.setTimer(time);
        Random random = new Random();
        Integer xWidth = random.nextInt(width-100);
        Integer yHeight = random.nextInt(height-100);
        try {
            if (time % catGenTime == 0) {
                Double randomProbability = random.nextDouble();
                if (randomProbability < catGenProbability) {
                    BaseAnimal animal = animalFactory.birthCat(xWidth, yHeight);
                    animals.add(animal);
                    animalAmount++;
                    informationPanel.setAnimalAmount(animalAmount);
                    drawAnimalPanel.repaint();
                }
            }
            if (time % dogGenTime == 0) {
                Double randomProbability = random.nextDouble();
                if (randomProbability < dogGenProbability) {
                    BaseAnimal animal = animalFactory.birthDog(xWidth, yHeight);
                    animals.add(animal);
                    animalAmount++;
                    informationPanel.setAnimalAmount(animalAmount);
                    drawAnimalPanel.repaint();
                }
            }
        }
        catch (Exception exc){
            exc.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Habitat(1024,768);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_B && !isStarted) {
            isStarted = true;
            t = new Timer();
            tt = new TimerTask() {

                @Override
                public void run() {
                    update();
                }
            };

            t.schedule(tt,0,1000);
        }
        if (key == KeyEvent.VK_E && isStarted) {
            t.cancel();
            tt.cancel();
            t.purge();
            stop();
            isStarted = false;
        }
        if(key == KeyEvent.VK_T) {
            changeTimerVisibility();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}




