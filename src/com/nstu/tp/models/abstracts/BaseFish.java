package com.nstu.tp.models.abstracts;

import java.awt.*;

public abstract class BaseFish {
    private Point cords;
    protected Image img;

    public BaseFish(Point cords) {
        this.cords = cords;
    }

    public Point getCords() {
        return cords;
    }

    public void setCords(Point cords) {
        this.cords = cords;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

}
