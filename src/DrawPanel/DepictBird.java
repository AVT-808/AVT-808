package DrawPanel;

import Array.*;
import Object.*;
import Serv.Client;

import javax.swing.*;
import java.awt.*;

public class DepictBird extends JPanel {


    public DepictBird () {
        Singleton.getM();
    }

    @Override
    protected synchronized void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);

        if (Singleton.getBird_s().isEmpty()) return; // Если пусто


        for (Bird bird : Singleton.getBird_s()) {
            setBackground(Color.GREEN);
            graphics.drawImage(bird.getImage(),bird.getPlace().x,bird.getPlace().y,50,50,null);
        }

        graphics.setColor(Color.BLACK);
        Font myFont = new Font("Times Roman", Font.ITALIC, 15);
        graphics.setFont(myFont);
        graphics.drawString("Подключено: " , 0, 30);
        for(int i = 0; i< Client.CountUsers();i++){
            graphics.drawString(Client.NameUsers(i), 0, 50+i*20);
        }
    }
}
