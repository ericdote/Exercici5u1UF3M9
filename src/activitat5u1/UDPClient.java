package activitat5u1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

/**
 *
 * @author Eric
 */
public class UDPClient {
    //Declarem el host i el port
    static final String HOST = "localhost";
    static final int PORT = 5487;
    /**
     * Metode main que estableix una conexió UDP, rep un missatge i l'envia al Server
     * Després rep un missatge del Server.
     * @param args
     * @throws SocketException
     * @throws UnknownHostException
     * @throws IOException 
     */
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        //Declarem el Socket per UDP
        DatagramSocket clientSocket = new DatagramSocket();
        System.out.println("UDP Client: ");
        //Demanem el missatge al usuari
        String missatge = JOptionPane.showInputDialog(null, "Escribiu un missatge: ", "Entrando", 3);
        //Declarem una cadena de bytes
        byte[] cadena = new byte[1000];
        //Passem el missatge al array de Bytes.
        cadena = missatge.getBytes();
        //Declarem el host
        InetAddress host = InetAddress.getByName(HOST);       
        //Construim el datagramPacket per enviar el missatge al server
        DatagramPacket dp = new DatagramPacket(cadena, missatge.length(), host, PORT);
        //Enviem al server el missatge
        clientSocket.send(dp);        
        System.out.println("Enviat: " + new String(dp.getData()));
        //Creem un nou byte[] per rebre la resposta del server
        byte[] resposta = new byte[1000];
        //Declarem un nou datagramPacket per rebre la resposta
        DatagramPacket resp = new DatagramPacket(resposta, resposta.length);
        //Rebem la resposta del server
        clientSocket.receive(resp);        
        System.out.println("Rebut: " + new String(resp.getData()));
        //Tanquem el socket UDP
        clientSocket.close();
        
    }

}
