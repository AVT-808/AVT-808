package Object;

import java.awt.*;
import java.io.*;
import javax.imageio.*;


public class Small extends Bird {

    public Small(Point place, Integer identifier, Integer time_luntik, Integer lifetime_Small) throws IOException {

        super(place);

        this.identifier = identifier;
        this.time_luntik = time_luntik;
        this.go_away = time_luntik + lifetime_Small;

        this.ris = ImageIO.read(getClass().getResource("/Small.png"));
    }
}
