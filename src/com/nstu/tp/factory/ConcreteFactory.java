package com.nstu.tp.factory;

import com.nstu.tp.models.GoldFish;
import com.nstu.tp.models.GuppyFish;
import com.nstu.tp.models.abstracts.BaseFish;

import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class ConcreteFactory implements AbstractFactory {
    private Integer guppyFishAmount;
    private Integer fishAmount;
    private Integer goldFishAmount;
    private Integer goldFishBirth;
    private Integer guppyFishBirth;
    private Float goldFishBirthProbability;
    private Float guppyFishBirthProbability;

    public ConcreteFactory(Integer goldFishBirth, Integer guppyFishBirth, Float goldFishBirthProbability, Float guppyFishBirthProbability) {
        guppyFishAmount =0;
        fishAmount = 0;
        goldFishAmount =0;
       this.goldFishBirth = goldFishBirth;
       this.guppyFishBirth = guppyFishBirth;
       this.goldFishBirthProbability = goldFishBirthProbability;
       this.guppyFishBirthProbability = guppyFishBirthProbability;
    }

    @Override
    public Integer getAmountOfBirth() {
        return fishAmount;
    }

    public Integer getGoldFishAmountBirth() {
        return goldFishAmount;
    }
    public Integer getGuppyFishAmountBirth() {
        return guppyFishAmount;
    }

    @Override
    public BaseFish birth(Integer time, Point cords) throws IOException {
       // if (time % goldFishBirth == 0 && time % guppyFishBirth  == 0){


        if (time % goldFishBirth == 0){
            Random random = new Random();
            Float birthProbability = random.nextFloat();
            if (birthProbability <= goldFishBirthProbability){
                BaseFish fish = new GoldFish(cords);
                fishAmount++;
                goldFishAmount++;
                return fish;

            }
        }
        if (time % guppyFishBirth == 0){
            Random random = new Random();
            Float birthProbability = random.nextFloat();
            if (birthProbability <= guppyFishBirthProbability){
                BaseFish fish = new GuppyFish(cords);
                guppyFishAmount++;
                fishAmount++;
                return fish;
            }
        }


            return null;
    }

    @Override
    public void destroy() {
        fishAmount = 0;
        goldFishAmount = 0;
        guppyFishAmount = 0;

    }
}
