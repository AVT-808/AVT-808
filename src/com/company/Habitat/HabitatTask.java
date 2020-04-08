package com.company.Habitat;


import java.util.TimerTask;

public class HabitatTask extends TimerTask {
    private final Habitat habitat;

    public HabitatTask(Habitat habitat) {
        this.habitat = habitat;
    }

    @Override
    public void run() {
        habitat.update();
    }
}
