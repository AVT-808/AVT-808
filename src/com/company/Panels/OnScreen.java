package com.company.Panels;

import com.company.Habitat.Habitat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;

public class OnScreen extends JDialog {
    protected JTextArea textArea;
    protected JButton ok_butt;
    TreeMap<Integer,Integer> treeMap;

    public OnScreen(  Habitat habitat){
        super(habitat,"Текущий объект");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        treeMap = habitat.returnTreeMap();
        for (Map.Entry<Integer,Integer> entry: treeMap.entrySet()) {
            String string = "Идентификатор- " + entry.getKey() + "  Время рождения- " + entry.getValue()+"\n";
            textArea.append(string);
        }

        textArea.setEditable(false); // Не редактируемое поле

        setResizable(false); // Не изменять размеры окна
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        setSize(new Dimension(300,300));

        add(scrollPane);
        scrollPane.setSize(300,280);

        this.setModal(true); // Блокирует остальные окна

       ok_butt = new JButton("ОК"); // Чтоб убрать окно
        ok_butt.setAlignmentX(Component.LEFT_ALIGNMENT);
        ok_butt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        add(ok_butt);
    }
}
