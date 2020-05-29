package com.company.Factory;

import com.company.Panels.MenuButtons;


import java.io.IOException;

public interface AbstractFactory {
    int getAmountOfBirth();
    void destroy();
    Object birth(int x_cord, int y_cord, int time, MenuButtons menuButtons, Integer id)throws IOException;
   // int getAmountOfDrones();
   // int getAmountOfWorkers();

}

