package com.company.Server;

import com.company.Models.Abstract.BaseBee;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Server implements Runnable{
    private Socket socket;
    private static int countUsers = 0;
    private static final String SET_USER = "setUser";
    private static final String DOWNLOAD = "download";
    private static final String GET_USERS_COUNT = "getCount";
    private static final String DISCONNECT = "disconnect";
    private static final String GET_USER_NAME = "getUserName";
    private static final String SEND_OBJ = "sendObj";
    private static final String SEND_USER = "sendUser";


    private static ArrayList<String> users = new ArrayList<>();
    private static ArrayList<BaseBee> bees = new ArrayList<>();
    private static String sendUser = " ";


    public Server(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            String user = objectInputStream.readUTF();
            String command = objectInputStream.readUTF();

            switch (command) {

                case SET_USER:
                    users.add(user);
                    countUsers++;
                    System.out.println("Подключен:"+ user);
                    break;

                case SEND_OBJ:
                    try {
                        bees = (ArrayList<BaseBee>) objectInputStream.readObject();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    sendUser = objectInputStream.readUTF();
                    System.out.println(user+" send to "+sendUser);
                    System.out.println("bees: "+bees);
                    break;
                case DOWNLOAD:
                    objectOutputStream.writeObject(bees);
                    sendUser = null;
                    break;
                case SEND_USER:
                    objectOutputStream.writeObject(sendUser);

                    break;
                case GET_USERS_COUNT:
                    objectOutputStream.writeObject(countUsers);
                    break;
                case DISCONNECT:
                    users.remove(user);

                    countUsers--;
                    System.out.println("Отключен:"+user);
                    break;
                case GET_USER_NAME:
                    objectOutputStream.writeObject(users);
                    break;
                default:
                    objectOutputStream.writeObject("ERROR");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
