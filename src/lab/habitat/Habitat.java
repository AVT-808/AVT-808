package lab.habitat;

import lab.habitat.creatures.birds.BigBird;
import lab.habitat.creatures.birds.SmallBird;

import java.util.*;

public class Habitat {




    private LinkedList<CreatureEventListener> creatureCreateListeners = new LinkedList<>();
    private LinkedList<CreatureEventListener> creatureDieListeners = new LinkedList<>();
    private LinkedList<Class<? extends ICreature>> creatureTypes = new LinkedList<>();

    private int ticks = 0;

    private HabitatMetrics metrics = new HabitatMetrics();
    private TreeSet<Integer> creatureID = new TreeSet<>();

    private LinkedList<ICreature> creatures = new LinkedList<>();//хранение объектов обьектов

    private HashMap<Integer, LinkedList<ICreature>> creaturesTTLs = new HashMap();//записывается время рождения обьектов


    public void update() {
        ticks++;
        metrics.incrementTime();


        // ограничение рождаемости
        if(creatures.size() >= 20)
            return;



        // create new creatures
        creatureTypes.forEach(T -> {
            try {
                if((Boolean) T.getMethod("isCreationAllowed", IHabitatMetrics.class).invoke(null, metrics) && ((ticks % BigBird.Period==0) && (creatureTypes.lastIndexOf(T) == 1)|| (ticks % SmallBird.Period==0) && (creatureTypes.lastIndexOf(T) == 0) )) {


                    ICreature c = T.getConstructor().newInstance();

                    System.out.println();

                    creatureID.add(c.getID());
                    System.out.println(creatureID);
                    c.setX(Math.random());
                    c.setY(Math.random());
                    creatures.add(c);

                    LinkedList<ICreature> cs = creaturesTTLs.getOrDefault(ticks + c.getTTL(), new LinkedList<>());
                    cs.add(c);

                    creaturesTTLs.put(ticks + c.getTTL(), cs);

                    metrics.incrementCreatureCount(T);
                    creatureCreateListeners.forEach(e -> e.onEvent(c));


                }
            }
            catch(Exception ex) {
                ex.printStackTrace();
            }
        });

        // and kill too old
        LinkedList<ICreature> cs = creaturesTTLs.getOrDefault(ticks, null);
        if(cs != null) {
            cs.forEach(c -> {
                creatures.removeFirstOccurrence(c);

                metrics.decrementCreatureCount(c.getClass());
                creatureDieListeners.forEach(e -> e.onEvent(c));
            });

            creaturesTTLs.remove(ticks);
        }
    }

    public void reset() {
        ticks = 0;
        metrics = new HabitatMetrics();
        creatures.clear();
        creaturesTTLs.clear();
    }


    public IHabitatMetrics getMetrics() {
        return metrics;
    }

    public void addCreatureType(Class creatureType) throws IllegalArgumentException {
        if(!ICreature.class.isAssignableFrom(creatureType)) {
            throw new IllegalArgumentException(creatureType.getName() + " doesn't implement Creature interface!");
        }

        if(creatureTypes.contains(creatureType)) {
            throw new IllegalArgumentException(creatureType.getName() + " already added to current habitat");
        }

        // but this assignment IS checked!
        creatureTypes.add(creatureType);
    }

    public LinkedList<ICreature> getCreaturesByType(Class<? extends ICreature> type) {
        LinkedList<ICreature> creaturesByType = new LinkedList<>();

        for (ICreature c : creatures) {
            if(c.getClass() == type)
                creaturesByType.add(c);
        }

        return creaturesByType;
    }

    public LinkedList<ICreature> getCreatures() {
        return (LinkedList<ICreature>) creatures.clone();
    }

    public HashMap<Integer, LinkedList<ICreature>> getCreaturesTTLs() {
        return (HashMap<Integer, LinkedList<ICreature>>) creaturesTTLs.clone();
    }


    public void addCreatureTypes(Class ... creatureTypes) throws IllegalArgumentException {
        for (Class creatureType : creatureTypes) {
            addCreatureType(creatureType);
        }
    }

    public void removeCreatureType(Class creatureType) {
        creatureTypes.remove(creatureType);
    }

    public void removeCreatureTypes(Class ... creatureTypes) throws IllegalArgumentException {
        for (Class type : creatureTypes) {
            removeCreatureType(type);
        }
    }


    public void addCreatureCreateListener(CreatureEventListener e) {
        creatureCreateListeners.add(e);
    }

    public void addCreatureDieListener(CreatureEventListener e) {
        creatureDieListeners.add(e);
    }


    protected class HabitatMetrics implements IHabitatMetrics {
        private int ticks = 0;

        private HashMap<Class<? extends ICreature>, Integer> creatureCountByType = new HashMap<>();//записываются птицы разного вида


        public int getCreatureCountByType(Class<? extends ICreature> type) {
            return creatureCountByType.getOrDefault(type, 0);
        }

        public int getTime() {

            return ticks;
        }



        public void incrementCreatureCount(Class<? extends ICreature> type) {


            int count = creatureCountByType.getOrDefault(type, 0);
            creatureCountByType.put(type, ++count);
        }

        public void decrementCreatureCount(Class<? extends ICreature> type) {


            int count = creatureCountByType.get(type);
            creatureCountByType.put(type, --count);
        }

        public void incrementTime() {
            ++ticks;
        }
    }
}
