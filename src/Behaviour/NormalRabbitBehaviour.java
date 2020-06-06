package Behaviour;

import Behaviour.Abstract.BaseAI;
import Habitat.RabbitList.Singleton;
import Models.NormalRabbit;

public class NormalRabbitBehaviour extends BaseAI {

    public NormalRabbitBehaviour(){
        start();
    }

    @Override
    public void run() {
        while (true){
            synchronized (this) {
                if(!isMoving) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                    for (var rabbit : Singleton.getRabbits()) {
                        if (rabbit instanceof NormalRabbit) {
                            rabbit.move();
                        }
                    }
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

            }
        }
    }

    @Override
    public synchronized void startRabbitBehaviour() {
        isMoving=true;
        notify();
    }

    @Override
    public void stopRabbitBehaviour() {
        isMoving = false;
    }

    @Override
    public void setTheadPriority(int priority) {
        setPriority(priority);
    }
}
