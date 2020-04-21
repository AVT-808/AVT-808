//package com.company;
//
//
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.io.*;
//
//public class Console extends JDialog{
//    private JDialog frame;
//    private JTextArea textArea;
//
//    public Console(JFrame myframe){
//
//         frame=new JDialog(myframe);
//         frame.setTitle("Console");
//         frame.setModal(true);
//         frame.setSize(700,500);
//         frame.setLocationRelativeTo(null);
//
//         textArea=new JTextArea(24, 50);
//         textArea.setBackground(Color.BLACK);
//         textArea.setForeground(Color.LIGHT_GRAY);
//         textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));;
//
//         frame.getContentPane().setLayout(new BorderLayout());
//         frame.getContentPane().add(new JScrollPane(textArea),BorderLayout.CENTER);
//         frame.setVisible(true);
//
//
//
//         frame.setDefaultCloseOperation(frame.HIDE_ON_CLOSE);
//         frame.setVisible(true);
//
//
//
//     }
//
//
//
//}
package com.company;

import com.company.Habitat.Habitat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Console extends JDialog {

    private int catSpawn;
    private int dogSpawn;
    JTextArea jTextArea = new JTextArea();
    JTextField jTextAreaCommand = new JTextField();

    JScrollPane scrollPane = new JScrollPane(jTextArea);

    Habitat window;
    JComboBox cats;
    JComboBox dogs;

    String text = "Use /info for a list of possible command\n";

    public Console(JFrame owner, Habitat myWindow, JComboBox catsComboBox, JComboBox dogsComboBox) {
        super(owner,"Console", true);
        window = myWindow;
        cats = catsComboBox;
        dogs = dogsComboBox;

        setBackground(Color.BLACK);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        jTextArea.setEditable(false);
        jTextArea.setBackground(Color.BLACK);
        jTextArea.setForeground(Color.WHITE);
        jTextArea.setFont(new Font("Times New Romain",Font.PLAIN,16));
        jTextArea.setLineWrap(true);
        jTextArea.setText(text);

        jTextAreaCommand.setBackground(Color.BLACK);
        jTextAreaCommand.setForeground(Color.WHITE);
        jTextAreaCommand.setFont(new Font("Times New Romain",Font.PLAIN,16));

        setLayout(new FlowLayout(FlowLayout.LEFT));
        scrollPane.setPreferredSize(new Dimension(785,420));
        add(scrollPane);
        jTextAreaCommand.setPreferredSize(new Dimension(785,25));
        add(jTextAreaCommand);
    }

    public void showConsole(){

        jTextAreaCommand.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    text += jTextAreaCommand.getText() + "\n";
                    jTextArea.setText(text);

                    switch (jTextAreaCommand.getText()){
                        case "/info":
                            text += "XXX - cats/dogs\n/stop generate XXX - Остановить генерацию \n/start generate XXX - Возобновить генерацию \n";
                            break;
                        case "/stop generate cats":
                            if (window.getP1() != 0) {
                                catSpawn = window.getP1();
                                window.setP1(0);;
                                cats.setSelectedIndex(0);
                            }
                            else {
                                text += "Cats generation already stopped\n";
                            }
                            break;
                        case "/start generate cats":
                            if (window.getP1() == 0) {
                                window.setP1(catSpawn);
                                cats.setSelectedIndex(catSpawn/10);
                            }
                            else {
                                text += "Cats generation is not stopped yet\n";
                            }
                            break;
                        case "/stop generate dogs":
                            if (window.getP2() != 0) {
                                dogSpawn = window.getP2();
                                window.setP2(0);;
                                dogs.setSelectedIndex(0);
                            }
                            else {
                                text += "Dogs generation already stopped\n";
                            }
                            break;
                        case "/start generate dogs":
                            if (window.getP2() == 0) {
                                window.setP2(dogSpawn);
                                dogs.setSelectedIndex(dogSpawn/10);
                            }
                            else {
                                text += "Dogs generation is not stopped yet\n";
                            }
                            break;

                        default:
                            text += "Unknown command. Please use /info for a list of possible command\n";
                            break;
                    }
                    jTextAreaCommand.setText(null);
                    jTextArea.setText(text);
                }
            }
        });
        setVisible(true);
    }


}
