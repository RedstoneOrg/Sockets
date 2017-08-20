package caelum.first;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class PacketThreadSend extends Thread {

    private DataOutputStream stream;
    private Object object;
    private int objectype;

    public PacketThreadSend(DataOutputStream stream, byte object){
        this.stream = stream;
        this.object = object;
        this.objectype = 0;
    }

    public PacketThreadSend(DataOutputStream stream, int object){
        this.stream = stream;
        this.object = object;
        this.objectype = 1;
    }

    public PacketThreadSend(DataOutputStream stream, String object){
        this.stream = stream;
        this.object = object;
        this.objectype = 2;
    }

    @Override
    public void run() {
        this.setName("PacketThread #0");
        try {
            stream.flush();
            System.out.println("ServerThread enviou: " + object);

            stream.writeLong(objectype);

            switch(objectype){
                case 0:
                    stream.writeByte((byte) object);
                    break;
                case 1:
                    stream.writeInt((int) object);
                    break;
                case 2:
                    stream.writeUTF((String) object);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
