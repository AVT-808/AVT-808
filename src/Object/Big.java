package Object;

import java.awt.*;
import java.io.*;
import javax.imageio.*;


public class Big extends Bird{

    Point place;
    static int x_Coord = 1;
    static int y_Coord = 1;



    public Big(Point place, Integer identifier, double time_luntik, Integer lifetime_Big) throws IOException {// Проверенное исключение: IOException

        super(place);

        this.who = true;
        this.identifier = identifier;
        this.time_luntik = time_luntik;
        this.go_away = time_luntik + lifetime_Big;
        this.place = place;
        this.ris = ImageIO.read(getClass().getResource("/duck2.png")); // Класс ImageIO, метод read
    }


    @Override
    public synchronized void move() {

        double x = place.getX()+x_Coord;
        double y = place.getY()+y_Coord;

        if (x > 1250-70) x = 1250-70;
        if (x < 0) x = 0;
        if (y < 0) y = 0;
        if (y > 650-155) y = 650-155;

        place.setLocation(x, y);
    }

    public static void setX_Coord(int x) {
        x_Coord=x;
    }

    public static void setY_Coord(int y) {
        y_Coord=y;
    }

}