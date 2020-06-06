package Object;

import java.awt.*;
import java.io.*;
import javax.imageio.*;

public class Small extends Bird {

    static int x_Coord = 1;
    static int y_Coord = 1;
    Point place;

    public Small(Point place, Integer identifier, double time_luntik, double lifetime_Small) throws IOException {

        super(place);

        this.who = false;
        this.identifier = identifier;
        this.time_luntik = time_luntik;
        this.go_away = time_luntik + lifetime_Small;
        this.place = place;
        this.ris = ImageIO.read(getClass().getResource("/Small.png"));

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

    public static void setY_Coord(int y) { y_Coord=y; }


}
