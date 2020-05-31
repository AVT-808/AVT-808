package lab.habitat;

import java.awt.image.BufferedImage;


public interface ICreature extends IBehaviour {
    static boolean isCreationAllowed() {
        throw new UnsupportedOperationException("isCreationAllowed must be implemented");
    }

    BufferedImage getImage();
    int getTTL();

}