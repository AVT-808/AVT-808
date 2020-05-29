package com.company.Factory;

import com.company.Models.Drone;
import com.company.Models.Worker;
import com.company.Panels.LifeTimeOfBees;
import com.company.Panels.MenuButtons;

import java.io.IOException;

public class ConcreteFactory implements AbstractFactory {


    IdTypeOfBee idTypeOfBee;


    public ConcreteFactory() {
        idTypeOfBee = new IdTypeOfBee();

    }
    @Override
    public int getAmountOfBirth () {
        return idTypeOfBee.getAmountOfBirth();
    }

    @Override
    public void destroy () {
        idTypeOfBee.destroy();
    }

    @Override
    public Object birth(int x, int y, int time, MenuButtons menuButtons, Integer id) throws IOException {

        int idType = idTypeOfBee.type_of_birth(x, y, time, menuButtons, id);

        if (idType == 1) {

            return new Worker(x, y, id, time,com.company.Panels.LifeTimeOfBees.return_life_time_Workers());

        } else {

            if (idType == 2) {
                return new Drone(x, y, id, time, com.company.Panels.LifeTimeOfBees.return_life_time_Drones());
            } else return null;
        }

    }

}