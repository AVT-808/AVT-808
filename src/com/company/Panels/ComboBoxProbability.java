package com.company.Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComboBoxProbability extends JPanel {

    JComboBox cb;
    JComboBox cb1;
    Double probabilityOfWorkers;
    Double percent;

    public ComboBoxProbability(){
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        Double[] Items = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0};

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                probabilityOfWorkers = (Double) cb.getSelectedItem();
                cb.setFocusable(false);
            }
        };
        ActionListener actionListener1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                percent = (Double) cb1.getSelectedItem();
                cb1.setFocusable(false);
            }
        };

        cb = new JComboBox(Items);
        cb.addActionListener(actionListener);
        cb1 = new JComboBox(Items);
        cb1.addActionListener(actionListener1);

        JLabel text = new JLabel("Вер.рожд.трутней");
        text.setFont(new Font("Courier New", Font.BOLD,11));
        text.setForeground(Color.BLACK);
        text.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel text2 = new JLabel("% отн. общ. числа");
        text2.setFont(new Font("Courier New", Font.BOLD,11));
        text2.setForeground(Color.BLACK);
        text2.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel jpanel = new JPanel();
        jpanel.setLayout(new GridLayout(2, 1));

        JPanel jpanel2 = new JPanel();
        jpanel2.setLayout(new GridLayout(2,1));

        jpanel.add(text);
        jpanel.add(cb);

        jpanel2.add(text2);
        jpanel2.add(cb1);

        this.add(jpanel);
        add(Box.createRigidArea(new Dimension(2,0))); // Отступ в расположении
        this.add(jpanel2);
        add(Box.createRigidArea(new Dimension(3,0)));

        setVisible(true);

        setFocusable(false);

    }
    public Double return_probabilityOfWorkers(){
        return probabilityOfWorkers;
    }

    public Double return_percent(){
        return percent;
    }

}
