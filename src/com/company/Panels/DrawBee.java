package com.company.Panels;

import com.company.Habitat.BeesArray.Singleton;
import com.company.Models.Abstract.BaseBee;
import com.company.Server.Client;

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
        for (int i =0; i<Singleton.getInstance().getBees().size(); i++) {
            setBackground(Color.PINK);
            g.drawImage(Singleton.getInstance().getBees().get(i).getBeeImage(),Singleton.getInstance().getBees().get(i).getPlace().x,Singleton.getInstance().getBees().get(i).getPlace().y,40,40,null);
        }
        g.setColor(Color.GRAY);
        Font myFont = new Font("Times Roman", Font.BOLD, 15);
        g.setFont(myFont);
        g.drawString("Подключено: " , 1350, 15);
        for(int i = 0; i< Client.CountUsers();i++){
            g.drawString(Client.NameUsers(i), 1350, 30+i*15);
        }
    }
}
