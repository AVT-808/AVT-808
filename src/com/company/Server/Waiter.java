package com.company.Server;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Waiter {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            JFrame serverFrame = new JFrame("server");
            serverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            serverFrame.setSize(300, 200);
            serverFrame.setLocationRelativeTo(null);
            serverFrame.setResizable(false);
            serverFrame.setVisible(true);

            // Ждет клиентов и создает поток
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new Server(socket)).start();
            }
        }
    }
}
