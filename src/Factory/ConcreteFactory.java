package Factory;

import Models.Abstract.BaseRabbit;
import Models.NormalRabbit;
import Models.WhiteRabbit;

import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class ConcreteFactory implements AbstractFactory {

    private Integer rabbitsAmount;//количество кроликов
    private Integer whiteRabbitsAmount;//количество белых кроликов
    private Integer normalRabbitsAmount;
    private Integer normalRabbitBirthTime;//время рождения обыкновенного кролика
    private Integer whiteRabbitBirthTime;//время рождения белого кролика
    private Float normalRabbitBirthProbability;//вероятность рождения обыкновенного кролика
    private Float rabbitsPercent;//процент от кроликов

    public ConcreteFactory() {
        rabbitsAmount = 0;
        whiteRabbitsAmount = 0;
        normalRabbitsAmount = 0;
    }

    @Override
    public Integer getAmountOfBirth() {
        return rabbitsAmount;
    }

    @Override
    public BaseRabbit birth(Integer time, Point coordinates) throws IOException {
        if(time%normalRabbitBirthTime == 0 && time%whiteRabbitBirthTime == 0){
            Random random = new Random();
            float randomFloat = random.nextFloat();
            if (randomFloat >= 0.5){
                return birthNormalRabbit(coordinates);
            }
            else{
                return birthWhiteRabbit(coordinates);
            }
        }
        if (time%normalRabbitBirthTime == 0) {
           return birthNormalRabbit(coordinates);
        }
        if (time%whiteRabbitBirthTime == 0) {
          return birthWhiteRabbit(coordinates);
        }
        return null;
    }

    @Override
    public void destroy() {
        rabbitsAmount = 0;
        whiteRabbitsAmount = 0;
        normalRabbitsAmount = 0;
    }

    @Override
    public void setSimulationProperties(Integer normalRabbitBirthTime, Integer whiteRabbitBirthTime, Float normalRabbitBirthProbability, Float rabbitsPercent) {
        this.normalRabbitBirthTime = normalRabbitBirthTime;
        this.whiteRabbitBirthTime = whiteRabbitBirthTime;
        this.normalRabbitBirthProbability = normalRabbitBirthProbability;
        this.rabbitsPercent = rabbitsPercent;
    }

    @Override
    public Integer getAmountOfNormalRabbits() {
        return normalRabbitsAmount;
    }

    @Override
    public Integer getAmountOfWhiteRabbits() {
        return whiteRabbitsAmount;
    }

    private BaseRabbit birthNormalRabbit(Point coordinates) throws IOException {
        Random random = new Random();
        float probability = random.nextFloat();
        if (probability <= normalRabbitBirthProbability) {
            BaseRabbit rabbit = new NormalRabbit(coordinates);
            rabbitsAmount++;
            normalRabbitsAmount++;
            return rabbit;
        }
        return null;
    }

    private BaseRabbit birthWhiteRabbit(Point coordinates) throws IOException {
        if(whiteRabbitsAmount<rabbitsAmount*rabbitsPercent) {
            BaseRabbit rabbit = new WhiteRabbit(coordinates);
            rabbitsAmount++;
            whiteRabbitsAmount++;
            return rabbit;
        }
        return null;
    }
}
