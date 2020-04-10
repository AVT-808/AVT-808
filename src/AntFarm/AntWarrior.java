package AntFarm;

import javax.imageio.ImageIO;
import java.io.IOException;

public class AntWarrior extends Ant
{
    AntWarrior(int x, int y) {
        super(x, y);
        try {
            this.image = ImageIO.read(getClass().getResource("/AntWarrior.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
