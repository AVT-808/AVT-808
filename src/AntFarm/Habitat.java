package AntFarm;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Random;

public class Habitat extends JFrame {
    private int time;
    private int antsAmount, workersAmount, warriorsAmount;
    private Singleton ants;
    protected AbstractFactory factory;
    private GUI gui;
    private boolean isInformationShowing;
    private boolean isOver, isPaused;

    Habitat(ActionListener actionListener)
    {
        super("Муравьиная ферма");
        int width = 1100, height = 700;
        setSize(width, height);
        setFocusable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        antsAmount = 0;
        workersAmount = 0;
        warriorsAmount = 0;
        ants = Singleton.getSingleton();
        factory = new ConcreteFactory();
        time = 0;
        isInformationShowing = true;
        isOver = true; isPaused = false;

        gui = new GUI(actionListener);
        add(gui);
    }

    void timerVisibility(boolean isTimerVisible) {
        if (isTimerVisible)
            gui.timer.setVisible(false);
        else
            gui.timer.setVisible(true);
    }

    void informationShowing()
    {
        if (isInformationShowing)
            gui.getInfoVisibility().setSelected(false);
        else
            gui.getInfoVisibility().setSelected(true);
        isInformationShowing = !isInformationShowing;
    }

    void update() {
        if (!isPaused)
        {
            time++;
            gui.timer.setText("Таймер: " + time);
            Random random = new Random();
            int xcord = random.nextInt(gui.draw.getWidth() - 200);
            int ycord = random.nextInt(gui.draw.getHeight() - 200);
            Ant ant = factory.createAnt(xcord, ycord, time);
            if (ant != null) {
                if (ant.getClass() == AntWorker.class)
                    workersAmount++;
                else
                    warriorsAmount++;
                ants.addAnt(ant);
                antsAmount++;
                gui.draw.repaint();
            }
        }
        else
        {
            gui.draw.repaint();
        }
    }

    boolean stop() {
        if (isInformationShowing) {
            isPaused = true;
            switch(JOptionPane.showConfirmDialog(null, "Время симуляции: " + time + "\nПоявилось муравьев: "
                            + antsAmount + "\nМуравьев-рабочих: " + workersAmount + "\nМуравьев-воинов: " +
                    warriorsAmount, "Результаты", JOptionPane.OK_CANCEL_OPTION)) {
                case JOptionPane.OK_OPTION: {
                    factory.exterminate();
                    isPaused = false;
                    isOver = true;
                    time = 0;
                    gui.timer.setText("Таймер: " + time);
                    ants.clearAnts();
                    antsAmount = 0;
                    workersAmount = 0; warriorsAmount = 0;
                    gui.draw.repaint();
                    return isOver;
                }
                case JOptionPane.CANCEL_OPTION:
                    {
                        isOver = false;
                        isPaused = false;
                        return isOver;
                    }
                default:
                {
                    isOver = false;
                    isPaused = false;
                    return false;
                }
            }
        }
        else {
            factory.exterminate();
            isOver = true;
            System.out.println(isOver);
            time = 0;
            gui.timer.setText("Таймер: " + time);
            ants.clearAnts();
            antsAmount = 0;
            workersAmount = 0; warriorsAmount = 0;
            gui.draw.repaint();
        }
        return true;
    }

    public JButton getButtonStart()
    {
        return gui.getButtonStart();
    }

    public JButton getButtonStop()
    {
        return gui.getButtonStop();
    }

    public JCheckBox getInformationVisibility()
    {
        return gui.getInfoVisibility();
    }

    public JRadioButton getTimerIsVisible()
    {
        return gui.getTimerIsVisible();
    }

    public JRadioButton getTimerNotVisible()
    {
        return gui.getTimerNotVisible();
    }

    public JTextField getWorkerPeriod()
    {
        return gui.getWorkerPeriod();
    }

    public JTextField getWarriorPeriod()
    {
        return gui.getWarriorPeriod();
    }

    public JComboBox getWorkerProbability()
    {
        return gui.getWorkerProbability();
    }

    public JComboBox getWarriorProbability()
    {
        return gui.getWarriorProbability();
    }

    public JMenuItem getMenuStart()
    {
        return gui.getMenuStart();
    }

    public JMenuItem getMenuStop()
    {
        return gui.getMenuStop();
    }

    public JMenuItem getMenuTimerVisible()
    {
        return gui.getMenuTimerVisible();
    }

    public JMenuItem getMenuInformationVisible()
    {
        return gui.getMenuInformationVisible();
    }
}
