package com.company.Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Information extends JDialog {

    private final JTextArea textArea;
    private final JButton ok;
    private final JButton cancel;
    Boolean b = true;

    public Information(JFrame frame, String title, int time, int allWorkersBirth, int allDronesBirth) {
        super(frame, "Информация", false);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        textArea = new JTextArea();//создаем текстовое окно

        String s_time = "Время симуляции: " + time + "\n";
        String s_allWorkersBirth = "Количество рабочих пчел: " + allWorkersBirth + "\n";
        String s_allDronesBirth = "Количество трутней: " + allDronesBirth + "\n";

        textArea.append(s_time);
        textArea.append(s_allWorkersBirth);
        textArea.append(s_allDronesBirth);
        textArea.setEditable(false); // Не редактируемое поле

        setResizable(false); // Не изменять размеры окна
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        setSize(new Dimension(300, 300));
        textArea.setFont(new Font("Dialog", Font.BOLD, 14));
        textArea.setTabSize(10);
        add(textArea);

        textArea.setMaximumSize(new Dimension(300, 280));
        this.setModal(true); // Блокирует остальные окна

        ok = new JButton("Ок");
        cancel = new JButton("Отмена");

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b = true;
                setVisible(false);
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b = false;
                setVisible(false);
            }
        });

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(1, 2));


        jPanel.setLayout(new GridLayout(1, 2));
        jPanel.add(ok);
        jPanel.add(cancel);

        add(jPanel);
        jPanel.setMaximumSize(new Dimension(180, 20));

        jPanel.setVisible(true);

    }

    public Boolean return_B(){
        return b;
    }
}
