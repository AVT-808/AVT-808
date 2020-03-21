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
    private final Integer normalRabbitBirthTime;//время рождения обыкновенного кролика
    private final Integer whiteRabbitBirthTime;//время рождения белого кролика
    private final Float normalRabbitBirthProbability;//вероятность рождения обыкновенного кролика
    private final Float rabbitsPercent;//процент от кроликов

    public ConcreteFactory(Integer normalRabbitBirthTime, Integer whiteRabbitBirthTime, Float normalRabbitBirthProbability, Float rabbitsPercent) {
        rabbitsAmount = 0;
        whiteRabbitsAmount = 0;
        this.normalRabbitBirthTime = normalRabbitBirthTime;
        this.whiteRabbitBirthTime = whiteRabbitBirthTime;
        this.normalRabbitBirthProbability = normalRabbitBirthProbability;
        this.rabbitsPercent = rabbitsPercent;
    }

    @Override
    public Integer getAmountOfBirth() {
        return rabbitsAmount;
    }

    @Override
    public BaseRabbit birth(Integer time, Point coordinates) throws IOException {
        if (time%normalRabbitBirthTime == 0) {
            Random random = new Random();
            float probability = random.nextFloat();
            if (probability <= normalRabbitBirthProbability) {
                BaseRabbit rabbit = new NormalRabbit(coordinates);
                rabbitsAmount++;
                return rabbit;
            }
        }
        if (time%whiteRabbitBirthTime == 0) {
            if(whiteRabbitsAmount<rabbitsAmount*rabbitsPercent) {
                BaseRabbit rabbit = new WhiteRabbit(coordinates);
                rabbitsAmount++;
                whiteRabbitsAmount++;
                return rabbit;
            }
        }
        return null;
    }

    @Override
    public void destroy() {
        rabbitsAmount = 0;
        whiteRabbitsAmount = 0;
    }
}
