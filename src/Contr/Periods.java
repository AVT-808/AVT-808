package Contr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Periods extends JPanel {

    public static JTextField textArea_big;
    public static JTextField textArea_small;
    static Integer time_birth_big=5; // Con
    static Integer time_birth_small=3; // Con

    public Periods() {

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        //****метки****//
        JLabel text = new JLabel("Период вз.");
        text.setFont(new Font("Courier New", Font.BOLD,11));
        text.setForeground(Color.BLACK);
        text.setHorizontalAlignment(SwingConstants.CENTER);


        JLabel text2 = new JLabel("Период пт.");
        text2.setFont(new Font("Courier New", Font.BOLD,11));
        text2.setForeground(Color.BLACK);
        text2.setHorizontalAlignment(SwingConstants.CENTER);


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));


        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(2,1));

        panel.add(text);
        panel2.add(text2);

        //****поля****//

        textArea_big = new JTextField(8);
        textArea_small = new JTextField(8);
        //  textArea_small.setFont(new Font("Dialog", Font.PLAIN, 7)); // установка шрифта
        textArea_small.setHorizontalAlignment(JTextField.CENTER);
        //textArea_small.setToolTipText("Длиное поле"); // Установка метки
        textArea_big.setHorizontalAlignment(JTextField.CENTER);


        textArea_small.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              try { time_birth_small = Integer.parseInt(textArea_small.getText());}
              catch (NumberFormatException e1) { // Исключительная ситуация - ввод неверных данных
                  time_birth_small = 2;
                  JFrame frame = new JFrame("Внимание!");
                  frame.setSize(300, 100);
                  JTextArea textArea = new JTextArea();
                  String string1 = "Введены некорректные данные\n";
                  String string2 = "Период рожд. пт. = 2\n";
                  String string3 = "(значение по умолчанию)\n";
                  textArea.append(string1);
                  textArea.append(string2);
                  textArea.append(string3);
                  textArea.setEditable(false);
                  frame.add(textArea);
                  frame.setVisible(true);
                  textArea_small.setText("2");
              }
                textArea_small.setEnabled(false);
                textArea_small.setFocusable(false);
            }
        });

        textArea_big.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               try {time_birth_big = Integer.parseInt(textArea_big.getText());}
               catch (NumberFormatException e1) {
                   time_birth_big = 5;
                   JFrame frame = new JFrame("Внимание!");
                   frame.setSize(300, 100);
                   JTextArea textArea = new JTextArea();
                   String string1 = "Введены некорректные данные\n";
                   String string2 = "Период рожд. вз. = 5\n";
                   String string3 = "(значение по умолчанию)\n";
                   textArea.append(string1);
                   textArea.append(string2);
                   textArea.append(string3);
                   textArea.setEditable(false);
                   frame.add(textArea);
                   frame.setVisible(true);
                   textArea_big.setText("5");
               }
                textArea_big.setEnabled(false);
                textArea_big.setFocusable(false);
            }
        });

        panel.add(textArea_big);
        panel2.add(textArea_small);

        add(panel);
        add(Box.createRigidArea(new Dimension(3,0))); // Разделитель
        add(panel2);

        setVisible(true);
    }

    public static Integer Return_time_birth_big() {return time_birth_big;}

    public static Integer Return_time_birth_small() {return time_birth_small;}

    public static JTextField getTextArea_big() {
        return textArea_big;
    }

    public static JTextField getTextArea_small() {
        return textArea_small;
    }
}
