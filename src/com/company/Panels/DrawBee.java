package com.company.Panels;

import com.company.Models.Abstract.BaseBee;

import javax.swing.*;
import java.awt.*;


public class DrawBee extends JPanel {
    private BaseBee [] bees;


    public DrawBee(BaseBee[] bees){
        this.bees = bees;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bees[0] == null)
            return;
        for (int i=0; bees [i] != null; i++)
            if (i < 500)
            g.drawImage(bees[i].getBeeImage(),bees[i].getX(),bees[i].getY(),70,70,null);
        }
    }
