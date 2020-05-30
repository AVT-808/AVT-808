package Move;

import Object.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ChangeCoord {

    static int i=0;

    public static void T() { // Таймер, определяющий период и направление движения// Вызывается в конструкторе SmallAI
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                i++;
                if (i%8==0) // Изменение направления движения каждые 8 сек
                {
                    Random coord = new Random();
                    int x_Coord = coord.nextInt(5) + 1; // Значения, которые приплюсуются к текущим place
                    if (x_Coord % 2 == 0) x_Coord = x_Coord * (-1);
                    int y_Coord = coord.nextInt(5) + 1;
                    if (y_Coord % 2 == 0) y_Coord = y_Coord * (-1);
                    Small.setX_Coord(x_Coord);
                    Small.setY_Coord(y_Coord);
                    Big.setX_Coord(x_Coord);
                    Big.setY_Coord(y_Coord);
                }
            }
        });
        timer.start();
    }
}
