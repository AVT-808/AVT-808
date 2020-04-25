package Contr;

import Habit.Habitat;
import Habit.ToBeContinued;

import javax.swing.*;
import java.util.Timer;

public class StarT_StoP {

    private static StarT_StoP N; // Поле, содержащее одиночный объект

    private Timer timer;
    private ToBeContinued habitatTask;

    private StarT_StoP() {} // Конструктор (приватный, чтоб не смог возвращать новые объекты)

    public static StarT_StoP getN()  // Создающий метод, который будет использоваться для получения одиночки
    {
        if (N == null) // Если объект ещё не создан
            N = new StarT_StoP(); // Создать новый объект
        return N; // вернуть ранее созданный объект
    }

    public void StarT(Habitat habitat, JButton button_start, JButton button_stop) {

        timer = new Timer();
        habitatTask = new ToBeContinued(habitat);
        timer.schedule(habitatTask, 0, 1000);

        button_start.setEnabled(false); // Блокировка кнопки
        button_stop.setEnabled(true);
    }

    public void StoP(Habitat habitat, JButton button_start, JButton button_stop) {

        timer.cancel(); // cancel() - прерывает поток таймера
        habitatTask.cancel();
        timer.purge(); // purge() - удаляет прерванные задания из очереди таймера
        habitat.Stop();

        button_stop.setEnabled(false);
        button_start.setEnabled(true);
    }
}

