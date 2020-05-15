package com.labi.var11.models.abstracts;

import java.awt.*;

public abstract class BaseAnimal {
    private Integer xCord;
    private Integer yCord;
    protected Image animalImage;

    protected BaseAnimal(Integer xCord, Integer yCord){
        this.xCord=xCord;
        this.yCord=yCord;
    }

    public Integer getX() {
        return xCord;
    }

    public void setX(Integer xCord) {
        this.xCord = xCord;
    }

    public Integer getY() {
        return yCord;
    }

    public void setY(Integer yCord) {
        this.yCord = yCord;
    }

    public Image getAnimalImage() {
        return animalImage;
    }
}

