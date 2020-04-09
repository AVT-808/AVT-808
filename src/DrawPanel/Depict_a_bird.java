package DrawPanel;

import Object.*;
import javax.swing.*;
import java.awt.*;

public class Depict_a_bird extends JPanel {

    Bird bird_s[];
    int Mass;

   public Depict_a_bird(Bird bird_s[], int Mass) {
        this.bird_s = bird_s;
        this.Mass = Mass;
    }

    @Override
    protected void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);

        if (bird_s[0] == null)
            return;

        for (int i=0; bird_s [i] != null; i++)
            if (i < Mass) {
                setBackground(Color.GREEN);
                graphics.drawImage(bird_s[i].getImage(),bird_s[i].getPlace().x,bird_s[i].getPlace().y,50,50,null);
            }
    }
}
