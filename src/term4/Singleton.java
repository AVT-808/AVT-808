package term4;

import java.util.HashSet;
import java.util.TreeMap;
import java.util.Vector;

public class Singleton {
    private static Singleton singleton;
    private Vector<House> houseVector;
    private HashSet<Double> id;
    private TreeMap<Double, Long> timesOfBirth;


    protected boolean isTopEdge = false;
    protected boolean isRightEdge = false;
    protected boolean isBottomEdge = false;
    protected boolean isLeftEdge = false;

    int speed = 100;

    private Singleton() {
        houseVector = new Vector<>();
        id = new HashSet<>();
        timesOfBirth = new TreeMap<>();
    }

    public static Singleton getSingleton(){
        if(singleton == null){
            singleton = new Singleton();
        }
        return  singleton;
    }

    public void setHouse(TypeOfHouse typeOfHouse){
        AbstractFactory abstractFactory = ConcreteFactory.concreteFactory(typeOfHouse);
        House house = abstractFactory.createHouse();
        house.setTimeOfBirth(MyComponent.getTime());
        houseVector.add(house);
        id.add(house.getId());
        timesOfBirth.put(house.getId(), MyComponent.getTime());
    }

    public House getHouse(int i){ return houseVector.get(i); }

    public long getTimeOfBirth(int i) { return houseVector.get(i).getTimeOfBirth(); }

    public void destroyHouse(int i) {
        id.remove(houseVector.get(i).getId());
        timesOfBirth.remove(houseVector.get(i).getId());
        houseVector.remove(i);  }

    public void clearArea() { houseVector.clear(); }

    public void setX(int i, int x){ houseVector.get(i).setX(x);}

    public void setY(int i, int y){ houseVector.get(i).setY(y);}

    public int getX(int i){ return houseVector.get(i).getX(); }

    public int getY(int i){ return houseVector.get(i).getY(); }

    public void setPosition(int i, int x, int y){ houseVector.get(i).setPosition(x, y); }

    public Vector<House> getHouseVector() { return houseVector; }

    public TreeMap<Double, Long> getTimesOfBirth() { return timesOfBirth; }
}
