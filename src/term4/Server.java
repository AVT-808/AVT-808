package term4;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;

public class Server implements Runnable {
    private Socket socket;
    private static ArrayList<String> users = new ArrayList<>();
    private static Map<String, Vector<House>> houses = new LinkedHashMap<>();

    private static final String SET_USER = "setUser";
    private static final String GET_USERS = "getUsers";
    private static final String GET_OBJECTS = "getObjects";
    private static final String DISCONNECT = "disconnect";

    public Server(Socket socket) { this.socket = socket; }

    @Override
    public void run() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            String user = objectInputStream.readUTF();
            String command = objectInputStream.readUTF();
            String target = objectInputStream.readUTF();
            System.out.println(user);
            System.out.println(command);
            System.out.println(target);

            switch (command) {
                case SET_USER -> users.add(user);
                case GET_USERS -> {
                    objectOutputStream.writeObject(users);
                    houses.put(user, (Vector<House>) objectInputStream.readObject());
                }
                case GET_OBJECTS -> objectOutputStream.writeObject(houses.get(target));
                case DISCONNECT -> {
                    users.remove(user);
                    houses.remove(user);
                }
                default -> objectOutputStream.writeObject("ERROR");
            }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
    }
}
