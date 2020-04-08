package Panels.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuComponent extends JMenuBar{
    private final JMenu simulationMenu;//пункт меню бара
    private final JMenu viewMenu;//пункт меню бара
    private final JMenu informationMenu;//пункт меню бара
    private final JMenuItem simulationStartability;//item меню
    private final JMenuItem timerVisibility;//item меню
    private final JMenuItem informationViewability;//item меню


    public MenuComponent(ActionListener actionListener){
        setLayout(new GridLayout(1,3));
        setFocusable(false);
        simulationStartability = new JMenuItem("Start/Stop");
        timerVisibility = new JMenuItem("Show/Hide");
        informationViewability = new JMenuItem("Allow/Disallow");

        simulationStartability.addActionListener(actionListener);
        timerVisibility.addActionListener(actionListener);
        informationViewability.addActionListener(actionListener);

        simulationMenu = new JMenu("Simulation");
        viewMenu = new JMenu("View");
        informationMenu = new JMenu("Information");

        simulationMenu.add(simulationStartability);
        viewMenu.add(timerVisibility);
        informationMenu.add(informationViewability);

        add(simulationMenu);
        add(viewMenu);
        add(informationMenu);
    }

    public JMenuItem getSimulationStartability() {
        return simulationStartability;
    }

    public JMenuItem getTimerVisibility() {
        return timerVisibility;
    }

    public JMenuItem getInformationViewability() {
        return informationViewability;
    }
}
