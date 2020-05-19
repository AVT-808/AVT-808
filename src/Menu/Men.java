package Menu;

import Contr.*;
import Habit.Habitat;
import Existence.*;

import javax.swing.*;
import java.awt.*;

public class Men extends JPanel {

    CheckB checkB;
    Clock clock;
    ComboB combob;
    Periods periods;
    Life life;
    CurrentObjects currentObjects;
    Butt butt;

    public Men(Habitat habitat) {

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        ////////
        butt = new Butt(habitat);
        add(butt);
        ////////
        currentObjects=new CurrentObjects(habitat); // Текущие объекты
        add(currentObjects);
        /////////
        checkB = new CheckB(); // Показывать информацию
        add(checkB);
        /////////
        periods = new Periods(); // Периоды появления птиц
        add(periods);
        /////////
        add(Box.createRigidArea(new Dimension(5,0))); // Разделитель
        /////////
        life = new Life(); // Время жизни
        add(life);
        /////////
        clock = new Clock(); // Показывать/скрывать таймер
        add(clock);
        ////////
        combob = new ComboB(); // Вероятности
        add(combob);
        ////////


        setVisible(true);
        setFocusable(false);
    }

    public Boolean Return_nagatost() { // Показывать ли инфу
        return  checkB.Return_nagatost();
    }

    public ComboB Return_combob() {
        return combob;
    }

    public Periods Return_periods() {
        return periods;
    }
}

