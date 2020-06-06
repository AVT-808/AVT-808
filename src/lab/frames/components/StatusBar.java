package lab.frames.components;

import javax.swing.*;
import java.awt.*;


public class StatusBar extends JPanel {
    private JLabel messageLabel = new JLabel("Статус: Симуляция приостановлена");


    public StatusBar() {
        setLayout(new BorderLayout());
        add(messageLabel, BorderLayout.CENTER);
    }

    public void setStatus(String message, Color c) {
        messageLabel.setText(message);
        setBackground(c);
        updateUI();
    }
}
