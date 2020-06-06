package Fact;

import Contr.Periods;
import Contr.ComboB;
import Menu.*;

import java.awt.*;
import java.io.*;
import java.util.Random;


public class IdentifyType {

static int proverka =3;


    private Integer number_of_birds, number_of_Big, number_of_Small;

    Integer time_birth_small, time_birth_big;
    Float chance_birth_big;
     float percent;
     static Boolean c = false;

    public IdentifyType() {
        number_of_birds = 0;
        number_of_Big = 0;
        number_of_Small = 0;

    }

    public Integer Return_the_Number_of_animals() {
        return number_of_birds;
    }

    public void Total_destruction() {
        number_of_birds = 0;
        number_of_Big = 0;
        number_of_Small = 0;
    }

    public Integer Luntik_type(Integer time, Point place, Men men, Integer identifier) {



        Periods periods = men.Return_periods();
        time_birth_big = periods.Return_time_birth_big();
        time_birth_small = periods.Return_time_birth_small();

        ComboB comboB = men.Return_combob();
        chance_birth_big = comboB.Return_chance_birth_big();
        percent = comboB.Return_percent();

        if (c) {
            c=false;
            percent = (float)proverka/100;
           // System.out.println(percent);
            comboB.setComboBoxS(percent);

        }

        if (time % time_birth_big == 0){ // % -  остаток от деления. Т.о. каждые n секунд

            Random random = new Random();
            float chance = random.nextFloat(); // float nextFloat() - возвращает следующее случайное значение типа float

            if (chance <= chance_birth_big) {
                number_of_birds++;
                number_of_Big++;
                return 1;
            }
        }

        if (time % time_birth_small == 0) {

            if(number_of_Small < number_of_Big*percent) {
                number_of_birds++;
                number_of_Small++;
                return 2;
            }
        }


        return 0;

    }

public static void setC(int p) {
    c=true;
    proverka = p;
}
}

