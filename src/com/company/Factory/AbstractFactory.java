package com.company.Factory;

import com.company.Models.Abstract.BaseBee;


import java.io.IOException;

public interface AbstractFactory {
    int getAmountOfBirth();
    void destroy();
    BaseBee birth(int x_cord, int y_cord, int time)throws IOException;

}
