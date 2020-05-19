package Existence;

import Habit.Habitat;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class OnTheField extends JDialog { // Информация из кнопки "Текущие объекты"

    protected JTextArea textArea;
    protected JButton button_ok;
    HashMap<Integer, Integer> hashMap;

    public OnTheField(Habitat habitat) {

        super(habitat.jFrame, "Текущие объекты", false);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea); // Для прокрутки текста

        hashMap = habitat.Return_hashMap();

        for (HashMap.Entry<Integer,Integer> entry : hashMap.entrySet()) {
            String string = "Идентификатор: " + entry.getKey() + "  Время рождения: " + entry.getValue()+"\n";
            textArea.append(string);
        }

        textArea.setEditable(false); // Не редактируемое поле

        setResizable(false); // Не изменять размеры окна
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        setSize(new Dimension(300,300));

        add(scrollPane);
        scrollPane.setSize(300,280);

        this.setModal(true); // Блокирует остальные окна

        button_ok = new JButton("ОК"); // Чтоб убрать окно
        button_ok.setAlignmentX(Component.RIGHT_ALIGNMENT); // Расположить справа

        button_ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            } // Исчезает, при нажатии "Ок"
        });

        add(button_ok);
    }
}

