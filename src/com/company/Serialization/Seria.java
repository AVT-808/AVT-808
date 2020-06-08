package com.company.Serialization;

import com.company.Habitat.BeesArray.Singleton;
import com.company.Habitat.Habitat;
import com.company.Models.Abstract.BaseBee;
import com.company.Models.Drone;
import com.company.Models.Worker;
import com.company.Panels.LifeTimeOfBees;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Seria {
    File file = new File("objects.dat");
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    FileOutputStream fileOutputStream;
    FileInputStream fileInputStream;


    public void serialization(){
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            try {
                if(file.createNewFile()){
                    System.out.println("File created");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        try {
            objectOutputStream = new ObjectOutputStream(fileOutputStream);


            objectOutputStream.writeObject(Singleton.getInstance().getBees());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

   public void deserialization(Habitat habitat) throws FileNotFoundException {
    Singleton.getInstance().clear();

       fileInputStream = new FileInputStream(file);
        try {
            objectInputStream = new ObjectInputStream(fileInputStream);


            Singleton.getInstance().setBees((ArrayList<BaseBee>)objectInputStream.readObject());

            Singleton bird_ss = Singleton.getInstance();

            if (!bird_ss.bees.isEmpty()) {
                for (BaseBee bee : Singleton.getInstance().getBees()) {
                    bee.timeOfBirth = Habitat.getTime();
                    if (bee instanceof Worker){
                        bee.dead = bee.timeOfBirth+ LifeTimeOfBees.life_time_Workers;
                        bee.beeImage = ImageIO.read(getClass().getResource("/Worker.png"));
                    } else{
                        bee.beeImage =ImageIO.read(getClass().getResource("/drone.png"));
                    bee.dead = bee.timeOfBirth+ LifeTimeOfBees.life_time_Drones;
                    }
                }
            }



        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            objectInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
