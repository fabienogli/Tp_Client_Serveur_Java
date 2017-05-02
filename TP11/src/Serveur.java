import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by Fabien on 11/04/2017.
 */
public class Serveur {
    byte[] buffer;
    DatagramSocket socket;
    int port;

    public Serveur(int port){
        this.port = port;
        try {
            socket = new DatagramSocket(port);
//            System.out.println("Serveur connecter au port");
        } catch (SocketException e) {
            e.printStackTrace();
//            System.out.println("le port est Occupé");
        }

    }

    private void initiateBuffer() {
        buffer = new byte[1024];
    }

    public DatagramSocket getSocket() {
        return socket;
    }


    public void recevoir(){
        initiateBuffer();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        try {
            socket.receive(packet);
            System.out.println(packet.getAddress().toString());
        } catch (IOException e) {
            e.printStackTrace();
//            System.out.println("Rien recu");
        }
        String string = new String(buffer);
        System.out.println(string);
    }
}
