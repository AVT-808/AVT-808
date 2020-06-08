package com.company.Panels;

import com.company.Habitat.BeesArray.Singleton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Console  extends JDialog {

    JTextArea jTextArea = new JTextArea();
    JTextField jTextAreaCommand = new JTextField();
    JScrollPane scrollPane = new JScrollPane(jTextArea);
    String text = "Use /info for a list of possible command\n";

    public Console(JFrame owner) {
        super(owner,"Console", true);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
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
        jTextAreaCommand.setFocusable(true);

        jTextAreaCommand.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    text += jTextAreaCommand.getText() + "\n";
                    jTextArea.setText(text);
                    switch (jTextAreaCommand.getText()){
                        case "/info":
                            text += "*** - workers/drones\n/show number of *** - Показать кол-во пчёл\n";
                            break;
                        case "/show number of workers":
                           text += "Number of Workers - " + Singleton.getInstance().getAllWorkersBirth()+ "\n";

                            break;
                        case "/show number of drones":
                            text += "Number of Drones - " + Singleton.getInstance().getAllDronesBirth()+ "\n";
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
