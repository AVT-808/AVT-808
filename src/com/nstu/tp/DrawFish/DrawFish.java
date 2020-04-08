package com.nstu.tp.DrawFish;

import com.nstu.tp.models.abstracts.BaseFish;
import java.awt.*;
import javax.swing.*;



    public class DrawFish extends JPanel {


        BaseFish fish[];
        int Mass;


        public DrawFish(BaseFish fish[], int Mass)
        {
            this.fish = fish;
            this.Mass=Mass;

        }

        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            if (fish[0] == null) {
                return;
            }

            for (int i=0; fish[i] != null; i++)

                if (i < Mass)
                    g.drawImage(fish[i].getImg(),fish[i].getCords().x,fish[i].getCords().y,50,50,null);


        }
    }






