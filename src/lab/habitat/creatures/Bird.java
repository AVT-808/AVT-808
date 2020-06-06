package lab.habitat.creatures;

import lab.habitat.ICreature;


public abstract class Bird implements ICreature {
    protected double X = 0;
    protected double Y = 0;


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

}
