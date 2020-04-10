package com.company.Habitat.Pet.Creatures;

import com.company.Habitat.Pet.Pet;
import com.company.Single.Singleton;

public class Cat extends Pet {
    private int speed = 10;



    private boolean isRight = false;
    private boolean isBottom = false;
    private boolean isLeft = false;
    private boolean isTop = false;
    public Cat(int id, int birth) {
        super(id,birth);
    }
    @Override
    public void move() {
        if (!isRight && !isBottom && !isLeft && !isTop) {
            if (getX() <= Singleton.getInstance().getWindow().getSizeX() - 80) {
                setX(getX() + speed);
            } else isRight = true;
        }
        if (isRight && !isBottom && !isLeft && !isTop) {
            if (getY() <= Singleton.getInstance().getWindow().getSizeY() - 120) {
                setY(getY() + speed);
            } else isBottom = true;
        }
        if (isRight && isBottom && !isLeft && !isTop) {
            if (getX() >= 10) {
                setX(getX() - speed);
            } else isLeft = true;
        }
        if (isRight && isBottom && isLeft && !isTop) {
            if (getY() >= 130) {
                setY(getY() - speed);
            } else isTop = true;
        }
        if (isRight && isBottom && isLeft && isTop) {
            isRight = false;
            isBottom = false;
            isLeft = false;
            isTop = false;
        }

    }
}
