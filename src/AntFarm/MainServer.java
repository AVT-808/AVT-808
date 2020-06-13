package AntFarm;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer
{
    public static void main(String[] args)
    {
        try
        {
            System.out.println("Сервер запущен");
            int port  = 3333;
            ServerSocket ss = new ServerSocket(port);
            while (true)
            {
                Socket s = ss.accept();
                Server p = new Server(s);
                p.start();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
