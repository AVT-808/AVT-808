import Habitat.HabitatFrame;
import Habitat.HabitatTask;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;

class Simulation extends KeyAdapter implements ActionListener{
    private Timer timer;
    private HabitatTask habitatTask;
    private final HabitatFrame habitat;
    private Boolean isStarted;
    private Boolean isShown;

    private Simulation() {
        habitat = new HabitatFrame("Habitat",this);
        habitat.setVisible(true);
        habitat.addKeyListener(this);
        isStarted = false;
        isShown = true;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();
        if(key == KeyEvent.VK_B && !isStarted) {
            startSimulation();
        }
        if (key == KeyEvent.VK_E && isStarted) {
           stopSimulation();
        }
        if(key == KeyEvent.VK_T) {
           changeTimerVisibility();
        }
    }

    public static void main(String[] args) {
        new Simulation();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == habitat.getStartSimulationButton()){
            startSimulation();
        }

        if(e.getSource() == habitat.getStopSimulationButton()){
            stopSimulation();
        }

        if(e.getSource() == habitat.getHideTimerRadioButton() || e.getSource() == habitat.getShowTimerRadioButton()) {
            changeTimerVisibility();
        }

        if(e.getSource() == habitat.getInformationDialogSelecter()) {
            habitat.changeInformationPanelVisibility();
        }

        if(e.getSource() == habitat.getMenuStartStopButton()) {
            if(!isStarted) {
                startSimulation();
            }
            else {
                stopSimulation();
            }
        }

        if(e.getSource() == habitat.getMenuShowHideTimerButton()) {
            changeTimerVisibility();
        }

        if(e.getSource() == habitat.getMenuShowHideInformationDialog()) {
            habitat.changeInformationPanelVisibility();
            habitat.getInformationDialogSelecter().setSelected(!habitat.getInformationDialogSelecter().isSelected());
        }

        if(e.getSource() == habitat.getShowAliveObjectsInformation()){
            timer.cancel();
            habitatTask.cancel();
            timer.purge();

            habitat.showAliveObjectsInformation();
            timer = new Timer();
            habitatTask = new HabitatTask(habitat);
            timer.schedule(habitatTask,0,1000);

        }
        habitat.requestFocus();
    }

    private void startSimulation(){
        habitat.disableSimulationProperties();
        var isBirthPropertiesCorrect = habitat.checkBirthSimulationProperties();
        var isDeathPropertiesCorrect = habitat.checkDeathSimulationProperties();
        if(isBirthPropertiesCorrect && isDeathPropertiesCorrect){
            Integer normalRabbitBirthTime = habitat.getNormalRabbitBirthTime();
            Integer whiteRabbitBirthTime = habitat.getWhiteRabbitBirthTime();
            Float normalRabbitBirthProbability = habitat.getNormalRabbitBirthProbability();
            Float rabbitPercent = habitat.getRabbitPercent();
            Integer normalRabbitDeathTime = habitat.getNormalRabbitDeathTime();
            Integer whiteRabbitDeathTime = habitat.getWhiteRabbitDeathTime();

            habitat.setSimulationProperties(normalRabbitBirthTime, whiteRabbitBirthTime, normalRabbitBirthProbability, rabbitPercent, normalRabbitDeathTime, whiteRabbitDeathTime);
            startSimulationTask();
            habitat.enableStopButton();
        }
        else {
            habitat.enableSimulationProperties();
        }
    }

    private void stopSimulation(){
        stopSimulationTask();
        habitat.enableSimulationProperties();
    }

    private void startSimulationTask(){
        isStarted = true;
        timer = new Timer();
        habitatTask = new HabitatTask(habitat);
        timer.schedule(habitatTask,0,1000);
    }

    private void stopSimulationTask(){
        timer.cancel();
        habitatTask.cancel();
        timer.purge();
        habitat.stop();
        isStarted = false;
    }

    private void changeTimerVisibility(){
        habitat.timerVisibility(isShown);
        if(isShown){
            habitat.getShowTimerRadioButton().setEnabled(true);
            habitat.getHideTimerRadioButton().setEnabled(false);
            habitat.getHideTimerRadioButton().setSelected(true);

        }
        else {
            habitat.getShowTimerRadioButton().setEnabled(false);
            habitat.getHideTimerRadioButton().setEnabled(true);
            habitat.getShowTimerRadioButton().setSelected(true);
        }
        isShown = !isShown;

    }
}
