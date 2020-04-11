package term4;

public abstract class BaseAI extends Thread {
    protected boolean isMoving;
    protected Thread thread;

    public synchronized void startAI() {}
    public void stopAI() {}
    public void setTheadPriority(int priority){}
}
