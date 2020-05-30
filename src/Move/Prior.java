package Move;

import java.awt.*;
import javax.swing.*;

public class Prior extends JPanel {

    JComboBox<Integer> comboBoxS;
    JComboBox<Integer> comboBoxB;
    JComboBox<Integer> comboBoxM;

    public Prior () {

        setLayout(new GridLayout(2, 3));

        JLabel textS = new JLabel("Пт");
        textS.setFont(new Font("Courier New", Font.BOLD,11));
        textS.setForeground(Color.BLUE);
        textS.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel textB = new JLabel("Вз");
        textB.setFont(new Font("Courier New", Font.BOLD,11));
        textB.setForeground(Color.BLUE);
        textB.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel textM = new JLabel("Осн");
        textM.setFont(new Font("Courier New", Font.BOLD,11));
        textM.setForeground(Color.BLUE);
        textM.setHorizontalAlignment(SwingConstants.CENTER);

        add(textB);
        add(textS);
        add(textM);

        Integer[] items = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        comboBoxS = new JComboBox<>(items);
        comboBoxS.setForeground(Color.BLUE);
        comboBoxB = new JComboBox<>(items);
        comboBoxB.setForeground(Color.BLUE);
        comboBoxM = new JComboBox<>(items);
        comboBoxM.setForeground(Color.BLUE);

        add(comboBoxB);
        add(comboBoxS);
        add(comboBoxM);

        setVisible(true);
        setFocusable(false);

        comboBoxS.addActionListener(e -> {
            SmallAI.getThread().setPriority((int)comboBoxS.getSelectedItem());
            System.out.println("Поток "+ SmallAI.getThread().getName()+" Пр: " + SmallAI.getThread().getPriority());
            comboBoxS.setFocusable(false);
        });

        comboBoxB.addActionListener(e -> {
                BigAI.getThread().setPriority((int)comboBoxB.getSelectedItem());
                System.out.println("Поток "+ BigAI.getThread().getName()+" Пр: " + BigAI.getThread().getPriority());
                comboBoxB.setFocusable(false);
        });

        comboBoxM.addActionListener(e -> {
                int pr = (int)comboBoxM.getSelectedItem();
                Thread.currentThread().setPriority(pr); // Получение ссылки на объект главного потока
                System.out.println("Поток "+ Thread.currentThread().getName()+" Пр: " + Thread.currentThread().getPriority());
                comboBoxM.setFocusable(false);
        });
    }

}
