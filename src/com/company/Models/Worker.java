package com.company.Models;


import com.company.Models.Abstract.BaseBee;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Worker extends BaseBee {
    Point place;

    int x,y;
    boolean sis = true;
    int speed = 1;


    public Worker(Point place, Integer id, Integer timeOfBirth,Integer life_time_Workers) throws IOException {
        super(place);
        this.id = id;
        this.timeOfBirth = timeOfBirth;
        this.dead = timeOfBirth + life_time_Workers;
        this.beeImage = ImageIO.read(getClass().getResource("/Worker.png"));
        this.place = place;


        x = place.x;
        y = place.y;
    }

    @Override
    public synchronized void  moving() {

        if (sis) {
            if(place.x==0 && place.y>0){
                place.y-=speed;
            }if(place.x>0 && place.y==0){
                place.x-=speed;
            }if(place.x>0 && place.y>0){
                place.x-=speed;
                place.y-=speed;
            }
            if(place.x<0){
                place.x=0;
            }
            if(place.y<0) {
                place.y = 0;
            }
            if(place.x==0 && place.y==0){

                sis = false;
            }
        }

        else{

            if(place.x==x && place.y<y){
                place.y+=speed;

            }if(place.x<x && place.y==y){
                place.x+=speed;
            }if(place.x<x && place.y<y){
                place.x+=speed;
                place.y+=speed;
            }
            if(place.x>x){
                place.x=x;
            }
            if(place.y>y){
                place.y=y;
            }

            if(place.x==x && place.y==y){
                sis = true;
            }
        }
        place.setLocation(place.x,place.y);

    }

}
