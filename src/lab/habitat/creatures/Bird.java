package lab.habitat.creatures;

import lab.habitat.IBehaviour;
import lab.habitat.ICreature;


public abstract class Bird implements IBehaviour, ICreature {
    public static double X = 0;
    public static double Y = 0;
    protected double targetX;
    protected double targetY;


    public Bird() {
    }

    public Bird(double x, double y) {
        X = x;
        Y = y;
    }

    public double getX(){
        return this.X;
    }

    public double getY(){
        return this.Y;
    }

    public void setX(double x){
        this.X = x;
    }

    public void setY(double y){
        this.Y = y;
    }

    public void move(double x, double y) {
        targetX = x;
        targetY = y;
    }

    public double getTargetX() {
        return targetX;
    }

    public double getTargetY() {
        return targetY;
    }
}
