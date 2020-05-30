package Existence;

import Habit.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrentObjects extends JPanel { // Кнопка - текущие объекты

    protected JButton button;

    public CurrentObjects(Habitat habitat) {

        button = new JButton("Текущ. об.");

        setLayout(new GridLayout(2, 1));

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OnTheField onTheField = new OnTheField(habitat);
                onTheField.setVisible(true);
            }
        });

        add(button);
        setFocusable(false);
    }

    public CurrentObjects getCCurrentObjects() { return this;}
}
