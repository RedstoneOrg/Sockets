package client.base;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Date;

public class ClientThread extends Thread {

    double[] dbuf = {65.56,66.89,67.98,68.82,69.55,70.37};

    @Override
    public void run() {
        try {
            Socket socket = new Socket("127.0.01",19154);


            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            while (!socket.isClosed()) {
                System.out.println("client->server: hello...");
                output.writeByte(2);

                System.out.println("client: waiting...");
                byte response = input.readByte();
                System.out.println("client: got response: " + response);
            }
            socket.close();
        }
        catch(Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
