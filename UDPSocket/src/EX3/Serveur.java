package EX3;
import java.net.*;
import java.util.Date;

public class Serveur {
    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            // Créer un socket UDP pour écouter sur le port 1250
            socket = new DatagramSocket(1250);
            System.out.println("Serveur UDP en attente sur le port 1250...");

            while (true) {
                // Créer un tableau de bytes pour stocker les données du datagramme
                byte[] receiveData = new byte[1024];

                // Créer un paquet UDP pour recevoir les données
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                // Attendre la réception du datagramme du client
                socket.receive(receivePacket);

                // Récupérer l'adresse IP et le port du client
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                // Obtenir la date et l'heure courante
                String dateTime = new Date().toString();

                // Convertir la date et l'heure en tableau de bytes
                byte[] sendData = dateTime.getBytes();

                // Créer un paquet UDP avec les données de la date et l'heure
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);

                // Envoyer le paquet au client
                socket.send(sendPacket);

                System.out.println("Datagramme envoyé au client : " + dateTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}