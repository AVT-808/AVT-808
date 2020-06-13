package Serv;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable{
    private Socket socket;
    private static int countUsers = 0;
    private static final String SET_USER = "setUser";
    private static final String GET_USERS_COUNT = "getCount";
    private static final String DISCONNECT = "disconnect";
    private static final String GET_USER_NAME = "getUserName";
    private static final String SEND_OBJ = "sendObj";
    private static final String SEND_US = "sendUs";
    private static final String SEND_US_NULL = "sendUsNULL";
    private static final String GET_US = "getUs";
    private static final String GET_N = "getN";
    private static ArrayList<String> users = new ArrayList<>();
    private static String sendUser = " ";
    public static String num;
    public static int n=0;

    public Server(Socket socket) { // Вызывается в классе Sw каждый раз, когда воссоздается клиентский сокет

        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream()); //  // getOutputStream() - доступ к потоку OutputStream объекта типа Socket
            String user = objectInputStream.readUTF();
            String command = objectInputStream.readUTF();

            switch (command) {

                case SET_USER: // Устанавливается в Client.Connection(), когда появляется новый клиент
                    users.add(user); // ArrayList<String> users
                    countUsers++;
                    System.out.println("Подключен:"+ user);
                    break;


                case GET_USERS_COUNT: // Вызывается в Client для DepictBird (чтоб их все вывести)
                    objectOutputStream.writeObject(countUsers);
                    break;

                case DISCONNECT: // Вызывается в Client. Если закрыли хэбитат, минус клиент
                    users.remove(user);
                    countUsers--;
                    System.out.println("Отключен:"+ user);
                    break;

                case GET_USER_NAME:  // Вызывается в Client для DepictBird (чтоб их все вывести)
                    objectOutputStream.writeObject(users);
                    break;


                case SEND_OBJ:
                    num = objectInputStream.readUTF();
                    int o =  Integer.parseInt(num);
                    System.out.println("Число: "+o);
                    n=o;
                    break;


                case SEND_US:
                    sendUser = objectInputStream.readUTF();
                    System.out.println("Получатель: "+ sendUser);
                    break;

                case SEND_US_NULL:
                    sendUser = objectInputStream.readUTF();
                    System.out.println("Получателей больше нет");
                    break;

                case GET_US:
                    objectOutputStream.writeObject(sendUser);
                    break;

                case GET_N:
                    objectOutputStream.writeObject(n);
                    break;

                default:
                    objectOutputStream.writeObject("ERROR");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


