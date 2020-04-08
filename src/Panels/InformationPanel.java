package Panels;

import javax.swing.*;
import java.awt.*;

public class InformationPanel extends JPanel {
    private final JLabel rabbitsAmount;
    private final JLabel timer;

    public InformationPanel() {
        setLayout(new GridLayout(1,2));
        rabbitsAmount = new JLabel("Количество рожденных кроликов: 0");
        timer = new JLabel("Таймер: 0");
        add(rabbitsAmount);
        add(timer);
    }

    public void setRabbitsAmount(Integer rabbitsAmount) {
        this.rabbitsAmount.setText("Количество рожденных кроликов: " + rabbitsAmount);
    }

    public void setTimer(Integer timer) {
        this.timer.setText("Таймер: " + timer);
    }

    public void setTimerVisibility(Boolean isVisible) {
        timer.setVisible(isVisible);
    }

}
