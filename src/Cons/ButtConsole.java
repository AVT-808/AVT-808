package Cons;

import Contr.StartStop;
import Habit.Habitat;
import Move.SmallAI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ButtConsole extends JPanel {

    public static JButton button =  new JButton("Консоль");

    public ButtConsole () {
        setLayout(new GridLayout(1, 1));

        button.addActionListener(e -> {
             Habitat.getConsole().setVisible(true);
                  });

        add(button);

    }


}
