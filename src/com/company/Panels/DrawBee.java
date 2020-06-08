package com.company.Panels;

import com.company.Habitat.BeesArray.Singleton;
import com.company.Models.Abstract.BaseBee;

import javax.swing.*;
import java.awt.*;


public class DrawBee extends JPanel {
    private final Singleton bees;

    public  DrawBee() {
        bees = Singleton.getInstance();
    }

    @Override
    protected synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(Singleton.getInstance().getBees().isEmpty())
            return;
        for (BaseBee bee : Singleton.getInstance().getBees()) {
            setBackground(Color.PINK);
            g.drawImage(bee.getBeeImage(),bee.getPlace().x,bee.getPlace().y,40,40,null);
        }
    }
}
