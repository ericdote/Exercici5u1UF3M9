package activitat5u1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author Eric
 */
public class UDPServer {
    //Declarem el port pel Server
    static final int PORT = 5487;
    /**
     * Clase que utiliztem per inicialitzar el Server i rebre missatges dels clients
     * Després els retornem al client.
     * @param args
     * @throws SocketException
     * @throws IOException 
     */
    public static void main(String[] args) throws SocketException, IOException {
        //Declarem el socket amb el seu port
        DatagramSocket socketServer = new DatagramSocket(PORT);
        System.out.println("Run UDP Server...");
        //Creem un byte[] per emmagatzemar la cadena que ens envia el client
        byte[] cadena = new byte[1000];
        while (true) {
            //Declarem el datagramPacket amb que rebem la cadena
            DatagramPacket dp = new DatagramPacket(cadena, cadena.length);
            //Rebem la cadena
            socketServer.receive(dp);
            //Pasem a String el byte[]
            String frase = new String(dp.getData());
            System.out.println("Rebut: " + frase);
            //Pasem a una cadena de bytes la frase que hem rebut i la pusem en mayus per distinguir de la que hem rebut
            byte[] cadena2 = frase.toUpperCase().getBytes();
            //Declarem al client la nova cadena en format byte[]
            DatagramPacket respuesta = new DatagramPacket(cadena2, cadena2.length, dp.getAddress(), dp.getPort());
            System.out.println("Enviat: " + frase.toUpperCase());
            //Enviem la cadena.
            socketServer.send(respuesta);
        }
    }
}
