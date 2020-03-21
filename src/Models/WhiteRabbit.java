package Models;

import Models.Abstract.BaseRabbit;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class WhiteRabbit extends BaseRabbit {

    public WhiteRabbit(Point coordinates) throws IOException {
        super(coordinates);
        this.rabbitImage = ImageIO.read(getClass().getResource("/WhiteRabbit.png"));
    }
}
