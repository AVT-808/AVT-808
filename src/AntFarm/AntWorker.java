package AntFarm;

import javax.imageio.ImageIO;
import java.io.IOException;

public class AntWorker extends Ant
{
    private int x0, y0;
    private boolean comeback;

    AntWorker(int x, int y, int id, int birthTime, int lifetime)  {
        super(x, y);
        this.id = id;
        this.birthTime = birthTime;
        this.deathTime = birthTime + lifetime;
        this.comeback = false;
        x0 = x; y0 = y;

        try {
            this.setImage(ImageIO.read(getClass().getResource("/AntWorker.png")));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void move()
    {
        int speed = 10;
        double k = (double)y0/x0;
        if (!(this.comeback) && (this.getX() > 0 || this.getY() > 0))
        {
            this.setX(this.getX()-speed);
            this.setY((int)(k*this.getX()));
        }
        if (this.comeback)
        {
            if(this.getX() == x0 && this.getY() == y0)
            {
                this.comeback = false;
            }
            else
            {
                this.setX(this.getX() + speed);
                this.setY((int)(k*getX()));
            }
        }
        if (this.getX() <= 0 && this.getY() <= 0)
        {
            this.comeback = true;
        }
    }
}
