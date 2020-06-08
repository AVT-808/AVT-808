package com.company.MovingBees;

import javax.swing.*;
import java.awt.*;

public class Pause extends JPanel {
    private static JCheckBox Box_Worker;
    private static JCheckBox Box_Drone;
    public static DroneAI droneAI;
    public static WorkerAI workerAI;

    public Pause() {
        setLayout(new GridLayout(2, 1));
        Box_Worker = new JCheckBox("Интел. рабочих.", true);
        add(Box_Worker);
        Box_Worker.setFocusable(false);


        Box_Drone = new JCheckBox("Интел. трутней.", true);
        add(Box_Drone);
        Box_Drone.setFocusable(false);

        Box_Worker.addActionListener(e -> { // Остановка/продолжение движения взрослых
            if (Box_Worker.isSelected())
                workerAI.startAI();
            else workerAI.stopAI();
        });


        Box_Drone.addActionListener(e -> { // Остановка/продолжение движения птенцов
            if (Box_Drone.isSelected())
                droneAI.startAI();
            else droneAI.stopAI();
        });
    }

    public static void setAI(DroneAI droneAI2, WorkerAI workerAI2) { // Ссылка из AnimalTour
        droneAI = droneAI2;
        workerAI = workerAI2;
    }
}
