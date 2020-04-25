package Contr;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class ComboB extends JPanel {

    JComboBox comboBox;
    JComboBox comboBox2;
    Float chance_birth_big = 0.0f;
    Float percent = 0.0f;

    public ComboB(){

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        ActionListener actionListener = new ActionListener() { // Взросл
            public void actionPerformed(ActionEvent e) {
                chance_birth_big = (Float) comboBox.getSelectedItem();
                comboBox.setFocusable(false);
            }
        };

        ActionListener actionListener2 = new ActionListener() { // Птенцы
            public void actionPerformed(ActionEvent e) {
                percent = (Float)comboBox2.getSelectedItem();
                comboBox2.setFocusable(false);
            }
        };


        Float[] items = {0.0f, 0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1f};

        comboBox = new JComboBox(items);
        comboBox.addActionListener(actionListener);

        comboBox2 = new JComboBox(items);
        comboBox2.addActionListener(actionListener2);

        JLabel text = new JLabel("P рождения вз. птицы");
        text.setFont(new Font("Courier New", Font.BOLD,11));
        text.setForeground(Color.BLACK);
        text.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel text2 = new JLabel("Птенцы относительно вз., %");
        text2.setFont(new Font("Courier New", Font.BOLD,11));
        text2.setForeground(Color.BLACK);
        text2.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(2,1));

        panel.add(text);
        panel.add(comboBox);

        panel2.add(text2);
        panel2.add(comboBox2);

        this.add(panel);
        add(Box.createRigidArea(new Dimension(2,0))); // Отступ в расположении
        this.add(panel2);
        add(Box.createRigidArea(new Dimension(3,0)));

        setVisible(true);

        setFocusable(false);
    }

    public Float Return_chance_birth_big() { return chance_birth_big; }

    public Float Return_percent() { return percent; }
}
