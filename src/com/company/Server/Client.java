package com.company.Server;

import com.company.Habitat.BeesArray.Singleton;
import com.company.Habitat.Habitat;
import com.company.Models.Abstract.BaseBee;
import com.company.Models.Worker;
import com.company.Panels.LifeTimeOfBees;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Client  {

    private static ArrayList<String> users = new ArrayList<>();

    private static String user;
    private static Socket socket;
    private static int countUsers;

    private static final String SET_USER = "setUser";
    private static final String DOWNLOAD = "download";
    private static final String GET_USERS_COUNT = "getCount";
    private static final String DISCONNECT = "disconnect";
    private static final String GET_USER_NAME = "getUserName";
    private static final String SEND_OBJ = "sendObj";
    private static final String SEND_USER = "sendUser";



    public static void Connection() {
        user = JOptionPane.showInputDialog("Log in:");
        try {
            socket = new Socket("localhost", 8080);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeUTF(user);
            objectOutputStream.writeUTF(SET_USER);
            objectOutputStream.writeObject(null);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int CountUsers(){
        try {
            socket = new Socket("localhost", 8080);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeUTF(user);
            objectOutputStream.writeUTF(GET_USERS_COUNT);
            objectOutputStream.flush();

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            countUsers = (Integer) objectInputStream.readObject();
            objectInputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return countUsers;
    }

    public static void Download(){

        if(Objects.equals(user, SendUsers())){
            try {
                socket = new Socket("localhost", 8080);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeUTF(user);
                objectOutputStream.writeUTF(DOWNLOAD);
                objectOutputStream.writeBoolean(false);
                objectOutputStream.flush();

                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
               ArrayList<BaseBee> bee = (ArrayList<BaseBee>) objectInputStream.readObject();

                for(int i = 0; i<bee.size();i++) {
                    bee.get(i).timeOfBirth = Habitat.getTime();
                    if (bee.get(i) instanceof Worker){
                        bee.get(i).dead = bee.get(i).timeOfBirth+ LifeTimeOfBees.life_time_Workers;
                        bee.get(i).beeImage = ImageIO.read(Client.class.getResource("/Worker.png"));
                    } else{
                        bee.get(i).beeImage =ImageIO.read(Client.class.getResource("/drone.png"));
                        bee.get(i).dead = bee.get(i).timeOfBirth+ LifeTimeOfBees.life_time_Drones;
                    }
                }
                Singleton.getInstance().getBees().addAll(bee);
                objectInputStream.close();

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

    }


    public static String NameUsers(int i){
        try {
            socket = new Socket("localhost", 8080);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeUTF(user);
            objectOutputStream.writeUTF(GET_USER_NAME);
            objectOutputStream.flush();

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            users = (ArrayList<String>) objectInputStream.readObject();
            objectInputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users.get(i);
    }


    public static void sendObj(ArrayList<BaseBee> bees){

        String sendUser  = JOptionPane.showInputDialog("Whom:");
        ArrayList<BaseBee> sendBees = new ArrayList<>();
        int random = new Random().nextInt(bees.size()-1);
        if(random<0){
            random = 0;
        }
        for(int i = 0; i<random;i++){
            sendBees.add(bees.get(i));
            bees.remove(0);
        }
        try {
            socket = new Socket("localhost", 8080);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeUTF(user);
            objectOutputStream.writeUTF(SEND_OBJ);
            objectOutputStream.writeObject(sendBees);
            objectOutputStream.writeUTF(sendUser);
            objectOutputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String SendUsers(){
        String sendUser = "";
        try {
            socket = new Socket("localhost", 8080);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeUTF(user);
            objectOutputStream.writeUTF(SEND_USER);
            objectOutputStream.flush();

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            sendUser = (String) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return sendUser;
    }

    public static void Disconnect(){
        try {
            socket = new Socket("localhost", 8080);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeUTF(user);
            objectOutputStream.writeUTF(DISCONNECT);
            objectOutputStream.writeObject(null);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
