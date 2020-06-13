package AntFarm;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

public class Client
{
    private static String username;
    private static Socket socket;
    private static String host = "localhost";
    private static int port = 3333;
    private static String setUser = "Добавить пользователя";
    private static String getUsers = "Получить список пользователей";
    private static String getObjects = "Передать объекты";
    private static String disconnect = "Отключиться";
    private static Vector<String> users = new Vector<>();

    Client() {
        username = JOptionPane.showInputDialog("Введите Ваше имя: ");
        try
        {
            socket = new Socket(host, port);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeUTF(username);
            objectOutputStream.writeUTF(setUser);
            objectOutputStream.writeUTF("Пользователь для передачи объектов");
            objectOutputStream.writeObject(null);
            objectOutputStream.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static Vector<String> getUsers() {
        try
        {
            socket = new Socket(host, port);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream.writeUTF(username);
            objectOutputStream.writeUTF(getUsers);
            objectOutputStream.writeUTF("Пользователь для передачи объектов");
            objectOutputStream.writeObject(Singleton.getAnts());
            objectOutputStream.flush();
            users = (Vector<String>) objectInputStream.readObject();
            objectInputStream.close();
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return users;
    }

    public void getAnts(Habitat antFarm) {
        int N = 3;
        int workers = 0, warriors = 0;
        String targetUser = "";
        try
        {
            while (!users.contains(targetUser))
            {
                targetUser = JOptionPane.showInputDialog("Введите имя пользователя для получения объектов:");
            }
            socket = new Socket(host,port);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream.writeUTF(username);
            objectOutputStream.writeUTF(getObjects);
            objectOutputStream.writeUTF(targetUser);
            objectOutputStream.writeObject(Singleton.getAnts());
            System.out.println(targetUser);
            objectOutputStream.flush();
            Vector<Ant> ants = (Vector<Ant>) objectInputStream.readObject();
            System.out.println("Изначальный размер массива: " + Singleton.getArraySize());
            System.out.println("Полученный вектор: " + ants + "\n");
            if (ants.size() < N)
                N = ants.size();
            for (int i = 0; i < N;i++)
            {
                Singleton.addAnt(ants.get(i), ants.get(i).id, antFarm.getTime());
                if (ants.get(i).getClass() == AntWorker.class)
                {
                    Singleton.getAnts().get(i).setImage(ImageIO.read(getClass().getResource("/AntWarrior.png")));
                    workers++;
                }
                else
                {
                    Singleton.getAnts().get(i).setImage(ImageIO.read(getClass().getResource("/AntWorker.png")));
                    warriors++;
                }
            }
            System.out.println("Новый размер массива: " + Singleton.getArraySize());
            System.out.println("Singleton: " + Singleton.getAnts());
            antFarm.setAntsAmount(Singleton.getArraySize());
            antFarm.setWorkersAmount(antFarm.getWorkersAmount() + workers);
            antFarm.setWarriorsAmount(antFarm.getWarriorsAmount() + warriors);
            objectInputStream.close();
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try
        {
            socket = new Socket(host,port);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeUTF(username);
            objectOutputStream.writeUTF(disconnect);
            objectOutputStream.writeUTF("Пользователь для передачи объектов");
            objectOutputStream.writeObject(null);
            objectOutputStream.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}