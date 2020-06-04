package AntFarm;

import java.util.*;

public class Singleton {
    private static Singleton singleton;
    private Vector<Ant> ants;
    private HashSet<Integer> ids;
    private TreeMap<Integer, Integer> birthTimes;

    private Singleton() {
        ants = new Vector<>();
        ids = new HashSet<>();
        birthTimes = new TreeMap<>();
    }

    public int getArraySize() {
        return ants.size();
    }

    public static synchronized Singleton getSingleton() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }

    public static void addAnt(Ant ant, int id, int birthTime) {
        singleton.ants.add(ant);
        singleton.ids.add(id);
        singleton.birthTimes.put(id, birthTime);
    }

    public static void clearAnts() {
        if (!singleton.ants.isEmpty()) {
            singleton.ants.clear();
            singleton.ids.clear();
            singleton.birthTimes.clear();
        }
    }

    public static void removeAnt(Ant ant, int id) {
        singleton.ants.remove(ant);
        singleton.ids.remove(id);
        singleton.birthTimes.remove(id);
    }

    public static Vector<Ant> getAnts() {
        return singleton.ants;
    }

    public static TreeMap<Integer, Integer> getBirthTime() {
        return singleton.birthTimes;
    }

    public static HashSet<Integer> getIds() {
        return singleton.ids;
    }
}