package com.company.Habitat;

import com.company.Factory.AbstractFactory;
import com.company.Factory.ConcreteFactory;
import com.company.Habitat.BeesArray.Singleton;
import com.company.Models.Abstract.BaseBee;
import com.company.Models.Worker;
import com.company.Panels.*;
import com.company.Serialization.DataFile;

import javax.swing.*;


import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import java.util.TreeMap;

public class Habitat extends JFrame {

    private final Singleton bees;



    private static int time;
    private AbstractFactory factory;
    public static DrawBee drawBee;
    private MenuPanel menuPanel;
    private Buttons button;
    private MenuButtons menuButtons;
    Integer id;
    private int allDronesBirth = 0;
    private int allWorkersBirth = 0;


    JButton startButton;
    JButton stopButton;
    JButton consoleButton;
    JButton button_save;
    JButton button_load;

    public Habitat(){


        int width = 1500;
        int height = 800;
        setSize(width, height);
        setResizable(false);

        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                DataFile.ExitApplication();
            }
        });

        bees = Singleton.getInstance();
        this.time = 0;

        factory = new ConcreteFactory();

        menuPanel = new MenuPanel();
        menuPanel.setMaximumSize(new Dimension(width,50));
        drawBee = new DrawBee();
        drawBee.setBounds(0,21, width, height -21);
        bees.setPanel(drawBee);
        menuButtons = new MenuButtons(this);
        menuButtons.setMaximumSize(new Dimension(width, 90));
        button = new Buttons(this);
        button.setMaximumSize(new Dimension(75,50));

        startButton = button.returnStart();
        stopButton = button.returnStop();
        consoleButton = button.returnConsoleButton();
        button_save = button.returnSbutton();
        button_load = button.returnLbutton();


        menuButtons.add(button);
        add(drawBee);
        add(menuPanel);
        add(menuButtons);
        setFocusable(true);
    }

    public static int getTime() {
        return time;
    }
    public JButton returnStart(){
        return startButton;
    }

    public JButton returnStop() {
        return stopButton;
    }
    public JButton returnConsoleButton() {
        return consoleButton;
    }

    public JButton returnSbutton() {
        return button_save;
    }
    public JButton returnLbutton() {
        return button_load;
    }


    public void stop(){//остановить отрисовку среды

        Boolean b = true;
        Boolean bl = menuButtons.return_chb();

        if (bl) {
            Information informationPanel = new Information(this, "Информация", time, allWorkersBirth, allDronesBirth);
            informationPanel.setVisible(true);
            b = informationPanel.return_B();
        }

        if(b) {
            time = 0;
            allWorkersBirth=0;
            allDronesBirth = 0;
            bees.clear();
            drawBee.repaint();
            factory.destroy();
            bees.destroy_hashSet();
            bees.destroy_treeMap();
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
        Point coordinates  = new Point(x_cord, y_cord);
        requestFocus();//фокус на поле

        try{
            id = coordinatesRandom.nextInt(100);
            BaseBee bee = (BaseBee) factory.birth(coordinates,time, menuButtons,id);
            menuPanel.setBeesAmount(factory.getAmountOfBirth());
            if(bee != null) {
                if(bee.getClass() == Worker.class){
                    allWorkersBirth++;
                }else {allDronesBirth++;}
                bees.addBees(bee);
                while (bees.hashSet.contains(id)) { //  Если элемент найден, он возвращает true, в противном случае false
                    id = coordinatesRandom.nextInt(100); // Делаем для того, чтобы номера не повторились
                }
                bees.addId(id); // Добавить ключ
                bees.putTreeMap(id,time);
                drawBee.repaint();
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        for(BaseBee bee: Singleton.getInstance().getBees()){
            if(bee.dead<time){
            Integer k = bee.id;
                bees.removeHashSet(k);
                bees.removeBees(bee);
                bees.removeTreeMap(k);
                drawBee.repaint();
                break;

            }
        }
    }
    public TreeMap<Integer,Integer> returnTreeMap(){
    return bees.returnTreeMap();
    }
}