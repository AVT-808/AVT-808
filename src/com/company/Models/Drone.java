package com.company.Models;

import com.company.Models.Abstract.BaseBee;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Drone extends BaseBee {
    public Drone(int x, int y) throws IOException {
        super(x, y);
        this.beeImage = ImageIO.read(getClass().getResource("/drone.png"));
    }
}
