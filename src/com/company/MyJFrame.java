package com.company;

import com.company.Habitat.Habitat;

import javax.swing.*;


public class MyJFrame extends JFrame  {

    MyJFrame(Habitat window){

        JFrame jFrame = new JFrame("Cats and Dogs");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);

        DataFile dataFile = new DataFile();
        GUI myGUI = new GUI(window,jFrame,dataFile);
        jFrame.add(myGUI);
        MyMenuBar myMenuBar = new MyMenuBar(myGUI,dataFile,window);
        jFrame.setMenuBar(myMenuBar);






        jFrame.setVisible(true);
        jFrame.setSize(window.getSizeX(),window.getSizeY());
        jFrame.setLocationRelativeTo(null);
    }
}
