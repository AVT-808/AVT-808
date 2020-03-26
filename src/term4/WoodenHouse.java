package term4;

import javax.swing.*;

public class WoodenHouse extends House {

    private static int period = 3;
    private static  double probability = Math.random();
    private static int count;
    private static int lifetime = 9;

    WoodenHouse(){
        img = new ImageIcon("WoodenHouse.png");
        count++;
        id = Math.random();
        typeOfHouse = TypeOfHouse.WOODEN;
    }

    public static int getCount() { return count; }

    public static int getPeriod() { return period; }

    public static void setPeriod(int period) { WoodenHouse.period = period; }

    public static double getProbability() { return probability; }

    public static void setProbability(double probability) { WoodenHouse.probability = probability; }

    public static int getLifetime() { return lifetime; }

    public static void setLifetime(int lifetime) { WoodenHouse.lifetime = --lifetime; }
}
