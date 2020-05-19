package Object;

import Behav.IBehaviour;
import java.awt.*;

public abstract class Bird implements IBehaviour
{
    public Integer identifier;
    private Point place;
    protected Image ris;
    public Integer time_luntik;
    public Integer go_away;

    protected Bird(Point place) { this.place = place; }

    @Override
    public Image getImage() { return ris; }

    @Override
    public Point getPlace() { return place; }
}
