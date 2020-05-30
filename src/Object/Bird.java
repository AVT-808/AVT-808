package Object;

import Behav.IBehaviour;
import java.awt.*;

public abstract class Bird implements IBehaviour
{
    public Integer identifier;
    public Point place;
    protected Image ris;
    public double time_luntik;
    public double go_away;

    protected Bird(Point place) { this.place = place;}

    @Override
    public Image getImage() { return ris; }

    @Override
    public Point getPlace() { return place; }

    @Override
    public void move() { }
}
