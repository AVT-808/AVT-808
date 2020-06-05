package Panels;

import Habitat.RabbitList.Singleton;

import javax.swing.*;
import java.awt.*;

public class AliveRabbitsDialog extends JDialog {
    private final JTextArea results;

    public AliveRabbitsDialog(JFrame frame, String title) {
        super(frame, title, true);
        results = new JTextArea();

        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        var rabbits = Singleton.getInstance();
        for (var rabbitId: rabbits.getRabbitsInformation().keySet()) {
            results.append("Id: " + rabbitId +"\n"+"Birth time:"+rabbits.getRabbitsInformation().get(rabbitId) + "\n\n");
        }
        results.setEditable(false);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(new Dimension(300,300));
        JScrollPane scrollPane = new JScrollPane(results,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane);
        setFocusable(true);
    }

    public void viewInformation(){
        results.setVisible(true);
        setVisible(true);
    }
}
