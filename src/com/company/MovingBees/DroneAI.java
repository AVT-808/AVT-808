package com.company.MovingBees;

import com.company.Habitat.BeesArray.Singleton;
import com.company.Models.Abstract.BaseBee;
import com.company.Models.Drone;
import com.company.Serialization.DataFile;

public class DroneAI extends BaseAI {
    public static Thread thread;
    public Boolean isMoving;

    public DroneAI() {
        thread = new Thread(this, "SmallAI");
        isMoving = true;
        thread.setPriority(DataFile.bufferInt[7]);

        thread.start();
    }

    @Override
    public void run() {
        int tick = 0;
        while (true){
            synchronized (this){

                while (!isMoving){
                    try {
                        System.out.printf("%s Ждет... \n", Thread.currentThread().getName());
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                
                Singleton bees_s = Singleton.getInstance();
                if (!bees_s.bees.isEmpty()) {
                    for (BaseBee bee : Singleton.getInstance().getBees()) {
                        if (bee.getClass() == Drone.class){
                            if(tick%40==0){
                                ((Drone) bee).Bugalteria();
                            }
                            bee.moving();
                        }

                    }
                }
                tick++;

                try {
                    Singleton.getInstance().update();
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public synchronized void startAI() {
        isMoving = true;
        notify();
    }

    @Override
    public void stopAI() {
        isMoving = false;
    }

    public static Thread getThread() {
        return thread;
    }

}
