package Move;

import Array.Singleton;
import Object.*;

public class BigAI extends BaseAI {

    public static Thread thread;
    public Boolean isMoving;

    public BigAI() {
        thread = new Thread(this, "BaseBigAI");
        isMoving = true;
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {

                while (!isMoving) { // Засыпание/пробуждение потока BaseBigAI
                    try {
                        //System.out.printf("%s Ждет... \n", Thread.currentThread().getName());
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Singleton bird_ss = Singleton.getM(); // Выполняется для каждого существующего объекта
                if (!bird_ss.Bird_s.isEmpty()) {
                    for (Bird bird : Singleton.getBird_s()) {
                        if (bird.getClass() == Big.class)
                            bird.move(); // Метод добавлен в классе Big
                    }
                }

                try {
                    Thread.sleep(100); //  Приостановить выполнение текущего потока на указанное в миллисекундах время
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
