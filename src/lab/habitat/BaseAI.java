package lab.habitat;


import java.util.Timer;


public abstract class BaseAI extends Thread {
    protected static double targetX = 0.5;
    protected static double targetY = 0.5;
    protected Timer timer;
    protected boolean active = true;

    public void pause(boolean b) {
        active = !b;
    }
}
