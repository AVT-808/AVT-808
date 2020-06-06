package Serial;

import Habit.Habitat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtSerial extends JPanel {

    private static final JButton button_save =  new JButton("Сохранить");
    private static final JButton button_load = new JButton("Загрузить");


    public ButtSerial(Habitat habitat) {

        Serializ serializ = new Serializ();

        setLayout(new GridLayout(1, 2));


        button_save.addActionListener(e -> {
            serializ.serialization();
        });

        button_load.addActionListener(e -> {
            serializ.deserialization(habitat);
        });

        add(button_save);
        add(button_load);

    }

    public static JButton Return_start() { return button_save; } // Для доступа к этим же кнопкам в Keyboard

    public static JButton Return_stop() { return button_load; }

}

