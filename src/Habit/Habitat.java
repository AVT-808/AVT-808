package Habit;

import Menu.*;
import Contr.*;
import Array.Singleton;
import DrawPanel.Depict_a_bird;
import Fact.*;
import Object.*;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Habitat extends JFrame {

    private final Singleton Bird_s;
    private final AbstractFactory factory;
    private Integer time;
    private final Depict_a_bird depict_a_bird;

    private Integer number_of_Big = 0;
    private Integer number_of_Small = 0;

    private final Line line;
    private final Butt butt;
    private final menu men;

    JButton button_start;
    JButton button_stop;

    //*********************************//

    public Habitat() {

        super("Field");
        int width = 1000;
        int height = 650;
        setSize(width, height);
        setResizable(true); // Размеры окна: false - не изменять

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Чтобы при закрытии окна закрывалась и программа, иначе она останется висеть в процессах

        this.time = 0;
        Bird_s = Singleton.getM();
        factory = new ConcreteFactory();

        men = new menu(this);
        men.setMaximumSize(new Dimension(width, 50));

        depict_a_bird = new Depict_a_bird();
        depict_a_bird.setMaximumSize(new Dimension(width, height-21 ));

        line = new Line();
        line.setMaximumSize(new Dimension(width, 20));

        butt = new Butt(this);
        button_start=butt.Return_start();
        button_stop=butt.Return_stop();
        men.add(butt);

        add(men);
        add(depict_a_bird);
        add(line);

        setFocusable(true);
    }

    //*********************************//

    public JButton Return_start(){
        return button_start;
    }

    public JButton Return_stop(){
        return button_stop;
    }

    //*********************************//

    public void Stop() // Остановить отрисовку среды
    {
        Boolean ddd=true;

        Boolean d = men.Return_nagatost();

        if (d) { // d==true

            Inf inf =new Inf(this,time,number_of_Small,number_of_Big);
            inf.setVisible(true);
            ddd=inf.Return_ddd();
        }

        // Обнуление всего
      if (ddd) {

          factory.Total_destruction();

          number_of_Small = 0;
          number_of_Big = 0;
          time = 0;
          Bird_s.Destruction();

          depict_a_bird.repaint();
      }
    }

    //*********************************//

    public void Clock_yes_no(Boolean dd){ // Есть часы или не тClock

        if (!dd) line.Clock(false);
        else line.Clock(true); // Сделать видимым
    }

    //*********************************//

    public Boolean Return_dd() {
        Boolean dd = men.Return_dd();
        men.Сhange_dd();
        return dd;
    }

    //*********************************//

    void Update() {

        time++;

        line.setTimer(time,factory.Return_the_Number_of_animals());

        Random coord = new Random(); // Расположение птицы
        int x_Coord = coord.nextInt(depict_a_bird.getWidth()-100);
        int y_Coord = coord.nextInt(depict_a_bird.getHeight()-100);

        requestFocus(); // Передает фокус на поле

        Point coordinates  = new Point(x_Coord, y_Coord);
        try
        {
            Bird bird = factory.Luntik(time, coordinates, men); // Возвращает какую-то из птиц или нет

            line.setTimer(time,factory.Return_the_Number_of_animals());

            if(bird != null)
            {
                if (bird.getClass() == Big.class) { number_of_Big++; }
                else { number_of_Small++; }

                Bird_s.Add_Bird_s(bird);

                depict_a_bird.repaint();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace(); // Печатать информации относительно исключения, т.e, как оно произошло и какой строке кода
        }
    }
}

