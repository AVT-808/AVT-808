package Fact;

import Contr.Periods;
import Object.*;
import Contr.ComboB;
import Menu.*;

import java.awt.*;
import java.io.IOException;
import java.util.Random;


public class ConcreteFactory implements AbstractFactory
{

    private Integer number_of_birds;
    private Integer number_of_Big;
    private Integer number_of_Small;

    private  Integer time_birth_small;
    private  Integer time_birth_big;
    private Float chance_birth_big;
    private Float percent;


    public ConcreteFactory() {
        number_of_birds = 0;
        number_of_Big = 0;
        number_of_Small = 0;
    }

    @Override
    public Integer Return_the_Number_of_animals() {
        return number_of_birds;
    }


    @Override
    public void Total_destruction() {
        number_of_birds = 0;
        number_of_Big = 0;
        number_of_Small = 0;
    }

    @Override
    public Bird Luntik(Integer time, Point place, menu men) throws IOException {

        Periods periods = men.Return_periods();
        this.time_birth_big = periods.Return_time_birth_big();
        this.time_birth_small = periods.Return_time_birth_small();

        ComboB comboB = men.Return_combob();
        this.chance_birth_big = comboB.Return_chance_birth_big();
        this.percent = comboB.Return_percent();

        if (time % this.time_birth_big == 0){ // % -  остаток от деления. Т.о. каждые n секунд

            Random random = new Random();
            float chance = random.nextFloat(); // float nextFloat() - возвращает следующее случайное значение типа float

            if (chance <= chance_birth_big) {
                Bird fowl = new Big(place);
                number_of_birds++;
                number_of_Big++;
                return fowl;
            }
        }

        if (time % this.time_birth_small == 0) {
            if(number_of_Small < number_of_Big*percent) {
                Bird fowl = new Small(place);
                number_of_birds++;
                number_of_Small++;
                return fowl;
            }
        }
        return null;
    }
}
