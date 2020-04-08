package com.nstu.tp.models;

import com.nstu.tp.models.abstracts.BaseFish;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class GuppyFish extends BaseFish {
    public GuppyFish(Point cords) throws IOException {
        super(cords);
        this.img = ImageIO.read(getClass().getResource("/GuppyFish.png"));
    }

    @Override
    public String toString() {
        return "GuppyFish";
    }
}
