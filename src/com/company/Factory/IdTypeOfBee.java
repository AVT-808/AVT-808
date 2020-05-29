package com.company.Factory;

import com.company.Panels.ComboBoxProbability;
import com.company.Panels.MenuButtons;
import com.company.Panels.TimeOfBirth;

import java.io.IOException;
import java.util.Random;

public class IdTypeOfBee {

    private int allBeesBirth;
    private int allDronesBirth;
    private int allWorkersBirth;
    private Double probabilityOfWorkers;
    private Double percent;
    private Integer birthTimeWorkers;
    private Integer birthTimeDrones;

    public IdTypeOfBee(){
        allBeesBirth = 0;
        allDronesBirth = 0;
        allWorkersBirth = 0;
    }
    public int getAmountOfBirth(){
        return allBeesBirth;
    }

    public void destroy() {
        allBeesBirth = 0;
        allDronesBirth = 0;
        allWorkersBirth = 0;
    }
    public int type_of_birth(int x, int y, int time, MenuButtons menuButtons, Integer id) throws IOException {


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
                allBeesBirth++;
                allWorkersBirth++;
                return 1;
            }
        }

        if (time % this.birthTimeDrones == 0) {
            if(allDronesBirth < allWorkersBirth*percent) {
                allBeesBirth++;
                allDronesBirth++;
                return 2;
            }
        }
        return 0;
    }

}
