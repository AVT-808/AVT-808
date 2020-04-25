package AntFarm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;

public class Main extends KeyAdapter implements ActionListener {
    private Habitat antFarm;
    protected MyTimerTask myTimerTask;
    protected Timer timer;
    private boolean isStarted, isTimerVisible;
    int workerPeriod, warriorPeriod;
    double workerProbability, warriorProbability;

    public static void main(String[] args)
    {
        new Main();
    }

    Main() {
        antFarm = new Habitat(this);
        antFarm.setVisible(true);
        antFarm.addKeyListener(this);
        isStarted = false;
        isTimerVisible = true;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();
        if (key == KeyEvent.VK_B && !isStarted) {
            startSimulation();
        }
        if (key == KeyEvent.VK_E && isStarted) {
            stopSimulation();
        }
        if (key == KeyEvent.VK_T) {
            antFarm.timerVisibility(isTimerVisible);
            if (isTimerVisible) {
                antFarm.getTimerIsVisible().setEnabled(true);
                antFarm.getTimerNotVisible().setEnabled(false);
                antFarm.getTimerNotVisible().setSelected(true);
            } else {
                antFarm.getTimerIsVisible().setEnabled(false);
                antFarm.getTimerIsVisible().setSelected(true);
                antFarm.getTimerNotVisible().setEnabled(true);
            }
            isTimerVisible = !isTimerVisible;
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object action = actionEvent.getSource();
        if (action == antFarm.getButtonStart()) {
            if (!isStarted)
                startSimulation();
        }
        if (action == antFarm.getButtonStop())
        {
            if (isStarted)
                stopSimulation();
        }
        if (action == antFarm.getInformationVisibility()) {
            antFarm.informationShowing();
        }
        if (action == antFarm.getTimerIsVisible() || action == antFarm.getTimerNotVisible()) {
            antFarm.timerVisibility(isTimerVisible);
            if (isTimerVisible) {
                antFarm.getTimerIsVisible().setEnabled(true);
                antFarm.getTimerNotVisible().setEnabled(false);
                antFarm.getTimerNotVisible().setSelected(true);
            } else {
                antFarm.getTimerIsVisible().setEnabled(false);
                antFarm.getTimerIsVisible().setSelected(true);
                antFarm.getTimerNotVisible().setEnabled(true);
            }
            isTimerVisible = !isTimerVisible;
        }
        if (action == antFarm.getMenuStart()) {
            if (!isStarted)
                startSimulation();
        }
        if (action == antFarm.getMenuStop()) {
            if (isStarted)
                stopSimulation();
        }
        if (action == antFarm.getMenuTimerVisible()) {
            antFarm.timerVisibility(isTimerVisible);
            if (isTimerVisible) {
                antFarm.getTimerIsVisible().setEnabled(true);
                antFarm.getTimerNotVisible().setEnabled(false);
                antFarm.getTimerNotVisible().setSelected(true);
            } else {
                antFarm.getTimerIsVisible().setEnabled(false);
                antFarm.getTimerIsVisible().setSelected(true);
                antFarm.getTimerNotVisible().setEnabled(true);
            }
            isTimerVisible = !isTimerVisible;
        }
        if (action == antFarm.getMenuInformationVisible()) {
            antFarm.informationShowing();
        }
        antFarm.requestFocus();
    }

    public void startSimulation()
    {
        if (checkParameters())
        {
            workerPeriod = Integer.parseInt(antFarm.getWorkerPeriod().getText());
            warriorPeriod = Integer.parseInt(antFarm.getWarriorPeriod().getText());
            workerProbability = (double)(antFarm.getWorkerProbability().getSelectedItem());
            warriorProbability = (double)(antFarm.getWarriorProbability().getSelectedItem());
            antFarm.factory.setParameters(workerPeriod, warriorPeriod, workerProbability, warriorProbability);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Введено некорректное значение. Установлены значения по умолчанию.", "Ошибка!", JOptionPane.ERROR_MESSAGE);
            workerPeriod = 5;
            warriorPeriod = 3;
            workerProbability = 0.9;
            warriorProbability = 0.7;
            antFarm.factory.setParameters(workerPeriod, warriorPeriod, workerProbability, warriorProbability);
            antFarm.getWorkerPeriod().setText(Integer.toString(workerPeriod));
            antFarm.getWarriorPeriod().setText(Integer.toString(warriorPeriod));
            antFarm.getWorkerProbability().setSelectedItem(workerProbability);
            antFarm.getWarriorProbability().setSelectedItem(warriorProbability);
        }
        //System.out.println(workerPeriod + " " + warriorPeriod + " " + workerProbability + " " + warriorProbability);
        isStarted = true;
        timer = new Timer();
        myTimerTask = new MyTimerTask(antFarm);
        timer.schedule(myTimerTask, 0, 1000);
        antFarm.getButtonStart().setEnabled(false);
        antFarm.getButtonStop().setEnabled(true);
        antFarm.getWorkerPeriod().setEnabled(false);
        antFarm.getWarriorPeriod().setEnabled(false);
        antFarm.getWorkerProbability().setEnabled(false);
        antFarm.getWarriorProbability().setEnabled(false);
    }

    public void stopSimulation()
    {
        boolean isOver = antFarm.stop();
        if (isOver)
        {
            antFarm.getButtonStart().setEnabled(true);
            antFarm.getButtonStop().setEnabled(false);
            antFarm.getWorkerPeriod().setEnabled(true);
            antFarm.getWarriorPeriod().setEnabled(true);
            antFarm.getWorkerProbability().setEnabled(true);
            antFarm.getWarriorProbability().setEnabled(true);
            timer.cancel();
            timer.purge();
            myTimerTask.cancel();
            isStarted = false;
        }
    }

    public boolean checkParameters() {
        try
        {
            Integer.parseInt(antFarm.getWorkerPeriod().getText());
        }
        catch (NumberFormatException e)
        {
            return false;
        }
        try {
            Integer.parseInt(antFarm.getWarriorPeriod().getText());
        }
        catch (NumberFormatException e)
        {
            return false;
        }
        return true;
    }
}
