package Models;

import Models.Abstract.BaseRabbit;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class NormalRabbit extends BaseRabbit {
    public NormalRabbit(Point coordinates) throws IOException {
        super(coordinates);
        this.rabbitImage = ImageIO.read(getClass().getResource("/Rabbit.png"));
    }
}
