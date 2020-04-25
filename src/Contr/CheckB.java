package Contr;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class CheckB extends JPanel{

    private JCheckBox italicBox;
    Boolean d;

    public CheckB() {

        italicBox = new JCheckBox("Показывать информацию",true);
        add(italicBox);
        italicBox.setFocusable(false);
    }

    public Boolean Return_nagatost(){
        d = italicBox.isSelected(); // Стоит ли галочка (да\нет)
        return d;
    }
}
