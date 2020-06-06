package Habitat;

import Behaviour.Abstract.BaseAI;
import Habitat.RabbitList.Singleton;
import Models.NormalRabbit;
import Models.WhiteRabbit;
import Panels.*;
import Factory.AbstractFactory;
import Factory.ConcreteFactory;
import Models.Abstract.BaseRabbit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class HabitatFrame extends JFrame {

    private final AbstractFactory factory;
    private final Singleton rabbits;
    private static Integer time;
    private final DrawRabbitPanel drawRabbit;
    private final MenuPanel menuPanel;
    private final InformationPanel informationPanel;
    private Boolean isInformationPanelAllowed;
    private Integer normalRabbitBirthTime;
    private Integer whiteRabbitBirthTime;
    private Float normalRabbitBirthProbability;
    private Float rabbitPercent;
    private Integer normalRabbitDeathTime;
    private Integer whiteRabbitDeathTime;

    private Integer whiteRabbitsAmount;
    private Integer normalRabbitsAmount;
    private Integer rabbitsAmount;


    public HabitatFrame(String title, ActionListener actionListener) {
        super(title);
        int width = 1600;
        int height = 800;
        setSize(width, height);
        setResizable(false);
        setFocusable(true);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        whiteRabbitsAmount = 0;
        normalRabbitsAmount = 0;
        rabbitsAmount=0;
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

    }

    public void stop(){//остановить отрисовку среды
        if(isInformationPanelAllowed) {
            EndSimulationDialog endSimulationDialog = new EndSimulationDialog(this,"Информация",time, normalRabbitsAmount, whiteRabbitsAmount);
            endSimulationDialog.viewInformation();
        }
        whiteRabbitsAmount=0;
        normalRabbitsAmount=0;
        rabbitsAmount=0;
        rabbits.clearRabbits();
        time = 0;

        informationPanel.setRabbitsAmount(rabbitsAmount);
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
        clearDeathRabbits(time);
        informationPanel.setTimer(time);
        Random coordinatesRandom = new Random();


        try{
            if(time%normalRabbitBirthTime==0)
            {
                int xCoordinate = coordinatesRandom.nextInt(drawRabbit.getWidth()-100);
                int yCoordinate = coordinatesRandom.nextInt(drawRabbit.getHeight()-100);

                Point coordinates  = new Point(xCoordinate, yCoordinate);
                BaseRabbit rabbit = birthNormalRabbit(coordinates,time,normalRabbitDeathTime);
                if(rabbit != null) {
                    rabbits.addRabbit(rabbit);
                }
            }
            if (time%whiteRabbitBirthTime==0)
            {
                int xCoordinate = coordinatesRandom.nextInt(drawRabbit.getWidth()-100);
                int yCoordinate = coordinatesRandom.nextInt(drawRabbit.getHeight()-100);

                Point coordinates  = new Point(xCoordinate, yCoordinate);
                BaseRabbit rabbit = birthWhiteRabbit(coordinates,time,whiteRabbitDeathTime);
                if(rabbit != null) {
                    rabbits.addRabbit(rabbit);
                }

            }
            informationPanel.setRabbitsAmount(rabbitsAmount);
            drawRabbit.repaint();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }


    private BaseRabbit birthNormalRabbit(Point coordinates, Integer birthTime, Integer deathTime) throws IOException {
        Random random = new Random();
        float probability = random.nextFloat();
        if (probability <= normalRabbitBirthProbability) {
            BaseRabbit rabbit = factory.birthNormalRabbit(coordinates,birthTime,deathTime);
            rabbitsAmount++;
            normalRabbitsAmount++;
            return rabbit;
        }
        return null;
    }

    private BaseRabbit birthWhiteRabbit(Point coordinates, Integer birthTime, Integer deathTime) throws IOException {
        if(whiteRabbitsAmount<rabbitsAmount*rabbitPercent) {
            BaseRabbit rabbit = factory.birthWhiteRabbit(coordinates,birthTime,deathTime);
            rabbitsAmount++;
            whiteRabbitsAmount++;
            return rabbit;
        }
        return null;
    }

    private void clearDeathRabbits(Integer time){
        var listToDelete = new ArrayList<BaseRabbit>();
        for (var rabbit: rabbits.getRabbits()) {

            if(rabbit instanceof WhiteRabbit && time - rabbit.getBirthTime() == whiteRabbitDeathTime)
            {
                listToDelete.add(rabbit);
                whiteRabbitsAmount--;
                rabbitsAmount--;
            }
            if(rabbit instanceof NormalRabbit && time - rabbit.getBirthTime() == normalRabbitDeathTime)
            {
                listToDelete.add(rabbit);
                normalRabbitsAmount--;
                rabbitsAmount--;
            }
        }
        for (var rabbit: listToDelete) {
            rabbits.clearRabbit(rabbit);
        }
    }

    public JButton getStartSimulationButton(){
        return menuPanel.getSimulationButton();
    }

    public void disableSimulationProperties(){
        menuPanel.disableSimulationProperties();
    }

    public Boolean checkBirthSimulationProperties() {
        return menuPanel.checkBirthSimulationProperties();
    }

    public Boolean checkDeathSimulationProperties() {
        return menuPanel.checkDeathSimulationProperties();
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

    public void setSimulationProperties(Integer normalRabbitBirthTime, Integer whiteRabbitBirthTime, Float normalRabbitBirthProbability, Float rabbitPercent, Integer normalRabbitDeathTime, Integer whiteRabbitDeathTime) {
        this.normalRabbitBirthTime = normalRabbitBirthTime;
        this.whiteRabbitBirthTime = whiteRabbitBirthTime;
        this.normalRabbitBirthProbability = normalRabbitBirthProbability;
        this.rabbitPercent = rabbitPercent;
        this.normalRabbitDeathTime = normalRabbitDeathTime;
        this.whiteRabbitDeathTime = whiteRabbitDeathTime;
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

    public Integer getNormalRabbitDeathTime() {
        return menuPanel.getNormalRabbitDeathTime();
    }

    public Integer getWhiteRabbitDeathTime() {
        return menuPanel.getWhiteRabbitDeathTime();
    }

    public JButton getShowAliveObjectsInformation(){
        return menuPanel.getShowAliveObjectsInformation();
    }

    public void showAliveObjectsInformation() {
        AliveRabbitsDialog aliveRabbitsDialog = new AliveRabbitsDialog(this,"Alive objects");
        aliveRabbitsDialog.viewInformation();
    }

    public static Integer getTime(){
        return time;
    }

    public JButton getNormalRabbitAIButton() {
        return menuPanel.getNormalRabbitAIButton();
    }

    public JButton getWhiteRabbitAIButton() {
        return menuPanel.getWhiteRabbitAIButton();
    }

    public Integer getNormalRabbitThreadPriority() {
        return menuPanel.getNormalRabbitThreadPriority();
    }

    public Integer getWhiteRabbitThreadPriority() {
        return menuPanel.getWhiteRabbitThreadPriority();
    }
}

