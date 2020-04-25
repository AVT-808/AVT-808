package com.company.Habitat.BeesArray;

import com.company.Habitat.Habitat;
import com.company.Habitat.HabitatTask;
import com.company.Models.Abstract.BaseBee;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TreeMap;

public class Singleton {

    private static Singleton instance;
    public final List<BaseBee> bees;
    private Timer timer;
    private HabitatTask habitatTask;
    Boolean isStart = true;

    public Singleton() {
        bees = new ArrayList<>();
    }

    public static Singleton getInstance(){
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void clear() {
        if (!instance.bees.isEmpty())
            instance.bees.clear();
    }

    public void addBees(BaseBee bee) {
        instance.bees.add(bee);
    }

    public static List<BaseBee> getBees(){
        return instance.bees;
    }
    
    public void Start(Habitat habitat, JButton startButton, JButton stopButton ){
        isStart = false;
        timer = new Timer();
        habitatTask = new HabitatTask(habitat);
        timer.schedule(habitatTask,0,1000);
        stopButton.setEnabled(true);
        startButton.setFocusable(false);
    }

    public void Stop(Habitat habitat, JButton startButton, JButton stopButton){
        isStart = true;
        timer.cancel();
        habitatTask.cancel();
        timer.purge();
        habitat.stop();
        stopButton.setEnabled(true);
        startButton.setFocusable(false);
    }

    public Boolean getIsStart(){
        return isStart;
    }
}
