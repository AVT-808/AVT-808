package Contr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inf extends JDialog {

    private final JTextArea textArea;
    private final JButton button_ok;
    private final JButton button_cancel;
    Boolean ddd = true;

    public Inf(JFrame habitat, Integer time, Integer number_of_Small, Integer number_of_Big) {

        super(habitat, "Inf", false);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        textArea = new JTextArea();

        String string_time = "Время симуляции: " + time + "\n";
        String string_small = "Птенцы: " + number_of_Small + "\n";
        String string_big = "Взрослые птицы: " + number_of_Big + "\n";

        textArea.append(string_time);
        textArea.append(string_small);
        textArea.append(string_big);
        textArea.setEditable(false); // Не редактируемое поле

        setResizable(false); // Не изменять размеры окна
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        setSize(new Dimension(300,300));

        add(textArea);

        textArea.setMaximumSize(new Dimension(300, 280));

        this.setModal(true); // Блокирует остальные окна

        button_ok = new JButton("Oк");
        button_cancel = new JButton("Отмена");

        button_ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ddd=true;
                setVisible(false);
            }
        });

        button_cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ddd = false;
                setVisible(false);
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));

        panel.add(button_ok);
        panel.add(button_cancel);

        add(panel);
        panel.setMaximumSize(new Dimension(160, 20));

        panel.setVisible(true);
    }

    public Boolean Return_ddd() { return ddd; }
}

