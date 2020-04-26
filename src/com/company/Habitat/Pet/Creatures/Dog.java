package com.company.Habitat.Pet.Creatures;

import com.company.Habitat.Pet.Pet;
import com.company.Single.Singleton;

import java.io.Serializable;

public class Dog  extends Pet implements Serializable {

    public Dog(int id, int birth) {
        super(id,birth);
    }
    private int speed = 4;
    private int stay;
    private int go = 2000;
    private int targetX;
    private int targetY;
    @Override
    public void move() {
        for(int i = 0; i< Singleton.getInstance().sizeArray(); i++){
            if(Singleton.getInstance().Get(i) instanceof Cat){
                stay = Singleton.getInstance().Get(i).getX() + getY() +Singleton.getInstance().Get(i).getY() + getY();
                if(stay < go ){
                    targetX = Singleton.getInstance().Get(i).getX();
                    targetY = Singleton.getInstance().Get(i).getY();
                }
            }
        }
        if(getX() > targetX){
            setX(getX()-speed);
        }
        if(getX() < targetX){
            setX(getX()+speed);
        }
        if(getY() > targetY){
            setY(getY()-speed);
        }
        if(getY() < targetY){
            setY(getY()+speed);
        }

    }
}
