package Panels;

import javax.swing.*;
import java.awt.*;

public class EndSimulationDialog extends JDialog {

    private final JTextArea results;

    public EndSimulationDialog(JFrame frame, String title, Integer time, Integer normalRabbitsAmount, Integer whiteRabbitsAmount) {
        super(frame, title, false);
        setLayout(new BorderLayout());
        results = new JTextArea();
        String timerResult = "Время симуляции: " + time + "\n";

        String normalRabbitsResult = "Количество обычных кроликов: " + normalRabbitsAmount + "\n";

        String whiteRabbitsResult = "Количество кроликов альбиносов: " + whiteRabbitsAmount + "\n";
        results.append(timerResult);
        results.append(normalRabbitsResult);
        results.append(whiteRabbitsResult);
        results.setEditable(false);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(new Dimension(300,300));
        add(results);

        this.setModal(true);

    }

    public void viewInformation(){
        results.setVisible(true);
        setVisible(true);
    }
}
