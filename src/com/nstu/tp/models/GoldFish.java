package com.nstu.tp.models;

import com.nstu.tp.models.abstracts.BaseFish;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class GoldFish extends BaseFish {
    public GoldFish(Point cords) throws IOException {
        super(cords);
        this.img = ImageIO.read(getClass().getResource("/GoldFish.jpg"));
    }

    @Override
    public String toString() {
        return "GoldFish";
    }
}
