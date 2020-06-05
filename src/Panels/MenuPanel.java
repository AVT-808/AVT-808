package Panels;

import Panels.Components.MenuComponent;
import Panels.Components.ToolBarComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {

    private final MenuComponent menu;
    private final ToolBarComponent toolBar;

    public MenuPanel(Integer width, ActionListener actionListener) {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        menu = new MenuComponent(actionListener);
        toolBar = new ToolBarComponent(actionListener);
        menu.setMaximumSize(new Dimension(width, 20));
        toolBar.setMaximumSize(new Dimension(width, 90));
        add(menu);
        add(toolBar);

    }

    public JButton getSimulationButton(){
        return toolBar.getStartSimulation();
    }

    public void disableSimulationProperties(){
        toolBar.disableSimulationProperties();
    }

    public Boolean checkBirthSimulationProperties() {
        return toolBar.checkBirthTimeSimulationProperties();
    }

    public Boolean checkDeathSimulationProperties() {
        return toolBar.checkDeathTimeSimulationProperties();
    }

    public Integer getNormalRabbitBirthTime() {
        return Integer.parseInt(toolBar.getNormalRabbitBirthTime().getText());
    }

    public Integer getWhiteRabbitBirthTime() {
        return Integer.parseInt(toolBar.getWhiteRabbitBirthTime().getText());
    }

    public Float getNormalRabbitBirthProbability() {
        return (Float) toolBar.getNormalRabbitBirthProbability().getSelectedItem();
    }

    public Float getRabbitPercent() {
        return (Float) toolBar.getRabbitsPercent().getSelectedItem();
    }

    public void enableStopButton() {
        toolBar.enableStopButton();
    }

    public void enableSimulationProperties() {
        toolBar.enableSimulationProperties();
    }

    public JButton getStopSimulationButton() {
        return toolBar.getStopSimulation();
    }

    public JRadioButton getHideTimerRadioButton() {
        return toolBar.getTimerNotAllowed();
    }

    public JRadioButton getShowTimerRadioButton() {
        return toolBar.getTimerAllowed();
    }

    public JCheckBox getInformationDialogSelecter() {
        return toolBar.getIsInformationPanelAllowed();
    }

    public JMenuItem getMenuStartStopButton() {
        return menu.getSimulationStartability();
    }

    public JMenuItem getMenuShowHideTimerButton() {
        return menu.getTimerVisibility();
    }

    public JMenuItem getMenuShowHideInformationDialog() {
        return menu.getInformationViewability();
    }

    public Integer getNormalRabbitDeathTime() {
        return Integer.parseInt(toolBar.getNormalRabbitDeathTime().getText());
    }

    public Integer getWhiteRabbitDeathTime() {
        return Integer.parseInt(toolBar.getWhiteRabbitDeathTime().getText());
    }

    public JButton getShowAliveObjectsInformation() {
        return toolBar.getShowAliveObjectsInformation();
    }
}
