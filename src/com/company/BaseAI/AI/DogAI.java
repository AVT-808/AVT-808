package com.company.BaseAI.AI;

import com.company.BaseAI.BaseAI;
import com.company.Habitat.Pet.Creatures.Dog;
import com.company.Single.Singleton;

public class DogAI extends BaseAI {
    public DogAI() {
        t=new Thread(this);
        flag= false;
        t.start();
    }

    public void run() {

        while (true) {
            try {
                synchronized (this) {
                    if (flag) {
                        wait();
                    }
                }
                for (int i = 0; i < Singleton.getInstance().sizeArray(); i++) {
                    if (Singleton.getInstance().Get(i) instanceof Dog) {
                        Singleton.getInstance().Get(i).move();
                    }
                }

                Thread.sleep(100);
            } catch (InterruptedException ie) {
            }
        }
    }
    public void stopAI() {
        flag = true;
    }
    public synchronized void  startAI() {
        flag = false;
        notify();
    }
    public boolean isFlag(){
        return flag;
    }
    public void setPriority(int priority){
        this.priority = priority;
    }
}
