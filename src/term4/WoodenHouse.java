package term4;

import javax.swing.*;

public class WoodenHouse extends House {

    public static final int PERIOD = 3;
    public static final double PROBABILITY = Math.random();
    private static int count;

    WoodenHouse(){
        img = new ImageIcon("WoodenHouse.png");
        count++;
    }

    public static int getCount() { return count; }
}
