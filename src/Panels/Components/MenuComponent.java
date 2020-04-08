package Panels.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuComponent extends JMenuBar{
    private final JMenu simulationMenu;
    private final JMenu viewMenu;
    private final JMenu informationMenu;

    private final JMenuItem simulationStartability;
    private final JMenuItem timerVisibility;
    private final JMenuItem informationViewability;


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

    public JMenu getSimulationMenu() {
        return simulationMenu;
    }

    public JMenu getViewMenu() {
        return viewMenu;
    }

    public JMenu getInformationMenu() {
        return informationMenu;
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
