package EX2;

import java.io.*;
import java.net.*;

public class Serveur {
    public static void main(String[] args) {
        try {
            // Cr�er un socket UDP pour �couter sur le port sp�cifi�
            DatagramSocket socket = new DatagramSocket(12000);

            // Cr�er un tableau de bytes pour stocker les donn�es de l'objet voiture
            byte[] receiveData = new byte[1024];

            // Cr�er un paquet UDP pour recevoir les donn�es
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            // Attendre la r�ception du paquet du client
            socket.receive(receivePacket);

            // Convertir les donn�es re�ues en objet voiture
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(receiveData);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            voiture receivedCar = (voiture) objectInputStream.readObject();

            // Traiter l'objet voiture re�u
            System.out.println("Voiture re�ue : " + receivedCar.getCarburant() + " litres de carburant");

            // Fermer le socket
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
