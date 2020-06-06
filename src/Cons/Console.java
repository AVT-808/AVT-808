package Cons;

import Fact.PercentChange;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;

public class Console extends JDialog  {

    int getTextInt = 50;

    public static PipedInputStream pipeIn;
    PipedOutputStream pipeOut;

    JTextArea jTextArea = new JTextArea();
    JTextField jTextAreaEnter = new JTextField();

    JScrollPane scrollPane = new JScrollPane(jTextArea);

    String string = "Изменить процент птенцов\nВведите новое число, затем нажмите ENTER\n";

    public Console(JFrame jFrame) {

        super(jFrame, "Console", true);
        setBounds(0, 0, 600, 400);
        setResizable(false); // Возможность изменить размеры окна
        jTextArea.setEditable(false); // Включение или выключение возможности редактирования текста
        setModal(false);

        jTextArea.setBackground(Color.BLACK); // Основное поле
        jTextArea.setForeground(Color.WHITE); // Цвет текста
        jTextArea.setFont(new Font("Times New Romain", Font.PLAIN, 14));  // Установка шрифта
        jTextArea.setLineWrap(true); // В длинных строках выполняется перенос
        jTextArea.setText(string);

        jTextAreaEnter.setBackground(Color.BLACK);
        jTextAreaEnter.setForeground(Color.WHITE);
        jTextAreaEnter.setFont(new Font("Times New Romain", Font.PLAIN, 16));

        setLayout(new FlowLayout(FlowLayout.LEFT));
        scrollPane.setPreferredSize(new Dimension(575, 320));
        add(scrollPane);
        jTextAreaEnter.setPreferredSize(new Dimension(575, 25));
        add(jTextAreaEnter);
jTextAreaEnter.requestFocus();
jTextAreaEnter.setFocusable(true);
    }

    public void ConsoleOperation(){

        jTextAreaEnter.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    string += jTextAreaEnter.getText() + "\n";
                    jTextArea.setText(string);

                    String getTextString = jTextAreaEnter.getText();
                    try {
                         getTextInt = Integer.parseInt(getTextString);
                        if ((getTextInt<0) || (getTextInt>100)) {
                            string += "Пожалуйста, введите число от 0 до 100\n";
                        } else {
                            string += "\nВ симмуляцию внесены изменения:\n" +
                                    "Птенцы генерируются при условии, что их количество менее " + getTextString +"% от общего числа взрослых птиц" +
                                    "\n\nЧтобы еще раз изменить процент птенцов\nВведите новое число, затем нажмите ENTER\n";
                            // jTextAreaEnter.setEnabled(false);
                            try {
                                pipeIn = new PipedInputStream();
                                pipeOut = new PipedOutputStream(pipeIn);
                                pipeOut.write(getTextInt);
                                PercentChange percentChange = new PercentChange(pipeIn);

                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                        jTextArea.setText(string);
                        jTextAreaEnter.setText(null);

                    } catch (NumberFormatException err) {
                        string += "Пожалуйста, введите только число\n";
                        jTextArea.setText(string);
                        jTextAreaEnter.setText(null);
                    }
                    jTextArea.setText(string);


jTextAreaEnter.setFocusable(true);
                  jTextAreaEnter.requestFocus();

                }
            }
        });

    }

}
