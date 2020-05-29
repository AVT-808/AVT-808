package com.company.Models;

import com.company.Models.Abstract.BaseBee;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Worker extends BaseBee {
    public Worker(int x, int y, Integer id, Integer timeOfBirth,Integer life_time_Workers) throws IOException {
        super(x, y);
        this.id = id;
        this.timeOfBirth = timeOfBirth;
        this.dead = timeOfBirth + life_time_Workers;
        this.beeImage = ImageIO.read(getClass().getResource("/Worker.png"));
    }
}
