package DrawPanel;

import Array.*;
import Object.*;

import javax.swing.*;
import java.awt.*;

public class Depict_a_bird extends JPanel {

    private final Singleton Bird_s;

    public Depict_a_bird () { Bird_s = Singleton.getM(); }

    @Override
    protected void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);

       if (Bird_s.getBird_s().isEmpty()) return;

       for (Bird bird : Bird_s.getBird_s()) {
           setBackground(Color.GREEN);
           graphics.drawImage(bird.getImage(),bird.getPlace().x,bird.getPlace().y,50,50,null);
       }
    }
}
