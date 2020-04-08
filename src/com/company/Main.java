package com.company;

import com.company.Habitat.Habitat;
import com.company.Habitat.HabitatTask;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;

public class Main extends KeyAdapter {

    private Timer timer;
    private HabitatTask habitatTask;
    private final Habitat habitat;
    private Boolean isStarted;
    private Boolean isShown;

    private Main() {
        habitat = new Habitat("Habitat", 2,2,0.9f,0.9f);
        habitat.setVisible(true);
        habitat.addKeyListener(this);
        isStarted = false;
        isShown = true;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_B && !isStarted){
            isStarted = true;
            timer = new Timer();
            habitatTask = new HabitatTask(habitat);
            timer.schedule(habitatTask,0,1000);
        }
        if (key == KeyEvent.VK_E && isStarted) {
            timer.cancel();
            habitatTask.cancel();
            timer.purge();
            habitat.stop();
            isStarted = false;
        }
        if(key == KeyEvent.VK_T) {
            isShown = !isShown;
            habitat.timerVisibility(isShown);
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
