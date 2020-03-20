package term4;

import javax.swing.*;

public class CapitalHouse extends House {

    private static int period = 4;
    private static double probability = Math.random();
    private static int count;

    CapitalHouse(){
        img = new ImageIcon("CapitalHouse.png");
        count++;
    }

    public static int getCount() { return count; }

    public static void setPeriod(int period) { CapitalHouse.period = period; }

    public static int getPeriod() { return period; }

    public static double getProbability() { return probability; }

    public static void setProbability(double probability) { CapitalHouse.probability = probability; }

}
