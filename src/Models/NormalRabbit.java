package Models;

import Habitat.HabitatFrame;
import Models.Abstract.BaseRabbit;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class NormalRabbit extends BaseRabbit{
    private Integer changeDirectionTime;
    private Random random;

    public NormalRabbit(Point coordinates, Integer birthTime, Integer deathTime, Integer speed, Integer changeDirectionTime) throws IOException {
        super(coordinates,birthTime,deathTime, speed);
        this.changeDirectionTime = changeDirectionTime;
        this.rabbitImage = ImageIO.read(getClass().getResource("/Rabbit.png"));
        random = new Random();
    }

    public void move() {
        if(coordinates.x > 1550 || coordinates.y>650) {
            coordinates.x =  changeDirectionX*random.nextInt(100);
            coordinates.y =  changeDirectionY*random.nextInt(100);
            return;
        }
        if(HabitatFrame.getTime() - birthTime % changeDirectionTime == 0)
        {

            var xRandomDirection = 1 + random.nextInt(100);
            var yRandomDirection = 1 + random.nextInt(100);
            changeDirectionX = xRandomDirection < 50 ? Direction.Right.getDirection(): Direction.Left.getDirection();
            changeDirectionY = yRandomDirection < 50 ? Direction.Up.getDirection(): Direction.Down.getDirection();
        }
        coordinates.x=coordinates.x+speed*changeDirectionX;
        coordinates.y=coordinates.y+speed*changeDirectionY;
    }
}
