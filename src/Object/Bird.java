package Object;

import Behav.IBehaviour;

import java.awt.*;
import java.io.Serializable;

public abstract class Bird implements IBehaviour, Serializable {
    public Integer identifier;
    public Point place;
    transient public Image ris;
    public double time_luntik;
    public double go_away;
    Boolean who;


    protected Bird(Point place) { this.place = place;}

    @Override
    public Image getImage() {
        return ris; }

    @Override
    public Point getPlace() { return place; }

    @Override
    public void move() { }

    public boolean getWho (){
        return who;
    }

}
