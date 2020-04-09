package Object;

import java.awt.*;
import java.io.*;
import javax.imageio.*;


public class Small extends Bird
{
    public Small(Point place) throws IOException
    {
        super(place);
        this.ris = ImageIO.read(getClass().getResource("/Small.png"));
    }
}
