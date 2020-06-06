package Serial;


import Array.Singleton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.util.LinkedList;

import Habit.Habitat;
import Object.*;

public class Serializ {
    File file = new File("C:\\Users\\алла\\IdeaProjects\\5ая\\src\\objects.dat");
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    FileOutputStream fileOutputStream;
    FileInputStream fileInputStream;
    JFileChooser jFileChooser = new JFileChooser();

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

        /*    Singleton bird_ss = Singleton.getM();
            if (!bird_ss.Bird_s.isEmpty()) {
                for (Bird bird : Singleton.getBird_s()) {
                    objectOutputStream.writeObject(bird);
                }
            }*/
            objectOutputStream.writeObject(Singleton.getBird_s());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void deserialization(Habitat habitat){
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            int res = jFileChooser.showDialog(null, "Open");
            if(res == JFileChooser.APPROVE_OPTION){
                file = jFileChooser.getSelectedFile();
                try {
                    fileInputStream = new FileInputStream(file);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        }

        try {
            objectInputStream = new ObjectInputStream(fileInputStream);

            habitat.Stop(1);
            Singleton.getM().setBird_s((LinkedList<Bird>)objectInputStream.readObject());

            Singleton bird_ss = Singleton.getM();

            if (!bird_ss.Bird_s.isEmpty()) {
                for (Bird bird : Singleton.getBird_s()) {
                    if (bird.getWho()){
                        bird.ris =ImageIO.read(getClass().getResource("/duck2.png"));
                    } else
                        bird.ris =ImageIO.read(getClass().getResource("/Small.png"));
                }
            }

            if (!bird_ss.Bird_s.isEmpty()) {
                for (Bird bird : Singleton.getBird_s()) {
                    bird.time_luntik = Habitat.getTime();
                    if (bird.getClass() == Small.class)
                    bird.go_away = bird.time_luntik + Existence.Life.Return_lifetime_Small();
                    else bird.go_away = bird.time_luntik + Existence.Life.Return_lifetime_Big();
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