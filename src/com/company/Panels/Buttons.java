package com.company.Panels;

import com.company.Habitat.BeesArray.Singleton;
import com.company.Habitat.Habitat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Buttons extends JPanel {
    private final JButton startButton;
    private final JButton stopButton;
  Singleton st;

    public Buttons(Habitat habitat){
        setLayout(new GridLayout(2,1));
        setFocusable(false);

        startButton = new JButton("Старт");
       // startButton.setFocusable(true);
        startButton.setMnemonic('B');
        startButton.setFocusable(false);
        startButton.setEnabled(true);


        stopButton = new JButton("Стоп");
        stopButton.setMnemonic('E');
        stopButton.setEnabled(false);

        add(startButton);
        startButton.setVisible(true);
        add(stopButton);
        stopButton.setVisible(true);

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
}
