package lab.habitat.creatures.birds;

import lab.habitat.IHabitatMetrics;
import lab.habitat.creatures.Bird;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;


public class SmallBird extends Bird {
    private static BufferedImage image;
    private static int ttl = 10;
    private static double critical = 0.3;
    private static double bornChance = 0.5;
    public static int Period = 1;
    public static int ID;
    private double step = 0.01 * Math.random() + 0.005;
    static {
        try {
            image = ImageIO.read(new File("src/lab/assets/habitat/creatures/birds/SmallBird.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static boolean isCreationAllowed(IHabitatMetrics metrics) {
        int adult_count = metrics.getCreatureCountByType(BigBird.class);
        if(adult_count != 0 && (double) metrics.getCreatureCountByType(SmallBird.class) / adult_count < critical)
            return Math.random() < bornChance;
        else
            return false;
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
    public double step() {
        return step;
    }
}
