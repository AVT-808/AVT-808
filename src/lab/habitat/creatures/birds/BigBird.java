package lab.habitat.creatures.birds;

import lab.habitat.IHabitatMetrics;
import lab.habitat.creatures.Bird;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;


public class BigBird extends Bird {
    private static BufferedImage image;
    private static int ttl = 10;
    private static double bornChance = 0.5;
    public static int Period = 2;
    public static int ID;
    static {
        try {
            image = ImageIO.read(new File("src/lab/assets/habitat/creatures/birds/BigBird.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static boolean isCreationAllowed(IHabitatMetrics metrics) {

        //System.out.println(Math.random());

        return Math.random() < bornChance;
    }

    public static void setBornChance(double chance) {
        if(chance > 1 || chance < 0)
            throw new RuntimeException();

        bornChance = chance;
    }

    public static void setPeriod(int period) {
        Period = period;
    }

    public static void setTTL(int new_ttl) {
        ttl = new_ttl;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getTTL() {
        return ttl;
    }

    public int getID() {
        ID = (new Random().nextInt(65550));
        return ID;
    }
}

