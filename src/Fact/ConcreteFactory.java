package Fact;

import Object.*;
import Menu.*;
import Habit.*;

import java.awt.*;
import java.io.IOException;


public class ConcreteFactory implements AbstractFactory {
    IdentifyType identifyType;

    public ConcreteFactory(/*Habitat habitat*/) throws IOException {
        identifyType = new IdentifyType(/*habitat.getConsole().getStream()*/);
    }

    @Override
    public Integer Return_the_Number_of_animals() {
      return identifyType.Return_the_Number_of_animals();//number_of_birds;
    }

    @Override
    public void Total_destruction() { identifyType.Total_destruction(); }

    @Override
    public Bird Luntik(Integer time, Point place, Men men, Integer identifier) throws IOException {

        Integer type = identifyType.Luntik_type(time, place, men, identifier);

        if (type==1) {

            return new Big(place, identifier,time, Existence.Life.Return_lifetime_Big());

        } else {

            if (type==2) {
                return new Small(place, identifier,time,  Existence.Life.Return_lifetime_Small());
            }  else return null;
        }
    }
}



