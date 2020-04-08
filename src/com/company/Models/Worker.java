package com.company.Models;

import com.company.Models.Abstract.BaseBee;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Worker extends BaseBee {
    public Worker(int x, int y) throws IOException {
        super(x, y);
        this.beeImage = ImageIO.read(getClass().getResource("/Worker.png"));
    }
}
