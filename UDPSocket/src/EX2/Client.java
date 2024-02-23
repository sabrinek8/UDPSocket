package EX2;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            // Créer un objet voiture et définir la quantité de carburant
            voiture myCar = new voiture("Volkswagen", "Toyota");
            myCar.setCarburant(70);

            // Créer un socket UDP
            DatagramSocket socket = new DatagramSocket();

            // Convertir l'objet voiture en tableau de bytes
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(myCar);
            byte[] sendData = byteArrayOutputStream.toByteArray();

            // Spécifier l'adresse IP et le port du serveur
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 12000;

            // Créer un paquet UDP avec les données de l'objet voiture
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);

            // Envoyer le paquet au serveur
            socket.send(sendPacket);

            // Fermer le socket
            socket.close();

            System.out.println("Objet voiture envoyé au serveur avec succès.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
