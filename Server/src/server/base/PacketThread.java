package server.base;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class PacketThread extends Thread {

    private Socket socket;

    public int result;

    public PacketThread(Socket sock){
        socket = sock;
    }

    @Override
    public void run() {
        this.setName("PacketThread");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DataInputStream clientinp;
        DataOutputStream clientout;

        try {
            clientinp = new DataInputStream(socket.getInputStream());
            clientout = new DataOutputStream(socket.getOutputStream());

            while (true) {
                System.out.println("reading...");
                byte sentence = clientinp.readByte();
                System.out.println("read: " + sentence);
                clientout.writeByte(2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int getResult(){
        return this.result;
    }


}
