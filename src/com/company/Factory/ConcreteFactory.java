package com.company.Factory;

import com.company.Models.Abstract.BaseBee;
import com.company.Models.Drone;
import com.company.Models.Worker;
import com.company.Panels.ComboBoxProbability;
import com.company.Panels.MenuButtons;
import com.company.Panels.TimeOfBirth;

import java.io.IOException;
import java.util.Random;

public class ConcreteFactory implements AbstractFactory {


    private int allBeesBirth;
    private int allDronesBirth;
    private int allWorkersBirth;
    private Double probabilityOfWorkers;
    private Double percent;
    private Integer birthTimeWorkers;
    private Integer birthTimeDrones;


    public ConcreteFactory(){
        allBeesBirth = 0;
        allDronesBirth = 0;
        allWorkersBirth = 0;
    }

    @Override
    public BaseBee birth(int x, int y, int time,MenuButtons menuButtons) throws IOException {

        ComboBoxProbability comboBoxProbability = menuButtons.return_comboBox();
        this.probabilityOfWorkers = comboBoxProbability.return_probabilityOfWorkers();
        this.percent = comboBoxProbability.return_percent();

        TimeOfBirth timeOfBirth = menuButtons.return_timerOfBirth();
        this.birthTimeWorkers = timeOfBirth.return_birthTimeWorkers();
        this.birthTimeDrones = timeOfBirth.return_birthTimeDrones();



        if (time % this.birthTimeWorkers == 0){ // % -  остаток от деления. Т.о. каждые n секунд

            Random random = new Random();
            double randomProbability = random.nextDouble(); // float nextFloat() - возвращает следующее случайное значение типа float

            if (randomProbability <= probabilityOfWorkers) {
                BaseBee baseBee = new Worker(x, y);
                allBeesBirth++;
                allWorkersBirth++;
                return baseBee;
            }
        }

        if (time % this.birthTimeDrones == 0) {
            if(allDronesBirth < allWorkersBirth*percent) {
                BaseBee baseBee = new Drone(x, y);
                allBeesBirth++;
                allDronesBirth++;
                return baseBee;
            }
        }
        return null;
    }

    @Override
    public int getAmountOfDrones() {
        return allDronesBirth;
    }

    @Override
    public int getAmountOfWorkers() {
        return allWorkersBirth;
    }

    @Override
    public int getAmountOfBirth() {
        return allBeesBirth;
    }

    @Override
    public void destroy() {
        allBeesBirth = 0;
        allDronesBirth = 0;
        allWorkersBirth = 0;
    }
}

