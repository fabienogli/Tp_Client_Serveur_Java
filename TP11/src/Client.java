import java.io.*;
import java.net.*;

/**
 * Created by Fabien on 11/04/2017.
 */
public class Client {
    private DatagramSocket socket;
    private InetAddress address;

    public Client(String ipAdress) {
        try {
            address = InetAddress.getByName(ipAdress);
        } catch (UnknownHostException e) {
            System.out.println("Adresse pas valide");
        }
        try {
            socket = new DatagramSocket();
            System.out.println("Port ouvert");
        } catch (SocketException ex) {
            System.out.println("Port fermer");
        }
    }

    public void envoieMessage(String message, int port, String destination) {

        DatagramPacket packet;
        byte[] data = message.getBytes();
        try {
            InetAddress address = InetAddress.getByName(destination);
            packet = new DatagramPacket(data, data.length, address, port);
            socket.send(packet);
//            System.out.println("Message envoyer");
        } catch (IOException e) {
            System.out.println("PROBLEME ENVOIE");
            e.printStackTrace();
        }
    }

}
