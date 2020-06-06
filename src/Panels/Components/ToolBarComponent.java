package Panels.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class ToolBarComponent extends JToolBar {
    private final JButton startSimulation;//начать симуляцию
    private final JButton stopSimulation;//закончить симуляцию
    private final JCheckBox isInformationPanelAllowed;//чекбокс показа диалогового окна
    private final ButtonGroup timerPanelVisability;//группа кнопок
    private final JRadioButton timerAllowed;//показ таймера
    private final JRadioButton timerNotAllowed;//убрать таймр
    private final JTextField normalRabbitBirthTime;//поле для получения времени рождения
    private final JTextField whiteRabbitBirthTime;//поле для получения времени рождения
    private final JComboBox normalRabbitBirthProbability;//вероятность рождения
    private final JComboBox rabbitsPercent;// процент от общего числа кроликов
    private final JTextField normalRabbitDeathTime;
    private final JTextField whiteRabbitDeathTime;
    private final JButton showAliveObjectsInformation;
    private final JButton normalRabbitAIButton;
    private final JButton whiteRabbitAIButton;
    private final JComboBox normalRabbitThreadPriority;
    private final JComboBox whiteRabbitThreadPriority;



    public ToolBarComponent(ActionListener actionListener) {
        setFloatable(false);
        setFocusable(false);
        setLayout(new GridLayout(3,5));

        startSimulation = new JButton("Start");
        startSimulation.setMnemonic(KeyEvent.VK_B);
        startSimulation.addActionListener(actionListener);

        stopSimulation = new JButton("Stop");
        stopSimulation.setMnemonic(KeyEvent.VK_E);
        stopSimulation.setEnabled(false);
        stopSimulation.addActionListener(actionListener);

        isInformationPanelAllowed = new JCheckBox("Allow/Disallow information dialog", true);
        isInformationPanelAllowed.addActionListener(actionListener);

        timerAllowed = new JRadioButton("Show timer");
        timerAllowed.setSelected(true);
        timerAllowed.setEnabled(false);

        timerNotAllowed = new JRadioButton("Hide timer");


        timerPanelVisability = new ButtonGroup();
        timerPanelVisability.add(timerAllowed);
        timerPanelVisability.add(timerNotAllowed);

        timerAllowed.addActionListener(actionListener);
        timerNotAllowed.addActionListener(actionListener);

        JLabel normalRabbitBirthTimeLabel = new JLabel("Normal rabbit birth time (1 - 999): ");
        JLabel whiteRabbitBirthTimeLabel = new JLabel("White rabbit birth time (1 - 999): ");

        JLabel normalRabbitBirthProbabilityLabel = new JLabel("Normal rabbit birth probability: ");
        JLabel whiteRabbitPercentLabel = new JLabel("White rabbit percent of all: ");



        normalRabbitBirthTime = new JTextField();
        whiteRabbitBirthTime = new JTextField();

        Float[] probabilityArray = {0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1f};
        normalRabbitBirthProbability = new JComboBox(probabilityArray);
        Float[] rabbitsPercentArray = {0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1f};
        rabbitsPercent = new JComboBox(rabbitsPercentArray);

        var normalRabbitDeathTimeLabel = new JLabel("Normal rabbit death time (1 - 999):");
        normalRabbitDeathTime = new JTextField();
        var whiteRabbitDeathTimeLabel = new JLabel("White rabbit death time (1 - 999):");
        whiteRabbitDeathTime = new JTextField();

        showAliveObjectsInformation = new JButton("Show alive objects information");
        showAliveObjectsInformation.setEnabled(false);

        showAliveObjectsInformation.addActionListener(actionListener);

        normalRabbitAIButton = new JButton("Запуск/Остановка ИИ обычного кролика");
        normalRabbitAIButton.addActionListener(actionListener);

        whiteRabbitAIButton = new JButton("Запуск/Остановка ИИ кролика альбингоса");
        whiteRabbitAIButton.addActionListener(actionListener);


        Integer[] rabbitThreadArray = {1,2,3,4,5,6,7,8,9,10};
        var normalRabbitThreadPriorityLabel= new JLabel("Приоритет потока ИИ обычного кролика:");
        normalRabbitThreadPriority = new JComboBox(rabbitThreadArray);
        var whiteRabbitThreadPriorityLabel= new JLabel("Приоритет потока ИИ кролика альбиноса:");
        whiteRabbitThreadPriority = new JComboBox(rabbitThreadArray);



        add(normalRabbitBirthTimeLabel);
        add(normalRabbitBirthTime);
        add(whiteRabbitBirthTimeLabel);
        add(whiteRabbitBirthTime);

        add(normalRabbitBirthProbabilityLabel);
        add(normalRabbitBirthProbability);
        add(whiteRabbitPercentLabel);
        add(rabbitsPercent);



        add(normalRabbitDeathTimeLabel);
        add(normalRabbitDeathTime);
        add(whiteRabbitDeathTimeLabel);
        add(whiteRabbitDeathTime);

        add(isInformationPanelAllowed);
        add(showAliveObjectsInformation);


        add(startSimulation);
        add(stopSimulation);

        add(timerAllowed);
        add(timerNotAllowed);



        add(normalRabbitAIButton);
        add(whiteRabbitAIButton);

        add(normalRabbitThreadPriorityLabel);
        add(normalRabbitThreadPriority);
        add(whiteRabbitThreadPriorityLabel);
        add(whiteRabbitThreadPriority);

    }

    public JButton getStartSimulation() {
        return startSimulation;
    }

    public JButton getStopSimulation() {
        return stopSimulation;
    }

    public JCheckBox getIsInformationPanelAllowed() {
        return isInformationPanelAllowed;
    }

    public JRadioButton getTimerAllowed() {
        return timerAllowed;
    }

    public JRadioButton getTimerNotAllowed() {
        return timerNotAllowed;
    }

    public JTextField getNormalRabbitBirthTime() {
        return normalRabbitBirthTime;
    }

    public JTextField getWhiteRabbitBirthTime() {
        return whiteRabbitBirthTime;
    }

    public JComboBox getNormalRabbitBirthProbability() {
        return normalRabbitBirthProbability;
    }

    public JComboBox getRabbitsPercent() {
        return rabbitsPercent;
    }

    public void disableSimulationProperties(){
        startSimulation.setEnabled(false);
        normalRabbitBirthTime.setEnabled(false);
        whiteRabbitBirthTime.setEnabled(false);
        normalRabbitBirthProbability.setEnabled(false);
        rabbitsPercent.setEnabled(false);
        whiteRabbitDeathTime.setEnabled(false);
        normalRabbitDeathTime.setEnabled(false);
        showAliveObjectsInformation.setEnabled(true);
        normalRabbitThreadPriority.setEnabled(false);
        whiteRabbitThreadPriority.setEnabled(false);
    }

    public Boolean checkBirthTimeSimulationProperties() {
        Boolean check;
        check = checkTextFieldsForCorrectness(normalRabbitBirthTime);
        if(!check){
            check = checkTextFieldsForCorrectness(whiteRabbitBirthTime);
            return false;
        }
        check = checkTextFieldsForCorrectness(whiteRabbitBirthTime);
        return check;
    }

    public Boolean checkDeathTimeSimulationProperties() {
        Boolean check;
        check = checkTextFieldsForCorrectness(normalRabbitDeathTime);
        if(!check){
            check = checkTextFieldsForCorrectness(whiteRabbitDeathTime);
            return false;
        }
        check = checkTextFieldsForCorrectness(whiteRabbitDeathTime);
        return check;
    }

    private Boolean checkTextFieldsForCorrectness(JTextField checkTextField){
        try{
            Integer checkNumber = Integer.parseInt(checkTextField.getText());
            if (!(checkNumber>=1 && checkNumber<=999)) {
                checkTextField.setText("Введите корректное число!");
                return false;
            }
        }
        catch (NumberFormatException e){
            checkTextField.setText("Введите корректное число!");
            return false;
        }
        return true;
    }

    public void enableStopButton() {
        stopSimulation.setEnabled(true);
    }

    public void enableSimulationProperties() {
        startSimulation.setEnabled(true);
        normalRabbitBirthTime.setEnabled(true);
        whiteRabbitBirthTime.setEnabled(true);
        normalRabbitBirthProbability.setEnabled(true);
        rabbitsPercent.setEnabled(true);
        stopSimulation.setEnabled(false);
        normalRabbitDeathTime.setEnabled(true);
        whiteRabbitDeathTime.setEnabled(true);
        showAliveObjectsInformation.setEnabled(false);
        normalRabbitThreadPriority.setEnabled(true);
        whiteRabbitThreadPriority.setEnabled(true);
    }

    public JTextField getNormalRabbitDeathTime() {
        return normalRabbitDeathTime;
    }

    public JTextField getWhiteRabbitDeathTime() {
        return whiteRabbitDeathTime;
    }

    public JButton getShowAliveObjectsInformation() {
        return showAliveObjectsInformation;
    }

    public JButton getNormalRabbitAIButton() {
        return normalRabbitAIButton;
    }

    public JButton getWhiteRabbitAIButton() {
        return whiteRabbitAIButton;
    }

    public JComboBox getNormalRabbitThreadPriority() {
        return normalRabbitThreadPriority;
    }

    public JComboBox getWhiteRabbitThreadPriority() {
        return whiteRabbitThreadPriority;
    }
}
