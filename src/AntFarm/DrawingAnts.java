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
    protected synchronized void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (Singleton.getAnts().isEmpty())
            return;
        for (int i = 0; i < ants.getArraySize(); i++) {
            if (ants.getAnts().get(i).getClass() == AntWorker.class)
                g.drawImage(ants.getAnts().get(i).getImage(), ants.getAnts().get(i).getX(), ants.getAnts().get(i).getY(), 100, 130, null);
            else
                g.drawImage(ants.getAnts().get(i).getImage(), ants.getAnts().get(i).getX(), ants.getAnts().get(i).getY(), 150, 170, null);
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        g.drawString("Подключено: " , 300, 30);
        for(int i = 0; i<Client.getUsers().size();i++)
        {
            g.drawString(Client.getUsers().get(i), 300, 50+i*20);
        }
    }
}
