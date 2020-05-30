package AntFarm;

import javax.imageio.ImageIO;
import java.io.IOException;

public class AntWorker extends Ant
{
    AntWorker(int x, int y, int id, int birthTime, int lifetime)  {
        super(x, y);
        this.id = id;
        this.birthTime = birthTime;
        this.deathTime = birthTime + lifetime;

        try {
            this.image = ImageIO.read(getClass().getResource("/AntWorker.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
