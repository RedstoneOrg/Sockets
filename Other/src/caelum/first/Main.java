package caelum.first;


import java.net.InetSocketAddress;
import java.util.*;

public class Main {

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        ServerThread serverThread = new ServerThread(new InetSocketAddress("127.0.0.1", 19154));
        serverThread.start();
        System.out.println("Thread: " + serverThread.getName() + " iniciou");
        ClientThread clientThread = new ClientThread();
        clientThread.start();;
        System.out.println("Thread: " + clientThread.getName() + " iniciou");
    }



}
