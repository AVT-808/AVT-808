package AntFarm;

import javax.swing.*;
import java.awt.*;

public class DrawingAnts extends JPanel
{
    private Singleton ants;

    public DrawingAnts()
    {
        ants = Singleton.getSingleton();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (Singleton.getAnts().isEmpty())
            return;
        for (int i = 0; i< ants.getArraySize() && i<500; i++)
        {
            if (ants.getAnts().get(i).getClass() == AntWorker.class)
                g.drawImage(ants.getAnts().get(i).getImage(), ants.getAnts().get(i).getX(), ants.getAnts().get(i).getY(), 100, 130, null);
            else
                g.drawImage(ants.getAnts().get(i).getImage(), ants.getAnts().get(i).getX(), ants.getAnts().get(i).getY(), 150, 170, null);
        }
    }
}
