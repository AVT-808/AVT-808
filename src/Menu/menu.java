package Menu;

import Contr.*;
import Habit.Habitat;

import javax.swing.*;

public class menu extends JPanel {

    CheckB checkB;
    Clock clock;
    ComboB combob;
    Periods periods;

    public menu(Habitat habitat) {

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        /////////
        checkB = new CheckB(); // Показывать информацию
        add(checkB);
        /////////
        periods = new Periods(); // Периоды появления птиц
        add(periods);
        /////////
        clock = new Clock(habitat); // Показывать/скрывать таймер
        add(clock);
        ////////
        combob = new ComboB(); // Вероятности
        add(combob);
        ////////

        setVisible(true);
        setFocusable(false);
    }

    public Boolean Return_nagatost() { // Показывать ли инфу
        Boolean d = checkB.Return_nagatost();
        return d;
    }

    public Boolean Return_dd() {
        Boolean dd = clock.Return_dd();
        return dd;
    }

    public void Сhange_dd() { clock.Change_dd(); }

    public ComboB Return_combob() {
        return combob;
        }

    public Periods Return_periods() {
        return periods;
    }
}

