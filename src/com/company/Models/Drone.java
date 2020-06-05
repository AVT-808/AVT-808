package com.company.Models;

import com.company.Habitat.BeesArray.Singleton;
import com.company.Models.Abstract.BaseBee;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class Drone extends BaseBee {


    Point place;
    int x, y;
    int speed = 1;

    public Drone(Point place, Integer id, Integer timeOfBirth,Integer life_time_Drones) throws IOException {
        super(place);
        this.id = id;
        this.timeOfBirth = timeOfBirth;
        this.dead = timeOfBirth+life_time_Drones;
        this.beeImage = ImageIO.read(getClass().getResource("/drone.png"));
        this.place=place;
    }

    @Override
    public synchronized void moving() {



        if(place.x > x){
            place.x-=speed;
        }
        if(place.x < x){
            place.x+=speed;
        }
        if(place.y > y){
            place.y-=speed;
        }
        if(place.y < y){
            place.y+=speed;
        }

        place.setLocation(place.x, place.y);


    }

    public void Bugalteria(){
      x = new Random().nextInt(1100);
      y = new Random().nextInt(700);

    }
}


