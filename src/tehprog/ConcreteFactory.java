package tehprog;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ConcreteFactory implements AbstractFactory{
    private int managerTime= 5;
    private int managerProb = 100;
    private int managerLimit = 20;
    private long managerLastSec;

    private int developerTime=3;
    private int developerProb = 70;
    private long developerLastSec;

    private BufferedImage developerImg;
    private BufferedImage managerImg;

    private int managerSpawned;
    private int developerSpawned;

    public ConcreteFactory(){
        try {
            managerImg = ImageIO.read(getClass().getResource("/res/manager.png"));
        } catch (IOException e) {
            System.out.println("manager img error");
        }
        try {
            developerImg = ImageIO.read(getClass().getResource("/res/developer.png"));
        } catch (IOException e) {
            System.out.println("programmer img error");
        }
    }


    public AbstractEmployee getEmployee(long time, Dimension spawnZone) {
        if(time - developerLastSec >= developerTime) {
            developerLastSec = time;
            if(Math.random()*100 < developerProb) {
                developerSpawned++;
                return new Developer(developerImg, new Point((int) (Math.random() * spawnZone.width),
                                                             (int) (Math.random() * spawnZone.height)));
            }
        }
        if(time - managerLastSec >= managerTime){
            managerLastSec = time;
            if(managerSpawned < developerSpawned * managerLimit/100 && Math.random()*100 < managerProb) {
                managerSpawned++;
                return new Manager(managerImg, new Point((int) (Math.random() * spawnZone.width),
                                                         (int) (Math.random() * spawnZone.height)));
            }
        }

        return null;
    }

    public int getDeveloperSpawned() {
        return developerSpawned;
    }

    public int getManagerSpawned() {
        return managerSpawned;
    }

    public void reset(){
        managerSpawned = 0;
        developerSpawned = 0;

        developerLastSec = 0;
        managerLastSec = 0;
    }
}
