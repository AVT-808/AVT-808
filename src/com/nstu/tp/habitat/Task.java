package com.nstu.tp.habitat;



import java.util.TimerTask;

public class Task extends TimerTask {
    private final Habitat habitat;

    public Task(Habitat habitat){
        this.habitat = habitat;
    }

    @Override
    public void run() {
        habitat.update();
    }
}
