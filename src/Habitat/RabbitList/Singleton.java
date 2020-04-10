package Habitat.RabbitList;

import Models.Abstract.BaseRabbit;

import java.util.ArrayList;
import java.util.List;

public final class Singleton {
    private static Singleton instance;
    private final List<BaseRabbit> rabbits;

    private Singleton() {
        rabbits = new ArrayList<>();
    }

    public static void addRabbit(BaseRabbit rabbit) {
        instance.rabbits.add(rabbit);
    }

    public static void clearRabbits(){
        if (!instance.rabbits.isEmpty())
            instance.rabbits.clear();
    }

    public static List<BaseRabbit> getRabbits(){
        return instance.rabbits;
    }

    public static Integer getRabbitsAmount(){
        return instance.rabbits.size();
    }

    public static Singleton getInstance(){
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
