package Factory;

import Models.Abstract.BaseRabbit;
import Models.NormalRabbit;
import Models.WhiteRabbit;

import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class ConcreteFactory implements AbstractFactory {
    @Override
    public BaseRabbit birthNormalRabbit(Point coordinates, Integer birthTime, Integer deathTime) throws IOException {
        return new NormalRabbit(coordinates, birthTime, deathTime, 20, 4);
    }

    @Override
    public BaseRabbit birthWhiteRabbit(Point coordinates, Integer birthTime, Integer deathTime) throws IOException {
        return new WhiteRabbit(coordinates, birthTime, deathTime, 20);
    }

}
