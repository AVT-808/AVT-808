package com.company.Panels;

import javax.swing.*;
import java.awt.*;

public class Information extends JDialog {
    private JLabel timerResult;
    private JLabel WorkerResult;
    private JLabel DroneResult;

    public Information(JFrame frame, String title, Integer time, Integer WorkerResult, Integer DroneResult){
        super(frame, title, false);

        this.timerResult = new JLabel("Время симуляции: " + time);
        this.timerResult.setFont(new Font("Courier New", Font.PLAIN,16));
        this.timerResult.setForeground(Color.BLUE);

        this.WorkerResult = new JLabel("Количество рабочиз пчел: " + WorkerResult);
        this.WorkerResult.setFont(new Font("Arial", Font.PLAIN,15));
        this.WorkerResult.setForeground(Color.ORANGE);

        this.DroneResult = new JLabel("Количество трутней: " + DroneResult);
        this.DroneResult.setFont(new Font("Times New Roman", Font.PLAIN,14));
        this.DroneResult.setForeground(Color.GREEN);

        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setLayout(new GridLayout(3,1));

        this.timerResult.setHorizontalAlignment(SwingConstants.CENTER);
        add(this.timerResult);

        this.WorkerResult.setHorizontalAlignment(SwingConstants.CENTER);
        add(this.WorkerResult);

        this.DroneResult.setHorizontalAlignment(SwingConstants.CENTER);
        add(this.DroneResult);

        this.setModal(true);

    }

    public void viewInformation(){
        this.timerResult.setVisible(true);
        this.WorkerResult.setVisible(true);
        this.DroneResult.setVisible(true);
        setVisible(true);
    }
}
