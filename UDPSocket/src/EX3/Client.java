package EX3;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            // Cr�er un socket UDP
            socket = new DatagramSocket();

            // Sp�cifier l'adresse IP et le port du serveur
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 1250;

            // Cr�er un tableau de bytes pour le datagramme vide
            byte[] sendData = new byte[0];

            // Cr�er un paquet UDP avec le datagramme vide
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);

            // Envoyer le datagramme au serveur
            socket.send(sendPacket);

            // Cr�er un tableau de bytes pour stocker la r�ponse du serveur
            byte[] receiveData = new byte[1024];

            // Cr�er un paquet UDP pour recevoir la r�ponse du serveur
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            // Attendre la r�ception de la r�ponse du serveur
            socket.receive(receivePacket);

            // Convertir les donn�es de la r�ponse en cha�ne de caract�res
            String serverResponse = new String(receivePacket.getData(), 0, receivePacket.getLength());

            System.out.println("R�ponse du serveur : " + serverResponse);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
