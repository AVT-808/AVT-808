package com.company.Panels;

import com.company.Serialization.DataFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeOfBirth extends JPanel {

    JTextField txt;
    JTextField txt2;
   public static Integer birthTimeWorkers = 2;
    public static Integer birthTimeDrones = 3;

    public TimeOfBirth(){
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JLabel text = new JLabel("Вр. рож. раб.");
        text.setFont(new Font("Courier New", Font.BOLD,11));
        text.setForeground(Color.BLACK);
        text.setHorizontalAlignment(SwingConstants.CENTER);


        JLabel text2 = new JLabel("Вр. рож. трут.");
        text2.setFont(new Font("Courier New", Font.BOLD,11));
        text2.setForeground(Color.BLACK);
        text2.setHorizontalAlignment(SwingConstants.CENTER);


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));


        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(2,1));

        panel.add(text);
        panel2.add(text2);

        birthTimeWorkers = DataFile.bufferInt[4];
        birthTimeDrones = DataFile.bufferInt[5];
        txt = new JTextField(9);
        txt.setText(String.valueOf(birthTimeWorkers));
        txt2 = new JTextField(9);
        txt2.setText(String.valueOf(birthTimeDrones));



        txt.setHorizontalAlignment(JTextField.CENTER);
        //textArea_small.setToolTipText("Длиное поле"); // Установка метки
        txt2.setHorizontalAlignment(JTextField.CENTER);

        txt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try { birthTimeWorkers = Integer.parseInt(txt.getText());}
                catch (NumberFormatException e1) {

                    JFrame frame = new JFrame("Внимание");
                    frame.setSize(200,200);
                    JTextArea textArea = new JTextArea();
                    String string1 = "Введены некорректные данные\n";

                    textArea.append(string1);

                    textArea.setEditable(false);
                    frame.add(textArea);
                    frame.setVisible(true);
                    txt.setText(" ");
                }
                /*txt.setEnabled(false);
                txt.setFocusable(false);*/
            }
        });

        txt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try { birthTimeDrones = Integer.parseInt(txt2.getText());}
                catch (NumberFormatException e1) {

                    JFrame frame = new JFrame("Внимание");
                    frame.setSize(200,200);
                    JTextArea textArea = new JTextArea();
                    String string1 = "Введены некорректные данные\n";
                    textArea.append(string1);
                    textArea.setEditable(false);
                    frame.add(textArea);
                    frame.setVisible(true);
                    txt2.setText(" ");
                }
                /*txt2.setEnabled(false);
                txt2.setFocusable(false);*/
            }
        });
        panel.add(txt);
        panel2.add(txt2);

        add(panel);
        add(Box.createRigidArea(new Dimension(5,0))); // Разделитель
        add(panel2);

        setVisible(true);

    }
    public Integer return_birthTimeDrones() {
        return birthTimeDrones;
    }

    public Integer return_birthTimeWorkers() {
        return birthTimeWorkers;
    }
}
