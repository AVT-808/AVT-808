package AntFarm;

import javax.swing.*;
import java.awt.*;

public class DrawingAnts extends JPanel
{
    private Ant[] ants;

    public DrawingAnts(Ant[] ants)
    {
        this.ants = ants;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (ants[0] == null)
            return;
        for (int i = 0; ants[i] != null && i<500; i++)
        {
            if (ants[i].getClass() == AntWorker.class)
                g.drawImage(ants[i].getImage(), ants[i].getX(), ants[i].getY(), 100, 130, null);
            else
                g.drawImage(ants[i].getImage(), ants[i].getX(), ants[i].getY(), 150, 170, null);
        }
    }
}
