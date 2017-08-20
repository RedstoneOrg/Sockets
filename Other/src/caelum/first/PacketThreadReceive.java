package caelum.first;

import java.io.DataInputStream;

public class PacketThreadReceive extends Thread {

    private DataInputStream stream;

    public PacketThreadReceive(DataInputStream stream){
        this.stream = stream;
    }

    @Override
    public void run() {
        try {
            System.out.println("Chegou:  " + stream.readLong());

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
