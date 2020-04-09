package Fact;

import Object.Bird;

import java.awt.*;
import java.io.IOException;


public interface AbstractFactory
{
    Integer Return_the_Number_of_animals(); // Возвращает число птиц
    Bird Luntik(Integer time, Point place) throws IOException; // Создание птицы (той или другой)
    void Total_destruction(); // Обнуление счетчиков
}
