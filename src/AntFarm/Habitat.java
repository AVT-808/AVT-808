package AntFarm;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class Habitat extends JFrame {
    private int time;
    private int antsAmount, workersAmount, warriorsAmount;
    private Ant[] ants;
    private AbstractFactory factory;
    private JLabel timer;
    private DrawingAnts antDraw;

    Habitat() {
        super("Муравьиная ферма");
        int width = 1100, height = 700;
        setSize(width, height);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        antsAmount = 0;
        workersAmount = 0;
        warriorsAmount = 0;
        ants = new Ant[500];
        factory = new ConcreteFactory();
        time = 0;

        JPanel timerPanel = new JPanel();
        timerPanel.setLayout(new GridLayout(1, 1));
        timerPanel.setBackground(Color.lightGray);
        timerPanel.setBounds(0, 0, width, 24);

        timer = new JLabel("Таймер: 0");
        timer.setHorizontalAlignment(SwingConstants.CENTER);
        timer.setFont(new Font("Arial", Font.BOLD, 20));
        timerPanel.add(timer);
        add(timerPanel);

        antDraw = new DrawingAnts(ants);
        antDraw.setBounds(0, 24, width, height - 24);
        add(antDraw);
    }

    void timerVisibility(boolean isVisible) {
        if (isVisible)
            timer.setVisible(false);
        else
            timer.setVisible(true);
    }

    void update() {
        time++;
        timer.setText("Таймер: " + time);
        Random random = new Random();
        int xcord = random.nextInt(antDraw.getWidth() - 200);
        int ycord = random.nextInt(antDraw.getHeight() - 200);
        Ant ant = factory.createAnt(xcord, ycord, time);
        if (ant != null) {
            if (ant.getClass() == AntWorker.class)
                workersAmount++;
            else
                warriorsAmount++;
            ants[antsAmount] = ant;
            antsAmount++;
            antDraw.repaint();
        }
    }

    void stop() {
        JDialog results = new JDialog(this, "Результаты");

        JLabel timerCount = new JLabel("Время симуляции: " + time);
        timerCount.setFont(new Font("Courier New", Font.ITALIC,20));
        timerCount.setForeground(Color.PINK);
        timerCount.setHorizontalAlignment(SwingConstants.CENTER);
        results.add(timerCount);

        JLabel str = new JLabel("Появилось муравьев: " + antsAmount);
        str.setFont(new Font("Times New Roman", Font.BOLD, 18));
        str.setForeground(Color.RED);
        str.setHorizontalAlignment(SwingConstants.CENTER);
        results.add(str);

        JLabel workersCount = new JLabel("Муравьев-рабочих: " + workersAmount);
        workersCount.setFont(new Font("Arial", Font.BOLD,15));
        workersCount.setForeground(Color.GREEN);
        workersCount.setHorizontalAlignment(SwingConstants.CENTER);
        results.add(workersCount);

        JLabel warriorsCount = new JLabel("Муравьев-воинов: " + warriorsAmount);
        warriorsCount.setFont(new Font("Calibri", Font.PLAIN,14));
        warriorsCount.setForeground(Color.ORANGE);
        warriorsCount.setHorizontalAlignment(SwingConstants.CENTER);
        results.add(warriorsCount);

        results.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        results.setSize(400, 400);
        results.setLayout(new GridLayout(4,1));
        results.setModal(true);
        results.setVisible(true);

        factory.exterminate();
        time = 0;
        timer.setText("Таймер: " + time);
        for (int i = 0; i <antsAmount; i++)
            ants[i] = null;
        antsAmount = 0;
        workersAmount = 0; warriorsAmount = 0;
        antDraw.repaint();
    }
}
