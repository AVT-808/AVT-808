package com.nstu.tp.factory;

import com.nstu.tp.models.GoldFish;
import com.nstu.tp.models.abstracts.BaseFish;

import java.awt.*;
import java.io.IOException;

public interface AbstractFactory {
    Integer getAmountOfBirth();
    Integer getGoldFishAmountBirth();
    Integer getGuppyFishAmountBirth();
    BaseFish birth(Integer time, Point cords) throws IOException;



    void destroy(); //уничтожить скписок объектов



}
