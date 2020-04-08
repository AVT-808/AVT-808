package Panels.Components;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ToolBarComponent extends JToolBar {
    private final JButton startSimulation;
    private final JButton stopSimulation;
    private final JCheckBox isInformationPanelAllowed;
    private final ButtonGroup timerPanelVisability;
    private final JRadioButton timerAllowed;
    private final JRadioButton timerNotAllowed;
    private final JTextField normalRabbitBirthTime;
    private final JTextField whiteRabbitBirthTime;
    private final JComboBox normalRabbitBirthProbability;
    private final JComboBox rabbitsPercent;
    private Float[] probabilityArray = {0.1f,0.2f,0.3f,0.4f,0.5f,0.6f,0.7f,0.8f,0.9f,1f};
    private Float[] rabbitsPercentArray = {0.1f,0.2f,0.3f,0.4f,0.5f,0.6f,0.7f,0.8f,0.9f,1f};


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

        normalRabbitBirthProbability = new JComboBox(probabilityArray);
        rabbitsPercent = new JComboBox(rabbitsPercentArray);


        add(normalRabbitBirthTimeLabel);
        add(whiteRabbitBirthTimeLabel);

        add(normalRabbitBirthProbabilityLabel);
        add(whiteRabbitPercentLabel);


        add(isInformationPanelAllowed);

        add(normalRabbitBirthTime);
        add(whiteRabbitBirthTime);

        add(normalRabbitBirthProbability);
        add(rabbitsPercent);
        add(new JLabel(""));
        add(startSimulation);
        add(stopSimulation);

        add(timerAllowed);
        add(timerNotAllowed);

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

    public ButtonGroup getTimerPanelVisibility() {
        return timerPanelVisability;
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
    }

    public Boolean checkSimulationProperties() {
        Boolean check;
        check = checkTextFieldsForCorrectness(normalRabbitBirthTime);
        if(!check){
            check = checkTextFieldsForCorrectness(whiteRabbitBirthTime);
            return false;
        }
        check = checkTextFieldsForCorrectness(whiteRabbitBirthTime);
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
    }

}
