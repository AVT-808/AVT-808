package term4;

import javax.swing.*;

public class CapitalHouse extends House {

    private static int period = 4;
    private static double probability = Math.random();
    private static int count;
    private static int lifetime = 10;

    CapitalHouse(){
        img = new ImageIcon("CapitalHouse.png");
        count++;
        id = Math.random();
        typeOfHouse = TypeOfHouse.CAPITAL;
        speed = (int) (Math.random() * ((Math.random() * 10) % 4) * 10);
    }

    @Override
    public void move() {
        if(inArea()){
            return;
        }
        if(getX() >= 70 && getY() >= 70){
            setPosition(getX() - speed, getY() - speed);
        }
        else if (getX() >= 70){
            setX(getX() - speed);
        }
        else if(getY() >= 70){
            setY(getY() - speed);
        }
    }

    @Override
    public boolean inArea() { return getX() < 1178 / 2 && getY() < 720 / 2; }

    public static int getCount() { return count; }

    public static void setPeriod(int period) { CapitalHouse.period = period; }

    public static int getPeriod() { return period; }

    public static double getProbability() { return probability; }

    public static void setProbability(double probability) { CapitalHouse.probability = probability; }

    public static int getLifetime() { return lifetime; }

    public static void setLifetime(int lifetime) { CapitalHouse.lifetime = --lifetime; }
}
