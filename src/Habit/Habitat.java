package Habit;

import Menu.*;
import Contr.*;
import Array.Singleton;
import DrawPanel.DepictBird;
import Fact.*;
import Object.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Random;

public class Habitat {

    private final Singleton Bird_s;
    private final AbstractFactory factory;
    private Integer time;
    private final DepictBird depict_a_bird;

    private Integer number_of_Big = 0;
    private Integer number_of_Small = 0;

    private final Line line;

    private final Men men;

    Integer identifier;
   public JFrame jFrame;

    //*********************************//

    public Habitat() {

        jFrame = new JFrame("Field");
        int width = 1250;
        int height = 650;
        jFrame.setSize(width, height);
        jFrame.setResizable(true); // Размеры окна: false - не изменять

        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Чтобы при закрытии окна закрывалась и программа, иначе она останется висеть в процессах

        this.time = 0;
        Bird_s = Singleton.getM();
        factory = new ConcreteFactory();

        men = new Men(this);
        men.setMaximumSize(new Dimension(width, 50));

        depict_a_bird = new DepictBird();
        depict_a_bird.setMaximumSize(new Dimension(width, height-21 ));

        line = new Line();
        line.setMaximumSize(new Dimension(width, 20));

        jFrame.add(men);
        jFrame.add(depict_a_bird);
        jFrame.add(line);

        jFrame.setFocusable(true);
    }

    //*********************************//

    public void Stop() // Остановить отрисовку среды
    {
        Boolean ddd = true;

        Boolean d = men.Return_nagatost(); // Показывать инфу или нет

        if (d) { // d==true

            Inf inf =new Inf(jFrame,time,number_of_Small,number_of_Big);
            inf.setVisible(true);
            ddd = inf.Return_ddd(); // Определяется нажали "Ок" или "Отмена"
        }

        // Обнуление всего
      if (ddd) {

          factory.Total_destruction();

          number_of_Small = 0;
          number_of_Big = 0;
          time = 0;
          Bird_s.Destruction_Bird_s();
          Bird_s.Destruction_hashMap();
          Bird_s.Destruction_treeSet();

          depict_a_bird.repaint();
      }
    }


    //*********************************//

    void Update() {

        time++;

        line.setTimer(time,factory.Return_the_Number_of_animals());

        Random coord = new Random(); // Расположение птицы
        int x_Coord = coord.nextInt(depict_a_bird.getWidth()-100);
        int y_Coord = coord.nextInt(depict_a_bird.getHeight()-100);

        Point coordinates  = new Point(x_Coord, y_Coord);

        jFrame.requestFocus(); // Передает фокус на поле

        try {
            identifier = coord.nextInt(100);

            Bird bird = factory.Luntik(time, coordinates, men, identifier); // Возвращает какую-то из птиц или нет
            line.setTimer(time,factory.Return_the_Number_of_animals());

            if(bird != null) {

                if (bird.getClass() == Big.class) { number_of_Big++; } // Изменить счетчик
                else { number_of_Small++; }

                Bird_s.Add_Bird_s(bird); // Добавить объект

                while (Bird_s.treeSet.contains(identifier)) { //  Если элемент найден, он возвращает true, в противном случае false
                  identifier = coord.nextInt(100); // Делаем для того, чтобы номера не повторились
                }

                Bird_s.Add_identifier(identifier); // Добавить ключ


                Bird_s.Put_hashMap(identifier,time); // Добавить в таблицу

                depict_a_bird.repaint();
            }

        }

        catch (Exception ex) {
            ex.printStackTrace(); // Печатать информации относительно исключения, т.e, как оно произошло и какой строке кода
        }

        ////////////////////////////////

        for (Bird bird : Singleton.getBird_s()) { // Удаляем объекты, время которых вышло

            if (bird.go_away == time) {

                Integer key = bird.identifier;

                Bird_s.hashMap_remove(key);
                Bird_s.treeSet_remove(key);
                Bird_s.Bird_s_remove(bird);

                depict_a_bird.repaint();
                break;
            }
        }
    }

    ////////////////////////////////
    public HashMap<Integer,Integer> Return_hashMap() { return Bird_s.Return_hashMap(); } // Связывает Singleton и OnTheField
}

