
package com.company.Models.Abstract;

import com.company.Models.Behaviour.IBehaviour;

import java.awt.*;

public abstract class BaseBee implements IBehaviour {
    private int x;
    private int y;

    protected Image beeImage;
    public Integer id;
    public Integer timeOfBirth;
    public Integer dead;

    protected BaseBee(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public Image getBeeImage() {
        return beeImage;
    }

    @Override
    public void setBeeImage(Image beeImage) {
        this.beeImage = beeImage;
    }
}
