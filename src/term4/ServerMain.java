package term4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        try(ServerSocket serverSocket = new ServerSocket(6141)){
            while (true){
                Socket socket = serverSocket.accept();
                new Thread(new Server(socket)).start();
            }
        }
    }
}
