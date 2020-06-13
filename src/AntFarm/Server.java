package AntFarm;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;

public class Server extends Thread {
    private Socket socket;
    private static Vector<String> users = new Vector<>();
    private static Map<String, Vector<Ant>> ants = new LinkedHashMap<>();
    private static String setUser = "Добавить пользователя";
    private static String getUsers = "Получить список пользователей";
    private static String getObjects = "Передать объекты";
    private static String disconnect = "Отключиться";

    Server(Socket socket)
    {
        this.socket = socket;
    }

    @Override
    public void run()
    {
        try
        {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            String user = objectInputStream.readUTF();
            String command = objectInputStream.readUTF();
            String target = objectInputStream.readUTF();
            System.out.println(user);
            System.out.println(command);
            System.out.println(target);
            if (command.equals(setUser))
            {
                users.add(user);
            }
            else
            {
                if (command.equals(getUsers))
                {
                    objectOutputStream.writeObject(users);
                    ants.put(user, (Vector<Ant>) objectInputStream.readObject());
                }
                else
                    {
                        if (command.equals(getObjects))
                        {
                            objectOutputStream.writeObject(ants.get(target));
                        }
                        else {
                            if (command.equals(disconnect))
                            {
                                users.remove(user);
                                ants.remove(user);
                            }
                            else {
                                objectOutputStream.writeObject("Ошибка!");
                            }
                        }
                    }
            }
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}