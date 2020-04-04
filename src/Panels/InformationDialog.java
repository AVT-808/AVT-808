package Panels;

import javax.swing.*;
import java.awt.*;

public class InformationDialog extends JDialog {

    private JLabel timerResult;
    private JLabel normalRabbitsResult;
    private JLabel whiteRabbitsResult;

    public InformationDialog(JFrame frame, String title, Integer time, Integer normalRabbitsAmount, Integer whiteRabbitsAmount) {
        super(frame, title, false);

        this.timerResult = new JLabel("Время симуляции: " + time);
        this.timerResult.setFont(new Font("Courier New", Font.ITALIC,16));
        this.timerResult.setForeground(Color.RED);

        this.normalRabbitsResult = new JLabel("Количество обычных кроликов: " + normalRabbitsAmount);
        this.normalRabbitsResult.setFont(new Font("Calibri", Font.ITALIC,15));
        this.normalRabbitsResult.setForeground(Color.GREEN);

        this.whiteRabbitsResult = new JLabel("Количество кроликов альбиносов: " + whiteRabbitsAmount);
        this.whiteRabbitsResult.setFont(new Font("Times New Roman", Font.ITALIC,14));
        this.whiteRabbitsResult.setForeground(Color.BLUE);

        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(3,1));

        this.timerResult.setHorizontalAlignment(SwingConstants.CENTER);
        add(this.timerResult);


        this.normalRabbitsResult.setHorizontalAlignment(SwingConstants.CENTER);
        add(this.normalRabbitsResult);


        this.whiteRabbitsResult.setHorizontalAlignment(SwingConstants.CENTER);
        add(this.whiteRabbitsResult);

        this.setModal(true);

    }

    public void viewInformation(){
        this.timerResult.setVisible(true);
        this.normalRabbitsResult.setVisible(true);
        this.whiteRabbitsResult.setVisible(true);
        setVisible(true);
    }
}
