package term4;

import javax.swing.*;

public class WoodenHouse extends House {

    private static int period = 3;
    private static  double probability = Math.random();
    private static int count;

    WoodenHouse(){
        img = new ImageIcon("WoodenHouse.png");
        count++;
    }

    public static int getCount() { return count; }

    public static int getPeriod() { return period; }

    public static void setPeriod(int period) { WoodenHouse.period = period; }

    public static double getProbability() { return probability; }

    public static void setProbability(double probability) { WoodenHouse.probability = probability; }
}
