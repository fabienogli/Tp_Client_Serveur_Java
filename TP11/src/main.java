import open.Browser;

import java.io.IOException;
import java.net.*;
import java.nio.Buffer;
import java.util.Enumeration;
import java.util.Scanner;

/**
 * Created by Fabien on 11/04/2017.
 */
public class main {

    public static DatagramSocket connexion(){
        DatagramSocket datagramSocket;
        try{
            datagramSocket = new DatagramSocket(); ///Sert a envoyer un datagramme
            return datagramSocket;
        }catch (SocketException ex){
            System.err.println("PortOccupé");
            return null;
        }
    }

    public static DatagramPacket remplirPacket(String message, String IP,int port){
        InetAddress destination;
        DatagramPacket datagramPacket;
        try{
            byte[] data =  message.getBytes();
            destination = InetAddress.getByName(IP);
            datagramPacket = new DatagramPacket(data, data.length, destination, port);
            return datagramPacket;
        }catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String args[]) throws IOException {
        Serveur serveur= new Serveur(Serveur.portConnexion);
        Client client = new Client("127.0.0.1");
        client.connexion("127.0.0.1");
        serveur.connexion();
        int continuer = 0;
//        while(true)
//        {
//            System.out.println("Taper le message à envoyer");
//            Scanner smessage = new Scanner(System.in);
//            String message = smessage.nextLine();
//            serveur.recevoir();
//        }

    }
}
