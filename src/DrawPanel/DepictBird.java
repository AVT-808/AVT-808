package DrawPanel;

import Array.*;
import Object.*;

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
    }

}
