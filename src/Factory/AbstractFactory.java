package Factory;

import Models.Abstract.BaseRabbit;

import java.awt.*;
import java.io.IOException;

public interface AbstractFactory {
    Integer getAmountOfBirth();//получть количество рожденных объектов

    BaseRabbit birth(Integer time, Point coordinates) throws IOException;//Родить объект BaseRabbit, содержащий в себе ссылку на наследника

    void destroy();//уничтожить скписок объектов
}
