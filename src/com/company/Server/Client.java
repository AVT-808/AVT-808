package com.company.Server;



import com.company.Habitat.Pet.Creatures.Cat;
import com.company.Habitat.Pet.Pet;
import com.company.Single.Singleton;
import sun.security.krb5.internal.PAEncTSEnc;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    private static ArrayList<String> users = new ArrayList<>();
    private static ArrayList<Pet> pets = new ArrayList<>();
    private static String user;
    private static Socket socket;
    private static int countUsers;

    private static final String SET_USER = "setUser";
    private static final String UPDATE = "update";
    private static final String DOWNLOAD = "download";
    private static final String GET_USERS_NAME = "getUserName";
    private static final String GET_USERS_COUNT = "getCount";
    private static final String SWAP = "swap";
    private static final String DISCONNECT = "disconnect";

    public Client() {
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
    public static void Update(){
        try {
            pets = Singleton.getInstance().getArray();
            socket = new Socket("localhost", 8080);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeUTF(user);
            objectOutputStream.writeUTF(UPDATE);
            objectOutputStream.writeObject(pets);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void Download(){
        user = JOptionPane.showInputDialog("Log in:");
        try {
            socket = new Socket("localhost", 8080);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeUTF(user);
            objectOutputStream.writeUTF(DOWNLOAD);
            objectOutputStream.flush();

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Singleton.getInstance().Clear();
            Singleton.getInstance().setArray((ArrayList<Pet>) objectInputStream.readObject());
            Singleton.getInstance().getWindow().quantityPet = Singleton.getInstance().sizeArray();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void Swap(){
        String myUser = user;
        user = JOptionPane.showInputDialog("Log in:");
        ArrayList<Pet> cats = new ArrayList<>();
        ArrayList<Pet> dogs = new ArrayList<>();
        for (int i = 0; i < Singleton.getInstance().sizeArray(); i++) {
            if (Singleton.getInstance().Get(i) instanceof Cat) {
                cats.add(Singleton.getInstance().Get(i));
            }else{
                dogs.add(Singleton.getInstance().Get(i));
            }
        }
        Singleton.getInstance().Clear();
        Singleton.getInstance().getWindow().quantityPet = 0;
        try {
            socket = new Socket("localhost", 8080);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeUTF(user);
            objectOutputStream.writeUTF(DOWNLOAD);
            objectOutputStream.flush();

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Singleton.getInstance().Clear();
            Singleton.getInstance().setArray((ArrayList<Pet>) objectInputStream.readObject());
            Singleton.getInstance().getWindow().quantityPet = Singleton.getInstance().sizeArray();
            objectInputStream.close();
            for (int i = 0; i < Singleton.getInstance().sizeArray(); i++) {
                if (Singleton.getInstance().Get(i) instanceof Cat) {
                    cats.add(Singleton.getInstance().Get(i));
                }else{
                    dogs.add(Singleton.getInstance().Get(i));
                }
            }
            Singleton.getInstance().Clear();
            Singleton.getInstance().setArray(cats);
            Singleton.getInstance().getWindow().quantityPet = Singleton.getInstance().sizeArray();


            socket = new Socket("localhost", 8080);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeUTF(user);
            objectOutputStream.writeUTF(SWAP);
            objectOutputStream.writeObject(dogs);
            objectOutputStream.flush();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        user = myUser;
    }
    public static String YouNow(){
        return user;
    }
    public static String NameUsers(int i){
        try {
            socket = new Socket("localhost", 8080);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeUTF(user);
            objectOutputStream.writeUTF(GET_USERS_NAME);
            objectOutputStream.flush();

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            users = (ArrayList<String>) objectInputStream.readObject();
            objectInputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users.get(i);
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