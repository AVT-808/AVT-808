package Contr;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class CheckB extends JPanel {

    private JCheckBox Box;

    public CheckB() {
        Box = new JCheckBox("Показывать информацию",true);
        add(Box);
        Box.setFocusable(false);
    }

    public Boolean Return_nagatost() {
        return Box.isSelected(); // Стоит ли галочка (да\нет)
    }
}
