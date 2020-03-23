package tehprog;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class AbstractEmployee{
    private BufferedImage img;
    private Point coords;

    public AbstractEmployee() {

    }
    public AbstractEmployee(BufferedImage img, Point coords) {
        this.img = img;
        this.coords = coords;
    }

    public Point getCoords() {
        return coords;
    }

    public void setCoords(Point coords) {
        this.coords = coords;
    }


    public Image getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }
}
