package EX2;

import java.io.*;
import java.net.*;

public class Serveur {
    public static void main(String[] args) {
        try {
            // Créer un socket UDP pour écouter sur le port spécifié
            DatagramSocket socket = new DatagramSocket(12000);

            // Créer un tableau de bytes pour stocker les données de l'objet voiture
            byte[] receiveData = new byte[1024];

            // Créer un paquet UDP pour recevoir les données
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            // Attendre la réception du paquet du client
            socket.receive(receivePacket);

            // Convertir les données reçues en objet voiture
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(receiveData);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            voiture receivedCar = (voiture) objectInputStream.readObject();

            // Traiter l'objet voiture reçu
            System.out.println("Voiture reçue : " + receivedCar.getCarburant() + " litres de carburant");

            // Fermer le socket
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
