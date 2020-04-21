package com.company.BaseAI;


public abstract class BaseAI implements Runnable {
    protected int priority = 1;
    protected boolean flag;
    protected Thread t;
    public void run() {}
    public void stopAI() {}
    public synchronized void  startAI() {}
    public void setPriority(int priority) {}
    public int getPriority(){return priority;}
}