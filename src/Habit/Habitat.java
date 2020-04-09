package Habit;

import DrawPanel.Depict_a_bird;
import Fact.*;
import Object.*;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Habitat extends JFrame
{
    Bird bird_s[];
    private Integer i = 0;
    private Integer Mass = 100;

    private final JLabel timer;

    private final AbstractFactory factory;
    private Integer time;

    private final Depict_a_bird depict_a_bird;
    private Integer number_of_Big;
    private Integer number_of_Small;

    public Habitat(String title, Integer time_birth_small, Integer time_birth_big, Float chance_birth_big, Float percent) {

        super(title);
        int width = 800;
        int height = 700;
        setSize(width, height);
        setResizable(true);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.time = 0;
        bird_s = new Bird [Mass];
        factory = new ConcreteFactory(time_birth_small,time_birth_big,chance_birth_big,percent);


        timer = new JLabel(factory.Return_the_Number_of_animals() + "    Время: " + time);

        JPanel labels = new JPanel();
        labels.setLayout(new GridLayout(1,1));

        labels.add(timer); // Таймер
        labels.setBounds(width-120,0, width,20);

        depict_a_bird = new Depict_a_bird(bird_s, Mass);
        depict_a_bird.setBounds(0,21, width, height);

        add(labels);
        add(depict_a_bird);

        number_of_Big=0;
        number_of_Small = 0;
    }


    public void Stop()
    {
        JLabel Result_time = new JLabel("За " + time + " секунд(ы)");

        Result_time.setFont(new Font("Times New Roman", Font.BOLD,16));
        Result_time.setForeground(Color.BLACK);
        Result_time.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel Result_small = new JLabel(" Приходили птенцы - " + number_of_Small + " ");
        Result_small.setFont(new Font("Courier New", Font.PLAIN,14));
        Result_small.setForeground(Color.RED);
        Result_small.setHorizontalAlignment(SwingConstants.LEFT);

        JLabel Result_big = new JLabel(" Прилетали взрослые - " + number_of_Big + "   ");
        Result_big.setFont(new Font("Courier New", Font.PLAIN,14));
        Result_big.setForeground(Color.RED);
        Result_big.setHorizontalAlignment(SwingConstants.LEFT);

        JDialog dialog = new JDialog(this, "Итог прогулки", false);
        dialog.setLayout(null);
        dialog.setResizable(false);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        dialog.setSize(300, 200);
        dialog.setLayout(new GridLayout(3,1));


        dialog.add(Result_time);
        dialog.add(Result_small);
        dialog.add(Result_big);
        dialog.setVisible(true);

        // Обнуление всего
        factory.Total_destruction();
        number_of_Small = 0;
        number_of_Big = 0;
        for (int j=0; j<Mass; j++) {
            bird_s[j] = null;
        }
        i = 0;
        time = 0;

        depict_a_bird.repaint();
    }


    public void Clock_yes_no(Boolean isShown){ // Есть часы или нет
        if (!isShown)
            timer.setVisible(false);
        else
            timer.setVisible(true);
    }

    void Update()
    {
        time++;
        timer.setText(factory.Return_the_Number_of_animals() + "    Время: " + time);

        Random coord = new Random();
        int x_Coord = coord.nextInt(depict_a_bird.getWidth()-50);
        int y_Coord = coord.nextInt(depict_a_bird.getHeight()-50);

        Point coordinates  = new Point(x_Coord, y_Coord);
        try
        {
            Bird bird = factory.Luntik(time, coordinates); // Возвращает какую-то из птиц или нет
            timer.setText(factory.Return_the_Number_of_animals() + "    Время: " + time);

            if(bird != null)
            {
                if (bird.getClass() == Big.class) {
                    number_of_Big++;
                }
                else {
                    number_of_Small++;
                }

                bird_s[i] = bird;
                i++;

                depict_a_bird.repaint();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

