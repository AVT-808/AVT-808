package AntFarm;

import java.awt.*;

public abstract class Ant implements IBehaviour
{
    private int x, y;
    private Image image;
    protected int id, birthTime, deathTime;

    protected Ant(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public void setX(int x)
    {
        this.x = x;
    }

    @Override
    public int getX()
    {
        return x;
    }

    @Override
    public void setY(int y)
    {
        this.y = y;
    }

    @Override
    public int getY()
    {
        return y;
    }

    @Override
    public void setImage(Image image)
    {
        this.image = image;
    }

    @Override
    public Image getImage()
    {
        return image;
    }

    @Override
    public void move() {}
}
