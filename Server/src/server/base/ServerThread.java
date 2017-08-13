package server.base;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Date;

public class ServerThread extends Thread {

    private InetSocketAddress address;

    public ServerThread(InetSocketAddress inetSocketAddress) {
        this.address = inetSocketAddress;
        this.setName("ServerThread");
    }

    @Override
    public void run() {
        try{
            try(ServerSocket server = new ServerSocket()){
                server.bind(this.address);
                while(!server.isClosed()){
                    try {
                        Socket socket = server.accept();
                        System.out.println("Client: " + socket.getInetAddress().getHostAddress());

                        DataInputStream input = new DataInputStream(socket.getInputStream());
                        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

                        PacketThread packet = new PacketThread(socket);
                        packet.start();

                        socket.close();
                        System.out.println("Conexão encerrada");
                        System.exit(0);
                    } catch (Exception e){}
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
