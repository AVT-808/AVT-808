package term4;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

public class Client {
    private static ArrayList<String> users = new ArrayList<>();
    private static String username;
    private static Socket socket;

    private static final String SET_USER = "setUser";
    private static final String GET_USERS = "getUsers";
    private static final String GET_OBJECTS = "getObjects";
    private static final String DISCONNECT = "disconnect";

    public Client() {
         username = JOptionPane.showInputDialog("Hello! Please enter your name:");
        try {
            socket = new Socket("localhost", 6141);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeUTF(username);
            objectOutputStream.writeUTF(SET_USER);
            objectOutputStream.writeUTF("targetUser");
            objectOutputStream.writeObject(null);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getUsers() {
        try{
            socket = new Socket("localhost", 6141);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeUTF(username);
            objectOutputStream.writeUTF(GET_USERS);
            objectOutputStream.writeUTF("targetUser");
            objectOutputStream.writeObject(Singleton.getSingleton().getHouseVector());
            objectOutputStream.flush();

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            users = (ArrayList<String>) objectInputStream.readObject();
            objectInputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return users;
    }

    public static void getHouses(){
        String targetUser = "";
        String s;
        try{
            while (!users.contains(targetUser)){
                targetUser = JOptionPane.showInputDialog("Please enter target name:");
            }
            socket = new Socket("localhost", 6141);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeUTF(username);
            objectOutputStream.writeUTF(GET_OBJECTS);
            objectOutputStream.writeUTF(targetUser);
            objectOutputStream.writeObject(Singleton.getSingleton().getHouseVector());
            System.out.println(targetUser);
            objectOutputStream.flush();

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Singleton.getSingleton().clearArea();
            Singleton.getSingleton().setHouseVector((Vector<House>) objectInputStream.readObject());
            MyComponent.setCount(Singleton.getSingleton().getHouseVector().size());
            for(int i = 0; i < Singleton.getSingleton().getHouseVector().size(); i++){
                Singleton.getSingleton().getHouse(i).setTimeOfBirth(MyComponent.getTime());
            }
            objectInputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect(){
        try {
            socket = new Socket("localhost", 6141);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeUTF(username);
            objectOutputStream.writeUTF(DISCONNECT);
            objectOutputStream.writeUTF("targetUser");
            objectOutputStream.writeObject(null);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
