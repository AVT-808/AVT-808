package com.company.Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LifeTimeOfBees extends JPanel {
    static Integer life_time_Drones;
    JTextField life_workers;
    JTextField life_drones;
    static Integer life_time_Workers;


    public LifeTimeOfBees(){
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JLabel text = new JLabel("Вр. жизни раб.");
        text.setFont(new Font("Courier New", Font.BOLD,11));
        text.setForeground(Color.BLACK);
        text.setHorizontalAlignment(SwingConstants.CENTER);


        JLabel text2 = new JLabel("Вр. жизни трут.");
        text2.setFont(new Font("Courier New", Font.BOLD,11));
        text2.setForeground(Color.BLACK);
        text2.setHorizontalAlignment(SwingConstants.CENTER);


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));


        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(2,1));

        panel.add(text);
        panel2.add(text2);

        life_workers = new JTextField(9);
        life_drones = new JTextField(9);

        life_workers.setHorizontalAlignment(JTextField.CENTER);

        life_drones.setHorizontalAlignment(JTextField.CENTER);

        life_workers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try { life_time_Workers = Integer.parseInt(life_workers.getText());}
                catch (NumberFormatException e1) {
                    life_time_Workers = 7;
                    JFrame frame = new JFrame("Внимание");
                    frame.setSize(200,200);
                    JTextArea textArea = new JTextArea();
                    String string1 = "Введены некорректные данные\n";
                    textArea.append(string1);
                    textArea.setEditable(false);
                    frame.add(textArea);
                    frame.setVisible(true);
                    life_workers.setText(" ");
                }
               /* life_workers.setEnabled(false);
                life_workers.setFocusable(false);*/
            }
        });

        life_drones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try { life_time_Drones = Integer.parseInt(life_drones.getText());}
                catch (NumberFormatException e1) {
                    life_time_Drones = 7;
                    JFrame frame = new JFrame("Внимание");
                    frame.setSize(200,200);
                    JTextArea textArea = new JTextArea();
                    String string1 = "Введены некорректные данные\n";
                    textArea.append(string1);
                    textArea.setEditable(false);
                    frame.add(textArea);
                    frame.setVisible(true);
                    life_drones.setText(" ");
                }
                /* life_drones.setEnabled(false);
                life_drones.setFocusable(false); */
            }
        });
        panel.add(life_workers);
        panel2.add(life_drones);

        add(panel);
        add(Box.createRigidArea(new Dimension(5,0))); // Разделитель
        add(panel2);

        setVisible(true);

    }
    public static Integer return_life_time_Drones() {
        return life_time_Drones;
    }

    public static Integer return_life_time_Workers() {
        return life_time_Workers;
    }
}




