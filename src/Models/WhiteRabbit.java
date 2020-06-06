package Models;

import Models.Abstract.BaseRabbit;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class WhiteRabbit extends BaseRabbit {

    public WhiteRabbit(Point coordinates, Integer birthTime, Integer deathTime, Integer speed) throws IOException {
        super(coordinates, birthTime, deathTime, speed);
        this.rabbitImage = ImageIO.read(getClass().getResource("/WhiteRabbit.png"));
    }

    public void move() {

        if(1480 - coordinates.x < speed)
        {
            changeDirectionX=Direction.Left.getDirection();
        }
        if(coordinates.x < speed )
        {
            changeDirectionX=Direction.Right.getDirection();
        }
        coordinates.x = coordinates.x + speed*changeDirectionX;
    }
}
