package Existence;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Life extends JPanel {

    JTextField textArea_big;
    JTextField textArea_small;
   static Integer lifetime_Big; // Con
    static  Integer lifetime_Small; // Con

    public Life() {

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        //****метки****//
        JLabel text = new JLabel("Время жизни вз.");
        text.setFont(new Font("Courier New", Font.BOLD,11));
        text.setForeground(Color.BLACK);
        text.setHorizontalAlignment(SwingConstants.CENTER);


        JLabel text2 = new JLabel("Время жизни пт.");
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
                try { lifetime_Small = Integer.parseInt(textArea_small.getText());}
                catch (NumberFormatException e1) { // Исключительная ситуация - ввод неверных данных
                    lifetime_Small = 10;
                    JFrame frame = new JFrame("Внимание!");
                    frame.setSize(300, 100);
                    JTextArea textArea = new JTextArea();
                    String string1 = "Введены некорректные данные\n";
                    String string2 = "Время жизни пт. = 10\n";
                    String string3 = "(значение по умолчанию)\n";
                    textArea.append(string1);
                    textArea.append(string2);
                    textArea.append(string3);
                    textArea.setEditable(false);
                    frame.add(textArea);
                    frame.setVisible(true);
                    textArea_small.setText("10");
                }
                textArea_small.setEnabled(false);
                textArea_small.setFocusable(false);
            }
        });

        textArea_big.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {lifetime_Big = Integer.parseInt(textArea_big.getText());}
                catch (NumberFormatException e1) {
                    lifetime_Big = 10;
                    JFrame frame = new JFrame("Внимание!");
                    frame.setSize(300, 100);
                    JTextArea textArea = new JTextArea();
                    String string1 = "Введены некорректные данные\n";
                    String string2 = "Время жизни вз. = 10\n";
                    String string3 = "(значение по умолчанию)\n";
                    textArea.append(string1);
                    textArea.append(string2);
                    textArea.append(string3);
                    textArea.setEditable(false);
                    frame.add(textArea);
                    frame.setVisible(true);
                    textArea_big.setText("10");
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

    public static  Integer Return_lifetime_Big() {return lifetime_Big;}

    public static  Integer Return_lifetime_Small() {return lifetime_Small;}
}

