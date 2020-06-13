package Move;

import Array.Singleton;
import Object.*;

public class SmallAI extends BaseAI {

    public static Thread thread;
    public Boolean isMoving;

    public SmallAI() {
        thread = new Thread(this, "SmallAI");
        isMoving = true;
        thread.start();
        ChangeCoord.T(); // Таймер, определяющий период и направление движения
    }

    @Override
    public void run() {
        while (true){
            synchronized (this){

                while (!isMoving){
                    try {
                       // System.out.printf("%s Ждет... \n", Thread.currentThread().getName());
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Singleton bird_ss = Singleton.getM();
                if (!bird_ss.Bird_s.isEmpty()) {
                    for (Bird bird : Singleton.getBird_s()) {
                        if (bird.getClass() == Small.class)
                            bird.move();
                    }
                }

                try {
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
    public void stopAI() { isMoving = false; }

    public static Thread getThread() {
        return thread;
    }

}
