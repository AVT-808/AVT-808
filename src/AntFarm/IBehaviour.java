package AntFarm;

import java.awt.*;

public interface IBehaviour
{
    void setX(int x);
    int getX();
    void setY(int y);
    int getY();
    void setImage(Image image);
    Image getImage();
    void move();
}
