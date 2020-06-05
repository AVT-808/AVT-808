package com.company.Panels;

import com.company.Habitat.Habitat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurObj extends JPanel {
    protected JButton but;
    public CurObj(Habitat habitat){
        but = new JButton("Т.О");
      //  setLayout(new GridLayout(2, 1));

        but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OnScreen onScreen = new OnScreen(habitat);
                onScreen.setVisible(true);
                setFocusable(false);
            }
        });
        add(but);
        setFocusable(false);
        }
    public CurObj getCCurrentObjects() { return this;}
    }

