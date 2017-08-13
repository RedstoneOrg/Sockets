package server.base;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class Main {


    public static void main(String[] strings){
        ServerThread thread = new ServerThread(new InetSocketAddress("127.0.0.1", 19154));
        thread.start();
        System.out.println("Iniciou: " + thread.getName());
    }
}
