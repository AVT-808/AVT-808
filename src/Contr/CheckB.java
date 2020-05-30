package Contr;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import java.awt.*;

public class CheckB extends JPanel {

    private JCheckBox Box;

    public CheckB() {
        Box = new JCheckBox("Показывать инф.",true);
        Box.setForeground(Color.BLUE);
        add(Box);
        Box.setFocusable(false);
    }

    public Boolean Return_nagatost() {
        return Box.isSelected(); // Стоит ли галочка (да\нет)
    }
}
