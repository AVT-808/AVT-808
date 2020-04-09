package Object;

import java.awt.*;
import java.io.*;
import javax.imageio.*;

public class Big extends Bird
{
    public Big(Point place) throws IOException
    {
        super(place);
        this.ris = ImageIO.read(getClass().getResource("/duck2.png"));
    }

}
