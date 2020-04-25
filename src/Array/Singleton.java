package Array;

import Object.*;

import java.util.ArrayList;
import java.util.List;


public class Singleton {

    private static Singleton M; // Поле, содержащее одиночный объект
    public final List <Bird> Bird_s;

    private Singleton() { // Конструктор  (приватный, чтоб не смог возвращать новые объекты)
        Bird_s = new ArrayList<>();
    }

    public static Singleton getM()  // Создающий метод, который будет использоваться для получения одиночки
    {
        if (M == null) // Если объект ещё не создан
            M = new Singleton(); // Создать новый объект
        return M; // вернуть ранее созданный объект
    }

    public static List<Bird> getBird_s(){
        return M.Bird_s;
    }

    public static void Add_Bird_s (Bird bird){
        M.Bird_s.add(bird);
    }

    public static void Destruction (){
        M.Bird_s.clear();
    }

}

