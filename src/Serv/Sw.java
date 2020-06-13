package Serv;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Sw {

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8080)) { // ServerSocket нужен только на этапе создания соединения
                                                                         // Создать объект типа Socket на стороне клиента и воссоздать его с помощью ServerSocket на стороне сервера – вот необходимый минимум для соединения.
            JFrame serverFrame = new JFrame("Сервер");
            serverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            serverFrame.setSize(200, 200);
            serverFrame.setLocationRelativeTo(null); // Окно появляетя в центре экрана
            serverFrame.setResizable(true); // Менять размеры окна
            serverFrame.setVisible(true);

            while (true) {
                Socket socket = serverSocket.accept(); // accept() ждёт пока кто-либо не захочет подсоединится к нему, и когда это происходит возвращает объект типа Socket, то есть воссозданный клиентский сокет.
                new Thread(new Server(socket)).start();
            }
        }
    }
}
