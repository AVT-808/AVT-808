
package com.company.Models.Abstract;

import com.company.Models.Behaviour.IBehaviour;

import java.awt.*;
import java.io.Serializable;

public abstract class BaseBee implements IBehaviour, Serializable {

    public Point place;

    transient public Image beeImage;
    public Integer id;
    public Integer timeOfBirth;
    public Integer dead;

    protected BaseBee(Point place) {
      /*  this.x = x;
        this.y = y;*/
        this.place = place;
    }


    @Override
    public Point getPlace() {
        return place;
    }

    @Override
    public Image getBeeImage() {
        return beeImage;
    }

}
