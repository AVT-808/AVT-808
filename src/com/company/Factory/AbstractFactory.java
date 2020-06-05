package com.company.Factory;

import com.company.Panels.MenuButtons;


import java.awt.*;
import java.io.IOException;

public interface AbstractFactory {
    int getAmountOfBirth();
    void destroy();
    Object birth(Point place, int time, MenuButtons menuButtons, Integer id)throws IOException;
   // int getAmountOfDrones();
   // int getAmountOfWorkers();

}

