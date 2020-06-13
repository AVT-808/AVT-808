package com.company.Panels;

import com.company.Habitat.BeesArray.Singleton;
import com.company.Habitat.Habitat;
import com.company.Serialization.Seria;
import com.company.Server.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class Buttons extends JPanel {
    private final JButton startButton;
    private final JButton stopButton;
    private final JButton consoleButton;
    private static final JButton button_save =  new JButton("Сохранить");
    private static final JButton button_load = new JButton("Загрузить");
    private static final JButton sendBees = new JButton("Отпраивть");

  Singleton st;

    public Buttons(Habitat habitat){
        setLayout(new GridLayout(2,1));
        setFocusable(false);

        Seria serializ = new Seria();
        startButton = new JButton("Старт");
        startButton.setMnemonic('B');
        startButton.setFocusable(false);
        startButton.setEnabled(true);


        stopButton = new JButton("Стоп");
        stopButton.setMnemonic('E');
        stopButton.setEnabled(false);

        consoleButton = new JButton("Консоль");
        consoleButton.setEnabled(true);
        consoleButton.setFocusable(false);


        button_save.setEnabled(true);
        button_save.setFocusable(false);

        button_load.setEnabled(true);
        button_load.setFocusable(false);


        sendBees.setEnabled(true);
        sendBees.setFocusable(false);


        add(startButton);
        startButton.setVisible(true);
        add(button_save);
        button_save.setVisible(true);
        add(consoleButton);
        consoleButton.setVisible(true);
        add(stopButton);
        stopButton.setVisible(true);
        add(button_load);
        button_load.setVisible(true);
        add(sendBees);
        sendBees.setVisible(true);



        st = Singleton.getInstance();

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                st.Start(habitat,startButton,stopButton);
                startButton.setEnabled(false);
                stopButton.setEnabled(true);
                setFocusable(false);
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                st.Stop(habitat,startButton,stopButton);
                stopButton.setEnabled(false);
                startButton.setEnabled(true);
                setFocusable(false);
            }
        });
        consoleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
               Singleton.getInstance().console();
            }
        });

        button_save.addActionListener(e -> {
            serializ.serialization();
        });

        button_load.addActionListener(e -> {
            try {
                serializ.deserialization(habitat);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        sendBees.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Client.sendObj(Singleton.getInstance().bees);
            }
        });

        stopButton.setFocusable(false);
        startButton.setFocusable(false);
        setFocusable(false);
    }
    public JButton returnStart() {
       return startButton;
    }

    public JButton returnStop() {
        return stopButton;
    }

    public JButton returnConsoleButton() {
        return stopButton;
    }

    public static JButton returnSbutton() { return button_save; } // Для доступа к этим же кнопкам в Keyboard

    public static JButton returnLbutton() { return button_load; }

    public static JButton returnSendBees(){return sendBees;}
}
