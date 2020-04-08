package com.nstu.tp.models;
import com.nstu.tp.models.abstracts.BaseFish;


public interface IBehaviour {
    void setCords(int x) ;
    void setImg(int y);
    int getImg();
    int getCords() ;
}
