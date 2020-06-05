package Factory;

import Models.Abstract.BaseRabbit;

import java.awt.*;
import java.io.IOException;

public interface AbstractFactory {
    BaseRabbit birthNormalRabbit(Point coordinates, Integer birthTime, Integer deathTime) throws IOException;//Родить объект BaseRabbit, содержащий в себе ссылку на наследника
    BaseRabbit birthWhiteRabbit(Point coordinates, Integer birthTime, Integer deathTime) throws IOException;
}
