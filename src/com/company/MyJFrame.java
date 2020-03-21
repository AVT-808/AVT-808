package com.company;

import com.company.Habitat.Habitat;

import javax.swing.*;


public class MyJFrame extends JFrame  {

    MyJFrame(Habitat window){

        JFrame jFrame = new JFrame("Cats and Dogs");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);

        //jFrame.setLayout(null);
        GUI myGUI = new GUI(window,jFrame);
        //myGUI.setBounds(0, 0, 1200, 60);
        jFrame.add(myGUI);
        MyMenuBar myMenuBar = new MyMenuBar(myGUI);
        jFrame.setMenuBar(myMenuBar);






        jFrame.setVisible(true);
        jFrame.setSize(window.getSizeX(),window.getSizeY());
        jFrame.setLocationRelativeTo(null);
    }
}
