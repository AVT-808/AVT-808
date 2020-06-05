package Habitat.RabbitList;

import Models.Abstract.BaseRabbit;

import java.util.*;

public final class Singleton {
    private static Singleton instance;
    private final List<BaseRabbit> rabbits;
    private final Set<UUID> rabbitsIds;
    private final Map<UUID,Integer> rabbitMap;

    private Singleton() {
        rabbits = new Vector<>();
        rabbitsIds = new TreeSet<>();
        rabbitMap=new HashMap<>();
    }

    public static void addRabbit(BaseRabbit rabbit) {
        instance.rabbits.add(rabbit);
        instance.rabbitsIds.add(rabbit.getId());
        instance.rabbitMap.put(rabbit.getId(),rabbit.getBirthTime());
    }

    public static void clearRabbits(){
        if (!instance.rabbits.isEmpty())
        {
            instance.rabbits.clear();
            instance.rabbitsIds.clear();
            instance.rabbitMap.clear();
        }

    }

    public static List<BaseRabbit> getRabbits(){
        return instance.rabbits;
    }

    public static Map<UUID, Integer> getRabbitsInformation(){
        return instance.rabbitMap;
    }

    public static void clearRabbit(BaseRabbit rabbit){
        instance.rabbits.remove(rabbit);
        instance.rabbitsIds.remove(rabbit.getId());
        instance.rabbitMap.remove(rabbit.getId());
    }


    public static Singleton getInstance(){
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
