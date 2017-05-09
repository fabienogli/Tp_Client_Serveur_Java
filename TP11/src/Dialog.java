import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by Fabien on 12/04/2017.
 */
public class Dialog {
    byte[] buffer;
    DatagramSocket socket;
    static int numDialog;
    Serveur serveur;
    int port; //Choisir un port au dessus de 1024
    int portClient;
    InetAddress ipClient;
    InetAddress ipServeur;
    Client client;

    public Dialog(InetAddress ipClient,  int portClient,  Client client, Serveur serveur){
        numDialog+=1;
        port = 1025+numDialog;
        socket = Serveur.intiateSocket(port);
        this.ipClient = ipClient;
        System.out.println(ipClient.toString());
        this.client = client;
        Client.envoieMessage("ouverture_dialog", portClient, ipClient.getHostName(), socket);
        System.out.println("Envoie du msg au client");
        if(client.receptionConnexion())
            startDialog();
    }

    public void startDialog()
    {
        while (true)
        {
            client.dialoguer();
            afficherReception();
        }
    }

    public String recevoir(){
        buffer = Serveur.initiateBuffer();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        try {
            socket.receive(packet);
            } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Rien recu");
        }
        String string =new String(buffer);
        return string.split(Client.forSplit)[0];
    }

    public void afficherReception(){

            String recu = recevoir();
            System.out.println(recu);
            }

    public void initBuffer()
    {
        buffer = new byte[1024];
    }

}
