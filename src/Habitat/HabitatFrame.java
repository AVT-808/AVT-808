package Habitat;

import Habitat.RabbitList.Singleton;
import Panels.DrawRabbitPanel;
import Factory.AbstractFactory;
import Factory.ConcreteFactory;
import Models.Abstract.BaseRabbit;
import Models.WhiteRabbit;
import Panels.InformationDialog;
import Panels.InformationPanel;
import Panels.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Random;

public class HabitatFrame extends JFrame {

    private final AbstractFactory factory;
    private final Singleton rabbits;
    private Integer time;
    private final DrawRabbitPanel drawRabbit;
    private final MenuPanel menuPanel;
    private final InformationPanel informationPanel;
    private Integer whiteRabbitsAmount;
    private Boolean isInformationPanelAllowed;

    public HabitatFrame(String title, ActionListener actionListener) {
        super(title);
        int width = 1200;
        int height = 800;
        setSize(width, height);
        setResizable(false);
        setFocusable(true);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.time = 0;
        rabbits = Singleton.getInstance();
        factory = new ConcreteFactory();

        isInformationPanelAllowed = true;

        menuPanel = new MenuPanel(width, actionListener);
        menuPanel.setMaximumSize(new Dimension(width, 110));
        informationPanel = new InformationPanel();
        informationPanel.setMaximumSize(new Dimension(width, 20));
        drawRabbit = new DrawRabbitPanel();
        drawRabbit.setMaximumSize(new Dimension(width, height - 130));

        add(menuPanel);
        add(informationPanel);
        add(drawRabbit);

        whiteRabbitsAmount=0;
    }

    public void stop(){//остановить отрисовку среды
        if(isInformationPanelAllowed) {
            InformationDialog informationDialog = new InformationDialog(this,"Информация",time,factory.getAmountOfBirth() - whiteRabbitsAmount,whiteRabbitsAmount);
            informationDialog.viewInformation();
        }
        factory.destroy();
        rabbits.clearRabbits();
        time = 0;

        informationPanel.setRabbitsAmount(factory.getAmountOfBirth());
        informationPanel.setTimer(time);
        drawRabbit.repaint();
    }
    
    public void timerVisibility(Boolean isShown){//скрыть таймер
        if (isShown)
            informationPanel.setTimerVisibility(false);
        else
            informationPanel.setTimerVisibility(true);
    }

    void update() {//продолжить симуляцию
        time++;
        informationPanel.setTimer(time);
        Random coordinatesRandom = new Random();
        int xCoordinate = coordinatesRandom.nextInt(drawRabbit.getWidth()-100);
        int yCoordinate = coordinatesRandom.nextInt(drawRabbit.getHeight()-100);

        Point coordinates  = new Point(xCoordinate, yCoordinate);

        try{
            BaseRabbit rabbit = factory.birth(time, coordinates);
            informationPanel.setRabbitsAmount(factory.getAmountOfBirth());
            if(rabbit != null) {
                if (rabbit.getClass() == WhiteRabbit.class)
                    whiteRabbitsAmount++;
                rabbits.addRabbit(rabbit);
                drawRabbit.repaint();
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public JButton getStartSimulationButton(){
        return menuPanel.getSimulationButton();
    }

    public void disableSimulationProperties(){
        menuPanel.disableSimulationProperties();
    }

    public Boolean checkSimulationProperties() {
        return menuPanel.checkSimulationProperties();
    }

    public Integer getNormalRabbitBirthTime() {
        return menuPanel.getNormalRabbitBirthTime();
    }

    public Integer getWhiteRabbitBirthTime() {
        return menuPanel.getWhiteRabbitBirthTime();
    }

    public Float getNormalRabbitBirthProbability() {
        return menuPanel.getNormalRabbitBirthProbability();
    }

    public Float getRabbitPercent() {
        return menuPanel.getRabbitPercent();
    }

    public void setSimulationProperties(Integer normalRabbitBirthTime, Integer whiteRabbitBirthTime, Float normalRabbitBirthProbability, Float rabbitPercent) {
        factory.setSimulationProperties(normalRabbitBirthTime, whiteRabbitBirthTime, normalRabbitBirthProbability, rabbitPercent);
    }

    public void enableStopButton() {
        menuPanel.enableStopButton();
    }

    public void enableSimulationProperties() {
        menuPanel.enableSimulationProperties();
    }

    public JButton getStopSimulationButton() {
        return menuPanel.getStopSimulationButton();
    }

    public JRadioButton getHideTimerRadioButton() {
        return menuPanel.getHideTimerRadioButton();
    }

    public JRadioButton getShowTimerRadioButton() {
        return menuPanel.getShowTimerRadioButton();
    }

    public JCheckBox getInformationDialogSelecter() {
        return menuPanel.getInformationDialogSelecter();
    }

    public void changeInformationPanelVisibility() {
        isInformationPanelAllowed=!isInformationPanelAllowed;
    }

    public JMenuItem getMenuStartStopButton() {
        return menuPanel.getMenuStartStopButton();
    }

    public JMenuItem getMenuShowHideTimerButton() {
        return menuPanel.getMenuShowHideTimerButton();
    }

    public JMenuItem getMenuShowHideInformationDialog() {
        return menuPanel.getMenuShowHideInformationDialog();
    }
}

