package com.company.Models;

import com.company.Models.Abstract.BaseBee;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Drone extends BaseBee {
    public Drone(int x, int y, Integer id, Integer timeOfBirth,Integer life_time_Drones) throws IOException {
        super(x, y);
        this.id = id;
        this.timeOfBirth = timeOfBirth;
        this.dead = timeOfBirth+life_time_Drones;
        this.beeImage = ImageIO.read(getClass().getResource("/drone.png"));
    }
}
