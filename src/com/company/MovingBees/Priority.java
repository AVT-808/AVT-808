package com.company.MovingBees;

import javax.swing.*;
import java.awt.*;

public class Priority extends JPanel {
    JComboBox<Integer> comboBoxDrone;
    JComboBox<Integer> comboBoxWorker;
    JComboBox<Integer> comboBoxM;

    public Priority () {

        setLayout(new GridLayout(2, 3));

        JLabel textDrone = new JLabel("Трут");
        textDrone.setFont(new Font("Courier New", Font.BOLD,11));
        textDrone.setForeground(Color.BLACK);
        textDrone.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel textWorker = new JLabel("Раб");
        textWorker.setFont(new Font("Courier New", Font.BOLD,11));
        textWorker.setForeground(Color.BLACK);
        textWorker.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel textM = new JLabel("Осн");
        textM.setFont(new Font("Courier New", Font.BOLD,11));
        textM.setForeground(Color.BLACK);
        textM.setHorizontalAlignment(SwingConstants.CENTER);

        add(textWorker);
        add(textDrone);
        add(textM);

        Integer[] items = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        comboBoxDrone = new JComboBox<>(items);
        comboBoxDrone.setForeground(Color.BLACK);
        comboBoxWorker = new JComboBox<>(items);
        comboBoxWorker.setForeground(Color.BLACK);
        comboBoxM = new JComboBox<>(items);
        comboBoxM.setForeground(Color.BLACK);

        add(comboBoxWorker);
        add(comboBoxDrone);
        add(comboBoxM);

        setVisible(true);
        setFocusable(false);

        comboBoxDrone.addActionListener(e -> {
            DroneAI.getThread().setPriority((int)comboBoxDrone.getSelectedItem());
            System.out.println("Поток "+ DroneAI.getThread().getName()+" Пр: " + DroneAI.getThread().getPriority());
            comboBoxDrone.setFocusable(false);
        });

        comboBoxWorker.addActionListener(e -> {
            WorkerAI.getThread().setPriority((int)comboBoxWorker.getSelectedItem());
            System.out.println("Поток "+ WorkerAI.getThread().getName()+" Пр: " + WorkerAI.getThread().getPriority());
            comboBoxWorker.setFocusable(false);
        });

        comboBoxM.addActionListener(e -> {
            int pr = (int)comboBoxM.getSelectedItem();
            Thread.currentThread().setPriority(pr); // Получение ссылки на объект главного потока
            System.out.println("Поток "+ Thread.currentThread().getName()+" Пр: " + Thread.currentThread().getPriority());
            comboBoxM.setFocusable(false);
        });
    }
}
