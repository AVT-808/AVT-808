package AntFarm;

import javax.imageio.ImageIO;
import java.io.IOException;

public class AntWarrior extends Ant
{
    int x0, y0;
    int iter = 1;

    AntWarrior(int x, int y, int id, int birthTime, int lifetime) {
        super(x, y);
        this.id = id;
        this.birthTime = birthTime;
        this.deathTime = birthTime + lifetime;
        this.x0 = x; this.y0 = y;

        try {
            this.setImage(ImageIO.read(getClass().getResource("/AntWarrior.png")));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void move()
    {
        int R = 30;
        double speed = Math.PI/6;
        setX((int)(x0+ R*Math.cos(iter*speed)));
        setY((int)(y0+R*Math.sin(iter*speed)));
        this.iter++;
    }
}
