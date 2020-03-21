package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyMenuBar extends MenuBar {
    MyMenuBar(GUI myGUI){
        MenuBar newMenu = new MenuBar();
        Menu menu = new Menu("Menu");
        add(menu);
        MenuItem runTimer = new MenuItem("Run");
        MenuItem stopTimer = new MenuItem("Stop");
        MenuItem endApp = new MenuItem("Shut down");
        menu.add(runTimer);
        menu.add(stopTimer);
        menu.add(new MenuItem("-"));
        menu.add(endApp);


        Menu settings = new Menu("Settings");
        add(settings);
        MenuItem hideOrShowTime = new MenuItem("Timer On/Off");
        MenuItem showInfoWhenPressStop = new MenuItem("Show info when press stop");
        Menu selectSpawnChanceCats = new Menu("Spawn chance cats");
        MenuItem cats0 = new MenuItem("0%");
        MenuItem cats1 = new MenuItem("10%");
        MenuItem cats2 = new MenuItem("20%");
        MenuItem cats3 = new MenuItem("30%");
        MenuItem cats4 = new MenuItem("40%");
        MenuItem cats5 = new MenuItem("50%");
        MenuItem cats6 = new MenuItem("60%");
        MenuItem cats7 = new MenuItem("70%");
        MenuItem cats8 = new MenuItem("80%");
        MenuItem cats9 = new MenuItem("90%");
        MenuItem cats10 = new MenuItem("100%");
        selectSpawnChanceCats.add(cats0);
        selectSpawnChanceCats.add(cats1);
        selectSpawnChanceCats.add(cats2);
        selectSpawnChanceCats.add(cats3);
        selectSpawnChanceCats.add(cats4);
        selectSpawnChanceCats.add(cats5);
        selectSpawnChanceCats.add(cats6);
        selectSpawnChanceCats.add(cats7);
        selectSpawnChanceCats.add(cats8);
        selectSpawnChanceCats.add(cats9);
        selectSpawnChanceCats.add(cats10);
        Menu selectSpawnChanceDogs = new Menu("Spawn chance dogs");
        MenuItem dogs0 = new MenuItem("0%");
        MenuItem dogs1 = new MenuItem("10%");
        MenuItem dogs2 = new MenuItem("20%");
        MenuItem dogs3 = new MenuItem("30%");
        MenuItem dogs4 = new MenuItem("40%");
        MenuItem dogs5 = new MenuItem("50%");
        MenuItem dogs6 = new MenuItem("60%");
        MenuItem dogs7 = new MenuItem("70%");
        MenuItem dogs8 = new MenuItem("80%");
        MenuItem dogs9 = new MenuItem("90%");
        MenuItem dogs10 = new MenuItem("100%");
        selectSpawnChanceDogs.add(dogs0);
        selectSpawnChanceDogs.add(dogs1);
        selectSpawnChanceDogs.add(dogs2);
        selectSpawnChanceDogs.add(dogs3);
        selectSpawnChanceDogs.add(dogs4);
        selectSpawnChanceDogs.add(dogs5);
        selectSpawnChanceDogs.add(dogs6);
        selectSpawnChanceDogs.add(dogs7);
        selectSpawnChanceDogs.add(dogs8);
        selectSpawnChanceDogs.add(dogs9);
        selectSpawnChanceDogs.add(dogs10);
        Menu selectSpawnChancePets = new Menu("Spawn chance");
        selectSpawnChancePets.add(selectSpawnChanceCats);
        selectSpawnChancePets.add(selectSpawnChanceDogs);

        MenuItem changeSpawnCats = new MenuItem("Change spawn time cats");
        MenuItem changeSpawnDogs = new MenuItem("Change spawn time dogs");
        Menu spawnTime = new Menu("Change spawn time");
        spawnTime.add(changeSpawnCats);
        spawnTime.add(changeSpawnDogs);
        MenuItem lifeTimeCats = new MenuItem("Select life time of cats");
        MenuItem lifeTimeDogs = new MenuItem("Select life time of dogs");
        Menu lifeTime = new Menu("Select life time");
        lifeTime.add(lifeTimeCats);
        lifeTime.add(lifeTimeDogs);



        settings.add(hideOrShowTime);
        settings.add(showInfoWhenPressStop);
        settings.add(new MenuItem("-"));
        settings.add(selectSpawnChancePets);
        settings.add(new MenuItem("-"));
        settings.add(spawnTime);
        settings.add(new MenuItem("-"));
        settings.add(lifeTime);



        runTimer.addActionListener(e -> myGUI.run.doClick());
        stopTimer.addActionListener(e -> myGUI.pause.doClick());
        hideOrShowTime.addActionListener(e -> {
            myGUI.timeVisible = !myGUI.timeVisible;
            if(myGUI.timeVisible){
                myGUI.showTime.setSelected(true);
                myGUI.hideTime.setSelected(false);
            }else{
                myGUI.showTime.setSelected(false);
                myGUI.hideTime.setSelected(true);
            }

        });
        showInfoWhenPressStop.addActionListener(e -> {
            myGUI.useDialog = !myGUI.useDialog;
            myGUI.screenResolution.setSelected(!myGUI.useDialog);
        });
        endApp.addActionListener(e -> {
            myGUI.timerTask.StopWork();
            myGUI.useKeys = false;

            myGUI.timeVisible = false;
            myGUI.endApp = true;
            myGUI.lineVisible = false;
            myGUI.selectTime.setVisible(false);
            myGUI.showTime.setVisible(false);
            myGUI.hideTime.setVisible(false);
            myGUI.run.setVisible(false);
            myGUI.pause.setVisible(false);
            myGUI.screenResolution.setVisible(false);
            myGUI.selectCatsTimeSpawn.setVisible(false);
            myGUI.selectDogsTimeSpawn.setVisible(false);
            myGUI.selectCatsSpawn.setVisible(false);
            myGUI.selectDogsSpawn.setVisible(false);
            myGUI.spawnCats.setVisible(false);
            myGUI.spawnDogs.setVisible(false);
            myGUI.catsComboBox.setVisible(false);
            myGUI.dogsComboBox.setVisible(false);
            myGUI.window.allClear();

            myGUI.repaint();
        });
        cats0.addActionListener(e -> {
            myGUI.window.setP1(0);
            myGUI.catsComboBox.setSelectedIndex(0);
        });
        cats1.addActionListener(e -> {
            myGUI.window.setP1(10);
            myGUI.catsComboBox.setSelectedIndex(1);
        });
        cats2.addActionListener(e -> {
            myGUI.window.setP1(20);
            myGUI.catsComboBox.setSelectedIndex(2);
        });
        cats3.addActionListener(e -> {
            myGUI.window.setP1(30);
            myGUI.catsComboBox.setSelectedIndex(3);
        });
        cats4.addActionListener(e -> {
            myGUI.window.setP1(40);
            myGUI.catsComboBox.setSelectedIndex(4);
        });
        cats5.addActionListener(e -> {
            myGUI.window.setP1(50);
            myGUI.catsComboBox.setSelectedIndex(5);
        });
        cats6.addActionListener(e -> {
            myGUI.window.setP1(60);
            myGUI.catsComboBox.setSelectedIndex(6);
        });
        cats7.addActionListener(e -> {
            myGUI.window.setP1(70);
            myGUI.catsComboBox.setSelectedIndex(7);
        });
        cats8.addActionListener(e -> {
            myGUI.window.setP1(80);
            myGUI.catsComboBox.setSelectedIndex(8);
        });
        cats9.addActionListener(e -> {
            myGUI.window.setP1(90);
            myGUI.catsComboBox.setSelectedIndex(9);
        });
        cats10.addActionListener(e -> {
            myGUI.window.setP1(100);
            myGUI.catsComboBox.setSelectedIndex(10);
        });
        dogs0.addActionListener(e -> {
            myGUI.window.setP2(0);
            myGUI.dogsComboBox.setSelectedIndex(0);
        });
        dogs1.addActionListener(e -> {
            myGUI.window.setP2(10);
            myGUI.dogsComboBox.setSelectedIndex(1);
        });
        dogs2.addActionListener(e -> {
            myGUI.window.setP2(20);
            myGUI.dogsComboBox.setSelectedIndex(2);
        });
        dogs3.addActionListener(e -> {
            myGUI.window.setP2(30);
            myGUI.dogsComboBox.setSelectedIndex(3);
        });
        dogs4.addActionListener(e -> {
            myGUI.window.setP2(40);
            myGUI.dogsComboBox.setSelectedIndex(4);
        });
        dogs5.addActionListener(e -> {
            myGUI.window.setP2(50);
            myGUI.dogsComboBox.setSelectedIndex(5);
        });
        dogs6.addActionListener(e -> {
            myGUI.window.setP2(60);
            myGUI.dogsComboBox.setSelectedIndex(6);
        });
        dogs7.addActionListener(e -> {
            myGUI.window.setP2(70);
            myGUI.dogsComboBox.setSelectedIndex(7);
        });
        dogs8.addActionListener(e -> {
            myGUI.window.setP2(80);
            myGUI.dogsComboBox.setSelectedIndex(8);
        });
        dogs9.addActionListener(e -> {
            myGUI.window.setP2(90);
            myGUI.dogsComboBox.setSelectedIndex(9);
        });
        dogs10.addActionListener(e -> {
            myGUI.window.setP2(100);
            myGUI.dogsComboBox.setSelectedIndex(10);
        });
        changeSpawnCats.addActionListener(e -> {
            String result = JOptionPane.showInputDialog(
                    null,
                    "Введите период рождения.","Рождение котов",JOptionPane.INFORMATION_MESSAGE);
            try{
                myGUI.window.setN1(Integer.parseInt(result));
                myGUI.spawnCats.setText(String.valueOf(myGUI.window.getN1()));
            }catch (Throwable number){
                JOptionPane.showMessageDialog(null, "Ввод некорректного значения!!!","Error",JOptionPane.ERROR_MESSAGE);
            }
        });
        changeSpawnDogs.addActionListener(e -> {
            String result = JOptionPane.showInputDialog(
                    null,
                    "Введите период рождения.","Рождение собак",JOptionPane.INFORMATION_MESSAGE);
            try{
                myGUI.window.setN2(Integer.parseInt(result));
                myGUI.spawnDogs.setText(String.valueOf(myGUI.window.getN2()));
            }catch (Throwable number){
                JOptionPane.showMessageDialog(null, "Ввод некорректного значения!!!","Error",JOptionPane.ERROR_MESSAGE);
            }
        });

        lifeTimeCats.addActionListener(e -> {
            String result = JOptionPane.showInputDialog(
                    null,
                    "Введите время жизни.","Жизнь котов",JOptionPane.INFORMATION_MESSAGE);
            try{
                myGUI.window.setCatsTimeOfLife(Integer.parseInt(result));
                myGUI.lifeCats.setText(String.valueOf(myGUI.window.getCatsTimeOfLife()));
            }catch (Throwable number){
                JOptionPane.showMessageDialog(null, "Ввод некорректного значения!!!","Error",JOptionPane.ERROR_MESSAGE);
            }
        });
        lifeTimeDogs.addActionListener(e -> {
            String result = JOptionPane.showInputDialog(
                    null,
                    "Введите время жизни.","Жизнь котов",JOptionPane.INFORMATION_MESSAGE);
            try{
                myGUI.window.setDogsTimeOfLife(Integer.parseInt(result));
                myGUI.lifeDogs.setText(String.valueOf(myGUI.window.getDogsTimeOfLife()));
            }catch (Throwable number){
                JOptionPane.showMessageDialog(null, "Ввод некорректного значения!!!","Error",JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
