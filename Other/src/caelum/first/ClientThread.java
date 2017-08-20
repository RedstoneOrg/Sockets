package caelum.first;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientThread extends Thread {

    public ClientThread(){
        this.setName("ClientThread");
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket("127.0.0.1",19154);


            sleep(2 * 1000);
            /*ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
            String object = (String) entrada.readObject();
            System.out.println("Client recebe: " + object);*/

            DataInputStream stream = new DataInputStream(socket.getInputStream());

            while(socket.isConnected()) {
                Object object;
                switch ((int) stream.readLong()) {
                    case 0:
                        object = stream.readByte();
                        break;
                    case 1:
                        object = stream.readInt();
                        break;
                    case 2:
                        object = stream.readUTF();
                        break;
                    default:
                        object = "Nada recebido";
                        break;
                }
                System.out.println("Recebeu: " + object);
            }

            sleep(2 * 1000);
            socket.close();
        }
        catch(Exception e) {
            try {
                sleep(1);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            System.out.println("Erro: " + e.getMessage());
        }
    }

}
