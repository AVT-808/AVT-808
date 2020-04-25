package Object;

import Behav.IBehaviour;
import java.awt.*;

public abstract class Bird implements IBehaviour
{
    private Point place;
    protected Image ris;

    protected Bird(Point place) { this.place = place; }

    @Override
    public Image getImage() { return ris; }

    @Override
    public Point getPlace() { return place; }
}
