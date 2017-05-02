import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by Fabien on 11/04/2017.
 */
public class Serveur {
    byte[] buffer;
    DatagramSocket socket;
    int port;
    InetAddress ipClient;
    public static int portConnexion = 1025;

    public Serveur(int port){
        this.port = port;
        socket = intiateSocket(port);

    }

    public static byte[] initiateBuffer() {
        return new byte[1024];
    }

    public DatagramSocket getSocket() {
        return socket;
    }

    public static DatagramSocket intiateSocket(int port)
    {
        DatagramSocket socket;
        try {
            socket = new DatagramSocket(port);
            return socket;
        } catch (SocketException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String messageRecu(){
        buffer = initiateBuffer();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        try {
            socket.receive(packet);
            ipClient = packet.getAddress();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Rien recu");
        }
        String string =new String(buffer);
        return string.split(Client.forSplit)[0];
    }

    public void connexion(){
        String connexion = messageRecu();
        System.out.println();
        if(connexion.equals("connexion")){
            //Créer dialog
            System.out.println("Connexion serveur");
        }
        else
            System.out.println("Erreur connexion");
    }
}
