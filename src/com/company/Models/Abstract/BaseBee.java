
package com.company.Models.Abstract;

import com.company.Models.Behaviour.IBehaviour;

import java.awt.*;

public abstract class BaseBee implements IBehaviour {
    /*protected int x;
    protected int y;*/
    public Point place;

    protected Image beeImage;
    public Integer id;
    public Integer timeOfBirth;
    public Integer dead;

    protected BaseBee(Point place) {
      /*  this.x = x;
        this.y = y;*/
        this.place = place;
    }

    /*@Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }*/
    @Override
    public Point getPlace() {
        return place;
    }

    @Override
    public Image getBeeImage() {
        return beeImage;
    }

}
