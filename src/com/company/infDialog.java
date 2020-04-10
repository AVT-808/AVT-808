package com.company;

import com.company.Habitat.Habitat;

import javax.swing.*;
import java.awt.*;

public class infDialog extends JDialog {
    private JButton okButton;
    private JButton closeButton;

    private JPanel info;
    private JTextArea textTime;
    private JPanel panel1;
    public infDialog(JFrame owner, Habitat window, int time){
        super(owner,"Information",true);
        setResizable(false);

        JPanel panelText = new JPanel();
        int quantityPet = window.dogs + window.cats;
        textTime.setText("Время работы: " + time +"\nКоличество кошек: " + window.cats + "\nКоличество собак: " + window.dogs + "\nВсего питомцев: " + quantityPet);
        textTime.setEnabled(false);



        panelText.add(textTime);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());


        okButton.addActionListener(e -> {
            owner.dispose();
            System.exit(0);
        });

        closeButton.addActionListener(e -> dispose());



        panel.add(okButton);
        panel.add(closeButton);
        add(panelText,BorderLayout.NORTH);
        add(panel, BorderLayout.SOUTH);
        setSize(400,400);
        setLocationRelativeTo(null);
        setVisible(true);

    }


}
