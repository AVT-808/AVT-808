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

        //add(new MyComponent(thisWindow,time));


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

//    static class MyComponent extends JComponent{
//        private Habitat window;
//        private int myTimer;
//
//        MyComponent(Habitat thatWindow, int time){
//            window = thatWindow;
//            myTimer = time;
//        }
//        @Override
//        protected void paintComponent(Graphics g) {
//            super.paintComponent(g);
//            g.setColor(Color.BLACK);
//            Font myFont=new Font("Arial",Font.BOLD,20);
//            g.setFont(myFont);
//            g.drawString("Время работы: " + myTimer,100,100);
//            g.setColor(Color.GREEN);
//            myFont=new Font("Times new Roman",Font.ITALIC,20);
//            g.setFont(myFont);
//            g.drawString("Количество кошек: "+window.cats ,100,140);
//            g.setColor(Color.ORANGE);
//            myFont=new Font("Calibri",Font.PLAIN,20);
//            g.setFont(myFont);
//            g.drawString("Количество собак: "+window.dogs ,100,180);
//            g.setColor(Color.RED);
//            myFont=new Font("Georgia",Font.BOLD + Font.ITALIC,20);
//            g.setFont(myFont);
//            int quantityPet = window.dogs + window.cats;
//            g.drawString("Всего питомцев: "+quantityPet ,100,220);
//
//        }
//    }

}
