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
    int port; //Choisir un port au dessus de 1024
    InetAddress ipClient;

    public Dialog(InetAddress ipClient){
        numDialog+=1;
        port = 1025+numDialog;
        socket = Serveur.intiateSocket(port);
        this.ipClient = ipClient;
    }

}
