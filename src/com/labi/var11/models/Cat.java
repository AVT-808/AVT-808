package com.labi.var11.models;

import com.labi.var11.models.abstracts.BaseAnimal;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Cat extends BaseAnimal {

    public Cat(Integer xCord, Integer yCord) throws IOException {
        super(xCord, yCord);
        this.animalImage = ImageIO.read(getClass().getResource("/cat.png"));
    }
}
