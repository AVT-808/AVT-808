package AntFarm;

import javax.swing.*;
import java.awt.event.*;
import java.util.Timer;

public class Control extends KeyAdapter implements ActionListener
{
    private Habitat antFarm;
    private CurrentObjects obj;
    private Console console;
    private Configuration configuration;
    private Serialization serializer;
    private Client client;
    private WorkerAI workerAI;
    private WarriorAI warriorAI;
    protected MyTimerTask myTimerTask;
    protected Timer timer;
    static boolean isStarted;
    private boolean isTimerVisible;
    int workerPeriod, warriorPeriod, workerLifetime, warriorLifetime;
    double workerProbability, warriorProbability;
    int mainPriority, workerPriority, warriorPriority;

    Control()
    {
        antFarm = new Habitat(this);
        antFarm.setVisible(true);
        antFarm.addKeyListener(this);
        configuration = new Configuration();
        configuration.load(antFarm);
        workerAI = new WorkerAI();
        warriorAI = new WarriorAI();
        isStarted = false;
        isTimerVisible = true;
        client = new Client();
        if (!antFarm.getWorkerAI().isSelected())
            workerAI.stopAI();
        if (!antFarm.getWarriorAI().isSelected())
            warriorAI.stopAI();
        serializer = new Serialization();
        antFarm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) { configuration.save(antFarm);
            }
        });
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
        if (action == antFarm.getButtonObjects())
        {
            obj = new CurrentObjects(Singleton.getBirthTime());
            obj.setVisible(true);
        }
        if (action == antFarm.getButtonConsole()) {
            console = new Console(antFarm);
            console.setVisible(true);
            console.ConsoleOperation(this);
        }
        if (action == antFarm.getButtonSave()) {
            serializer.serialize();
        }
        if (action == antFarm.getButtonLoad()) {
            if (isStarted)
                stopSimulation();
            serializer.deserialize(antFarm);
            antFarm.repaint();
        }
        if (action == antFarm.getButtonServer())
        {
            client.getAnts(antFarm);
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
        if (action == antFarm.getWorkerAI())
        {
            if (antFarm.getWorkerAI().isSelected())
                workerAI.startAI();
            else workerAI.stopAI();
        }
        if(action == antFarm.getWarriorAI())
        {
            if (antFarm.getWarriorAI().isSelected())
                warriorAI.startAI();
            else warriorAI.stopAI();
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
            workerLifetime = Integer.parseInt(antFarm.getWorkerLifetime().getText());
            warriorLifetime = Integer.parseInt(antFarm.getWarriorLifetime().getText());
            workerProbability = (double)(antFarm.getWorkerProbability().getSelectedItem());
            warriorProbability = (double)(antFarm.getWarriorProbability().getSelectedItem());
            antFarm.factory.setParameters(workerPeriod, warriorPeriod, workerProbability, warriorProbability, workerLifetime, warriorLifetime);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Введено некорректное значение. Установлены значения по умолчанию.", "Ошибка!", JOptionPane.ERROR_MESSAGE);
            workerPeriod = 20;
            warriorPeriod = 7;
            workerLifetime = 50;
            warriorLifetime = 30;
            workerProbability = 1.0;
            warriorProbability = 0.8;
            antFarm.factory.setParameters(workerPeriod, warriorPeriod, workerProbability, warriorProbability, workerLifetime, warriorLifetime);
            antFarm.getWorkerPeriod().setText(Integer.toString(workerPeriod));
            antFarm.getWarriorPeriod().setText(Integer.toString(warriorPeriod));
            antFarm.getWorkerLifetime().setText(Integer.toString(workerLifetime));
            antFarm.getWarriorLifetime().setText(Integer.toString(warriorLifetime));
            antFarm.getWorkerProbability().setSelectedItem(workerProbability);
            antFarm.getWarriorProbability().setSelectedItem(warriorProbability);
        }
        mainPriority = (int)antFarm.getMainThread().getSelectedItem();
        workerPriority = (int)antFarm.getWorkerThread().getSelectedItem();
        warriorPriority = (int)antFarm.getWarriorThread().getSelectedItem();
        Thread.currentThread().setPriority(mainPriority);
        workerAI.setPriority(workerPriority);
        warriorAI.setPriority(warriorPriority);
        if (!workerAI.isMoving && antFarm.getWorkerAI().isSelected())
            workerAI.startAI();
        if (!warriorAI.isMoving && antFarm.getWarriorAI().isSelected())
            warriorAI.startAI();
        if (!antFarm.getWorkerAI().isSelected())
            workerAI.stopAI();
        if (!antFarm.getWarriorAI().isSelected())
            warriorAI.stopAI();
        isStarted = true;
        timer = new Timer();
        myTimerTask = new MyTimerTask(antFarm);
        timer.schedule(myTimerTask, 0, 1000);
        antFarm.getButtonStart().setEnabled(false);
        antFarm.getButtonStop().setEnabled(true);
        antFarm.getWorkerPeriod().setEnabled(false);
        antFarm.getWarriorPeriod().setEnabled(false);
        antFarm.getWorkerLifetime().setEnabled(false);
        antFarm.getWarriorLifetime().setEnabled(false);
        antFarm.getWorkerProbability().setEnabled(false);
        antFarm.getWarriorProbability().setEnabled(false);
        antFarm.getMainThread().setEnabled(false);
        antFarm.getWorkerThread().setEnabled(false);
        antFarm.getWarriorThread().setEnabled(false);
    }

    public void stopSimulation()
    {
        workerAI.stopAI(); warriorAI.stopAI();
        boolean isOver = antFarm.stop();
        if (isOver)
        {
            antFarm.getButtonStart().setEnabled(true);
            antFarm.getButtonStop().setEnabled(false);
            antFarm.getWorkerPeriod().setEnabled(true);
            antFarm.getWarriorPeriod().setEnabled(true);
            antFarm.getWorkerLifetime().setEnabled(true);
            antFarm.getWarriorLifetime().setEnabled(true);
            antFarm.getWorkerProbability().setEnabled(true);
            antFarm.getWarriorProbability().setEnabled(true);
            antFarm.getMainThread().setEnabled(true);
            antFarm.getWorkerThread().setEnabled(true);
            antFarm.getWarriorThread().setEnabled(true);
            timer.cancel();
            timer.purge();
            myTimerTask.cancel();
            Client.disconnect();
            isStarted = false;
        }
        else {
            if (antFarm.getWorkerAI().isSelected())
                workerAI.startAI();
            if (antFarm.getWarriorAI().isSelected())
                warriorAI.startAI();
        }
    }

    public boolean checkParameters() {
        try
        {
            Integer.parseInt(antFarm.getWorkerPeriod().getText());
            Integer.parseInt(antFarm.getWarriorPeriod().getText());
            Integer.parseInt(antFarm.getWorkerLifetime().getText());
            Integer.parseInt(antFarm.getWarriorLifetime().getText());
        }
        catch (NumberFormatException e)
        {
            return false;
        }
        return true;
    }
}
