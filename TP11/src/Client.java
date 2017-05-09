import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * Created by Fabien on 11/04/2017.
 */
public class Client {
    private DatagramSocket socket;
    private InetAddress address;
    public static String forSplit = "µ";

    int portDestination;
    String destination;


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

    public static void envoieMessage(String message, int port, String destination, DatagramSocket socket){

        DatagramPacket packet;
        message+=forSplit;
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

    public void dialoguer()
    {
            Scanner scanner = new Scanner(System.in);
            String message = scanner.nextLine();
            envoyer(message);
    }

    public void envoyer(String string)
    {
        envoieMessage(string, portDestination, destination, this.socket);
    }


    public boolean receptionConnexion()
    {
        boolean verif;
        String co = recevoir();
        if(co.equals("ouverture_dialog"))
        {
            System.out.println("Le dialogue est ouvert");
            verif = true;
        }else{
            System.out.println("Rien recu");
            verif = false;
        }
        return true;
    }

    public void connexion(String destination)
    {
        envoieMessage("connexion",Serveur.portConnexion, destination, socket);
        System.out.println("Connexion client");
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setPortDestination(int portDestination) {
        this.portDestination = portDestination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String recevoir(){
        byte[] buffer = Serveur.initiateBuffer();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        try {
            socket.receive(packet);
            this.portDestination = packet.getPort();
            this.destination = packet.getAddress().getHostName();
            System.out.println("Dans le try");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Rien recu");
        }

        String string =new String(buffer);
        return string.split(Client.forSplit)[0];
    }
}
