package com.company.Server;
import com.company.DataBase;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class MyServer {

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            JFrame serverFrame = new JFrame("server");
            serverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            serverFrame.setSize(300, 200);
            serverFrame.setLocationRelativeTo(null);
            serverFrame.setResizable(false);
            serverFrame.setVisible(true);

            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new Server(socket)).start();
            }
        }
    }

}
