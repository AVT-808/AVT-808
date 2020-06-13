package Serv;


import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;


public class Client  {

    private static ArrayList<String> users = new ArrayList<>();
    private static String user;
    private static Socket socket;
    private static int countUsers;

    private static final String SET_USER = "setUser";
    private static final String GET_USERS_COUNT = "getCount";
    private static final String DISCONNECT = "disconnect";
    private static final String GET_USER_NAME = "getUserName";
    private static final String SEND_OBJ = "sendObj";
    private static final String SEND_US = "sendUs";
    private static final String SEND_US_NULL = "sendUsNULL";
    private static final String GET_US = "getUs";
    private static final String GET_N = "getN";


    public static void Connection() { // Вызывается в AnimalTour

        user = JOptionPane.showInputDialog("Введите ваш логин:"); // Строка с логином
        try {
            socket = new Socket("localhost", 8080); // Через его каналы ввода/вывода будут общаться клиент с сервером
                                                               // Объявляется этот класс на стороне клиента, а сервер воссоздаёт его, получая сигнал на подключение
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream()); // ObjectOutputStream - преобразует объекты в байты
                                                                                                      // getOutputStream() - доступ к потоку OutputStream объекта типа Socket
            objectOutputStream.writeUTF(user); // writeUTF(String str): записывает в поток строку в кодировке UTF-8
            objectOutputStream.writeUTF(SET_USER); // Считается в Server.run()
            objectOutputStream.writeObject(null);
            objectOutputStream.flush(); // void flush() - финализирует выходное состояние, очищая все буферы вывода
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int CountUsers(){  // Вызывается в DepictBird (когда оно там всё перечисляется)
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

    public static String NameUsers(int i){ // Вызывается в DepictBird (когда оно там всё перечисляется)
        try {
            socket = new Socket("localhost", 8080);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeUTF(user);
            objectOutputStream.writeUTF(GET_USER_NAME); // В ответ сервер напишет массив с именами
            objectOutputStream.flush();

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            users = (ArrayList<String>)objectInputStream.readObject(); // Считаем этт массив
            objectInputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users.get(i);
    }

    public static void Disconnect(){ // Вызывается в Habitat, если закрыто окно хэбитат
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

    public static void sendObj(){

        String sendUser  = JOptionPane.showInputDialog("Передаваемое число:");
               try {
            socket = new Socket("localhost", 8080);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeUTF(user);
            objectOutputStream.writeUTF(SEND_OBJ);
            objectOutputStream.writeUTF(sendUser);
            objectOutputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendUs(){

        String sendUser  = JOptionPane.showInputDialog("Получатель:");
        try {
            socket = new Socket("localhost", 8080);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeUTF(user);
            objectOutputStream.writeUTF(SEND_US);
            objectOutputStream.writeUTF(sendUser);
            objectOutputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendUsNull(){

        try {
            socket = new Socket("localhost", 8080);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeUTF(user);
            objectOutputStream.writeUTF(SEND_US_NULL);
            objectOutputStream.writeUTF(" ");
            objectOutputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String Download(){
        String sendUser = "";
        try {
            socket = new Socket("localhost", 8080);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeUTF(user);
            objectOutputStream.writeUTF(GET_US);
            objectOutputStream.flush();
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        sendUser = (String) objectInputStream.readObject();
       // System.out.println("Надо передать: "+sendUser);
        objectInputStream.close();
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    }
        return sendUser;
    }

    public static int DownN(){
        int n=0;
        try {
            socket = new Socket("localhost", 8080);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeUTF(user);
            objectOutputStream.writeUTF(GET_N);
            objectOutputStream.flush();
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            n = (Integer) objectInputStream.readObject();
            // System.out.println("Надо передать: "+sendUser);
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return n;
    }


    public static String getUser() {
        return user;
    }
}
