package Contr;

import javax.swing.*;
import java.awt.*;

public class Line extends JPanel {

    private static final JLabel timer = new JLabel("0   Время: 0");

    public Line() {

        setLayout(new GridLayout(1,1));
        add(timer);
        setVisible(true);
    }

    public void setTimer(Integer timer, Integer number) {
        this.timer.setText(number + "   Время: " + timer);
    }

    public static void Clock(Boolean p) {
        timer.setVisible(p);
    }
}
