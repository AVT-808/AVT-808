package com.company.Panels;

import com.company.Habitat.BeesArray.Singleton;
import com.company.Models.Abstract.BaseBee;

import javax.swing.*;
import java.awt.*;


public class DrawBee extends JPanel {
    private final Singleton bees;

    public DrawBee() {
        bees = Singleton.getInstance();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(bees.getBees().isEmpty())
            return;
        for (BaseBee bee : bees.getBees()) {
            setBackground(Color.PINK);
            g.drawImage(bee.getBeeImage(),bee.getX(),bee.getY(),40,40,null);
        }
    }
}
