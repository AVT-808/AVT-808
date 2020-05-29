package com.company.Habitat.BeesArray;

import com.company.Habitat.Habitat;
import com.company.Habitat.HabitatTask;
import com.company.Models.Abstract.BaseBee;

import javax.swing.*;
import java.util.*;
import java.util.Timer;


public class Singleton {

    private static Singleton instance;
    public final List<BaseBee> bees;
    public HashSet<Integer> hashSet;
    TreeMap<Integer,Integer> treeMap;
    private Timer timer;
    private HabitatTask habitatTask;
    Boolean isStart = true;

    public Singleton() {
        bees = new ArrayList<>();
        hashSet = new HashSet<>();
        treeMap = new TreeMap<>();
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

    public void destroy_hashSet() {
        instance.hashSet.clear();
    }

    public void destroy_treeMap(){
        instance.treeMap.clear();
    }

    public void removeBees(BaseBee bee){
        bees.remove(bee);
    }

    public void removeHashSet(Integer k){
        instance.hashSet.remove(k);
    }
    public void removeTreeMap(Integer k){
        instance.treeMap.remove(k);
    }

    public void addBees(BaseBee bee) {
        instance.bees.add(bee);
    }

    public void addId(Integer id){
        instance.hashSet.add(id);
    };

    public void putTreeMap(Integer id, Integer dead){
        instance.treeMap.put(id,dead);
    }

    public static List<BaseBee> getBees(){
        return instance.bees;
    }

    public TreeMap<Integer, Integer> returnTreeMap() { return treeMap; }



    //сделать текущий объект CurrentObj
    
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
