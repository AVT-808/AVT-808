package lab.habitat.ai;

import lab.habitat.BaseAI;
import lab.habitat.Habitat;
import lab.habitat.ICreature;
import lab.habitat.creatures.birds.BigBird;

import java.util.Timer;
import java.util.TimerTask;


public class BigBirdAI extends BaseAI {
    private Habitat habitat;
    private Timer changeTargetTimer;


    public BigBirdAI(Habitat h) {
        habitat = h;
    }

    public synchronized void run() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                update();
            }
        }, 0, 200);
        changeTargetTimer = new Timer();
        changeTargetTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                changeTarget();
            }
        }, 0, 5000);

    }

    protected void update() {
        if(!active)
            return;

        for(ICreature c : habitat.getCreaturesByType(BigBird.class)) {
            if(c.getTargetX() != targetX && c.getTargetY() != targetY) {
                double dx = c.step() * Math.signum(targetX - c.getX());
                double dy = c.step() * Math.signum(targetY - c.getY());
                c.setX(c.getX() + dx);
                c.setY(c.getY() + dy);
            }
        }
    }

    protected void changeTarget() {
        targetX = Math.random();
    }
}
