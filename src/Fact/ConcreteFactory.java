package Fact;

import Object.*;

import java.awt.*;
import java.io.IOException;
import java.util.Random;


public class ConcreteFactory implements AbstractFactory
{

    private Integer number_of_birds;
    private Integer number_of_Big;
    private Integer number_of_Small;

    private final Integer time_birth_small;
    private final Integer time_birth_big;
    private final Float chance_birth_big;
    private final Float percent;

    public ConcreteFactory(Integer time_birth_small, Integer time_birth_big, Float chance_birth_big, Float percent)
    {
        number_of_birds = 0;
        number_of_Big = 0;
        number_of_Small = 0;

        this.time_birth_small = time_birth_small;
        this.time_birth_big = time_birth_big;
        this.chance_birth_big = chance_birth_big;
        this.percent = percent;
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
    public Bird Luntik(Integer time, Point place) throws IOException {

        if (time % time_birth_big == 0)
        {
            Random random = new Random();
            float chance = random.nextFloat();
            if (chance <= chance_birth_big)
            {
                Bird fowl = new Big(place);
                number_of_birds++;
                number_of_Big++;
                return fowl;
            }
        }

        if (time % time_birth_small == 0)
        {
            if(number_of_Small < number_of_Big*percent)
            {
                Bird fowl = new Small(place);
                number_of_birds++;
                number_of_Small++;
                return fowl;
            }
        }
        return null;
    }

}
