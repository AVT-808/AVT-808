package term4;

import javax.swing.*;

public class CapitalHouse extends House {

    public static final int PERIOD = 4;
    public static final double PROBABILITY = Math.random();
    private static int count;

    CapitalHouse(){
        img = new ImageIcon("CapitalHouse.png");
        count++;
    }

    public static int getCount() { return count; }
}
