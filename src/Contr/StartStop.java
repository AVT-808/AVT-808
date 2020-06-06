package Contr;

import Habit.Habitat;
import Habit.ToBeContinued;

import javax.swing.*;
import java.util.Timer;

public class StartStop {

    private static StartStop N; // Поле, содержащее одиночный объект

    private Timer timer;
    private ToBeContinued habitatTask;
    boolean cho_proisxodit=false;

    private StartStop() {} // Конструктор (приватный, чтоб не смог возвращать новые объекты)

    public static StartStop getN() { // Создающий метод, который будет использоваться для получения одиночки

        if (N == null) // Если объект ещё не создан
            N = new StartStop(); // Создать новый объект
        return N; // вернуть ранее созданный объект
    }

    public void StarT(Habitat habitat, JButton button_start, JButton button_stop) {
        if (!cho_proisxodit) {
            timer = new Timer();
            habitatTask = new ToBeContinued(habitat);
            timer.schedule(habitatTask, 0, 1000);

            button_start.setEnabled(false); // Блокировка кнопки
            button_stop.setEnabled(true);
            cho_proisxodit = true;
        }
    }

    public void StoP(Habitat habitat, JButton button_start, JButton button_stop) {
        if (cho_proisxodit) {
            timer.cancel(); // cancel() - прерывает поток таймера
            habitatTask.cancel();
            timer.purge(); // purge() - удаляет прерванные задания из очереди таймера
            habitat.Stop(0);

            button_stop.setEnabled(false);
            button_start.setEnabled(true);
            cho_proisxodit = false;
        }
    }
}

