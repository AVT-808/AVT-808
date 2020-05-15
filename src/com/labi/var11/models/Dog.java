package com.labi.var11.models;

import com.labi.var11.models.abstracts.BaseAnimal;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Dog extends BaseAnimal {

    public Dog(Integer xCord, Integer yCord) throws IOException {
        super(xCord, yCord);
        this.animalImage = ImageIO.read(getClass().getResource("/dog.png"));
    }
}
