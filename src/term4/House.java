package term4;

import javax.swing.*;
import java.io.Serializable;

public abstract class House implements IBehaviour, Serializable {
    ImageIcon img;
    private long timeOfBirth;
    private int x;
    private int y;
    protected double id;
    TypeOfHouse typeOfHouse;
    protected int speed;


    public ImageIcon getImg() { return img; }

    public long getTimeOfBirth() { return timeOfBirth; }

    public void setTimeOfBirth(long timeOfBirth) { this.timeOfBirth = timeOfBirth; }

    @Override
    public void setX(int x) { this.x = x; }

    @Override
    public void setY(int y) { this.y = y; }

    @Override
    public int getX() { return x; }

    @Override
    public int getY() { return y; }

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double getId() { return id; }

    public TypeOfHouse getTypeOfHouse() { return typeOfHouse; }

    public int getSpeed() { return speed; }

    public void setSpeed(int speed) { this.speed = speed; }
}
