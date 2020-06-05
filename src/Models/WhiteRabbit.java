package Models;

import Models.Abstract.BaseRabbit;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class WhiteRabbit extends BaseRabbit {

    public WhiteRabbit(Point coordinates, Integer birthTime, Integer deathTime) throws IOException {
        super(coordinates, birthTime, deathTime);
        this.rabbitImage = ImageIO.read(getClass().getResource("/WhiteRabbit.png"));
    }
}
