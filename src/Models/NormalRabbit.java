package Models;

import Models.Abstract.BaseRabbit;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class NormalRabbit extends BaseRabbit {
    public NormalRabbit(Point coordinates, Integer birthTime, Integer deathTime) throws IOException {
        super(coordinates,birthTime,deathTime);
        this.rabbitImage = ImageIO.read(getClass().getResource("/Rabbit.png"));
    }
}
