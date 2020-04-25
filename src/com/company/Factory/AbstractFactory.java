package com.company.Factory;

import com.company.Models.Abstract.BaseBee;
import com.company.Panels.MenuButtons;


import java.io.IOException;

public interface AbstractFactory {
    int getAmountOfBirth();
    void destroy();
    BaseBee birth(int x_cord, int y_cord, int time, MenuButtons menuButtons)throws IOException;
    int getAmountOfDrones();
    int getAmountOfWorkers();

}

