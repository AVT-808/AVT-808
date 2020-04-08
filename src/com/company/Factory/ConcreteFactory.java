package com.company.Factory;

import com.company.Models.Abstract.BaseBee;
import com.company.Models.Drone;
import com.company.Models.Worker;

import java.io.IOException;
import java.util.Random;

public class ConcreteFactory implements AbstractFactory {

    int timeBirthDrone;
    int timeBirthWorker;
    double probabilityJoWorker;
    double percentAllBees;
    int allBeesBirth;
    int allDronesBirth;

    public ConcreteFactory(int timeBirthDrone,int timeBirthWorker, double probabilityJoWorker,double percentAllBees){
        this.timeBirthDrone = timeBirthDrone;
        this.timeBirthWorker = timeBirthWorker;
        this.probabilityJoWorker  = probabilityJoWorker;
        this.percentAllBees = percentAllBees;
    }

    @Override
    public BaseBee birth(int x, int y, int time) throws IOException {
        if(time%timeBirthDrone == 0 && allDronesBirth<percentAllBees*allBeesBirth){
            BaseBee baseBee = new Drone(x,y);
            allDronesBirth++;
            allBeesBirth++;
            return baseBee;
        }
        if(time%timeBirthWorker == 0){
            Random random = new Random();
            double randomProbability = random.nextDouble();
            if(probabilityJoWorker > randomProbability){
                BaseBee baseBee = new Worker(x,y);
                allBeesBirth++;
                return baseBee;
            }
        }
        return null;
    }

    @Override
    public int getAmountOfBirth() {
        return allBeesBirth;
    }

    @Override
    public void destroy() {
        allBeesBirth = 0;
        allDronesBirth = 0;
    }


}
