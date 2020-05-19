package Object;

import java.awt.*;
import java.io.*;
import javax.imageio.*;

public class Big extends Bird {

    public Big(Point place, Integer identifier, Integer time_luntik, Integer lifetime_Big) throws IOException {// Проверенное исключение: IOException

        super(place);

        this.identifier = identifier;
        this.time_luntik = time_luntik;
        this.go_away = time_luntik + lifetime_Big;

        this.ris = ImageIO.read(getClass().getResource("/duck2.png")); // Класс ImageIO, метод read
    }
}