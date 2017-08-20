package caelum.first;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

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

                        /*ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());

                        saida.flush();
                        String object = "Hello Amigo!";
                        saida.writeObject(object);
                        System.out.println("Servidor envia: " + object);*/

                        Scanner scan = new Scanner(System.in);

                            System.out.print("Digite algo: ");
                            String c = scan.nextLine();
                            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                            PacketThreadSend[] send = new PacketThreadSend[]{
                                    new PacketThreadSend(out, c),
                                    new PacketThreadSend(out, 1),
                            };

                            for (int i = 0; i < send.length; i++) {
                                send[i].start();
                                System.out.println("Thread: {" + send[1].getName() + "}:");
                                sleep(1);
                            }


                            sleep(2 * 1000);

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
