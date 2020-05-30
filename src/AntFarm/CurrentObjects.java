package AntFarm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.TreeMap;

public class CurrentObjects extends JDialog
{
    private JButton buttonOK = new JButton("OK");
    private JTextArea textArea = new JTextArea();
    private JScrollPane scrollPane = new JScrollPane(textArea);

    public CurrentObjects (TreeMap <Integer, Integer> birthTimes)
    {
        super(new JFrame(), "Текущие объекты", true);
        setSize(400,350);
        add(buttonOK, BorderLayout.SOUTH);
        add(scrollPane);
        textArea.setEditable(false);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        String text = "";

        for (Map.Entry<Integer,Integer> e : birthTimes.entrySet())
        {
            text+= "Индентификатор: " + e.getKey() + "\nВремя рождения: " + e.getValue() + "\n\n";
        }
        textArea.setText(text);

        buttonOK.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                setVisible(false);
            }
        });
    }
}
