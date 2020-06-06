package lab.habitat;

import java.awt.image.BufferedImage;


public interface ICreature {
    static boolean isCreationAllowed() {
        throw new UnsupportedOperationException("isCreationAllowed must be implemented");
    }
    BufferedImage getImage();
    int getID();
    int getTTL();
    double getX();
    double getY();
    void setX(double x);
    void setY(double y);
    void move(double x, double y);
    double getTargetX();
    double getTargetY();
    double step();
}
