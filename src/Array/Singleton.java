package Array;

import Object.*;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashMap;

public class Singleton implements Serializable {

    private static Singleton M; // Поле, содержащее одиночный объект
    public LinkedList <Bird> Bird_s;
    public Set<Integer> treeSet;
    HashMap<Integer, Integer> hashMap;

    private Singleton() { // Конструктор  (приватный, чтоб не смог возвращать новые объекты)
        Bird_s = new LinkedList<>();
        treeSet = new TreeSet<>();
        hashMap = new HashMap<>();
    }

    public synchronized static Singleton getM()  // Создающий метод, который будет использоваться для получения одиночки
    {
        if (M == null) // Если объект ещё не создан
            M = new Singleton(); // Создать новый объект
        return M; // вернуть ранее созданный объект
    }

    public void Add_Bird_s (Bird bird){
        M.Bird_s.add(bird);
    } // Установка значений (коллекции)

    public void Add_identifier (Integer identifier){
        M.treeSet.add(identifier);
    }

    public void Put_hashMap(Integer identifier,Integer time_luntik) {M.hashMap.put(identifier,time_luntik);} //

    public void Destruction_Bird_s(){
        M.Bird_s.clear();
    } // Полное очищение (коллекции)

    public void Destruction_treeSet() { M.treeSet.clear(); }

    public void Destruction_hashMap() { M.hashMap.clear(); } //

    public void Bird_s_remove(Bird bird) { Bird_s.remove(bird); } // Удаление объектов по времени (коллекции)

    public void treeSet_remove(Integer key) { treeSet.remove(key); }

   public void hashMap_remove(Integer key) { hashMap.remove(key); } //

    public static LinkedList<Bird> getBird_s(){
        return M.Bird_s;
    }

    public HashMap<Integer, Integer> Return_hashMap() { return hashMap; }

    public void setBird_s(LinkedList<Bird> a){
        Bird_s = a;
    }


}

