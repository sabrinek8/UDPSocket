package EX3;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            // Créer un socket UDP
            socket = new DatagramSocket();

            // Spécifier l'adresse IP et le port du serveur
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 1250;

            // Créer un tableau de bytes pour le datagramme vide
            byte[] sendData = new byte[0];

            // Créer un paquet UDP avec le datagramme vide
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);

            // Envoyer le datagramme au serveur
            socket.send(sendPacket);

            // Créer un tableau de bytes pour stocker la réponse du serveur
            byte[] receiveData = new byte[1024];

            // Créer un paquet UDP pour recevoir la réponse du serveur
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            // Attendre la réception de la réponse du serveur
            socket.receive(receivePacket);

            // Convertir les données de la réponse en chaîne de caractères
            String serverResponse = new String(receivePacket.getData(), 0, receivePacket.getLength());

            System.out.println("Réponse du serveur : " + serverResponse);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
