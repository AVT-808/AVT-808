package com.company.Server;

import com.company.Habitat.Pet.Pet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;


public class Server implements Runnable {
    private Socket socket;
    private static int countUsers = 0;
    private static ArrayList<String> users = new ArrayList<>();
    private static HashMap<String,ArrayList<Pet>> pets = new HashMap<>();

    private static final String SET_USER = "setUser";
    private static final String UPDATE = "update";
    private static final String DOWNLOAD = "download";
    private static final String GET_USERS_NAME = "getUserName";
    private static final String GET_USERS_COUNT = "getCount";
    private static final String SWAP = "swap";
    private static final String DISCONNECT = "disconnect";



    public Server(Socket socket) { this.socket = socket; }

    @Override
    public void run() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            String user = objectInputStream.readUTF();
            String command = objectInputStream.readUTF();

            switch (command) {
                case GET_USERS_NAME:
                    objectOutputStream.writeObject(users);
                    break;
                case SET_USER:
                    users.add(user);
                    countUsers++;
                    break;
                case UPDATE:
                    pets.put(user, (ArrayList<Pet>) objectInputStream.readObject());
                    break;
                case SWAP:
                    ArrayList<Pet> dogs = (ArrayList<Pet>) objectInputStream.readObject();
                    pets.remove(user);
                    pets.put(user,dogs);
                    break;
                case DOWNLOAD:
                    objectOutputStream.writeObject(pets.get(user));
                    break;
                case GET_USERS_COUNT:
                    objectOutputStream.writeObject(countUsers);
                    break;
                case DISCONNECT:
                    users.remove(user);
                    pets.remove(user);
                    countUsers--;
                    break;
                default:
                    objectOutputStream.writeObject("ERROR");
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}