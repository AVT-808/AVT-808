package AntFarm;

import javax.imageio.ImageIO;
import java.io.IOException;

public class AntWorker extends Ant
{
    AntWorker(int x, int y)  {
        super(x, y);
        try {
            this.image = ImageIO.read(getClass().getResource("/AntWorker.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
