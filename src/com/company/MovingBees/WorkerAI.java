package com.company.MovingBees;

import com.company.Habitat.BeesArray.Singleton;
import com.company.Models.Abstract.BaseBee;
import com.company.Models.Worker;
import com.company.Serialization.DataFile;

public class WorkerAI extends BaseAI {
    public static Thread thread;
    public Boolean isMoving;

    public WorkerAI() {
        thread = new Thread(this, "BaseAI");
        isMoving = true;
        thread.setPriority(DataFile.bufferInt[6]);

        thread.start();

    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {

                while (!isMoving) { // Засыпание/пробуждение потока BaseAI
                    try {

                        System.out.printf("%s Ждет... \n", Thread.currentThread().getName());
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Singleton bees_s = Singleton.getInstance(); // Выполняется для каждого существующего объекта
                if (!bees_s.bees.isEmpty()) {
                    for (BaseBee bee : Singleton.getInstance().getBees()) {
                        if (bee.getClass() == Worker.class)
                            bee.moving(); // Метод добавлен в классе Worker
                    }
                }

                try {
                    Singleton.getInstance().update();
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

