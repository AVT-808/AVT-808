package com.company.Panels;

import javax.swing.*;


public class ShowInfButton extends JPanel {
    JCheckBox chb;
    Boolean bl;

    public ShowInfButton() {
        chb = new JCheckBox("Показать информацию", true);
        add(chb);
        chb.setFocusable(false);
    }

    public Boolean return_chb() {
        bl = chb.isSelected(); // Стоит ли галочка да\нет return d;
        return bl;
    }
}
