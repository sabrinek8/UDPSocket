package EX2;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            // Cr�er un objet voiture et d�finir la quantit� de carburant
            voiture myCar = new voiture("Volkswagen", "Toyota");
            myCar.setCarburant(70);

            // Cr�er un socket UDP
            DatagramSocket socket = new DatagramSocket();

            // Convertir l'objet voiture en tableau de bytes
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(myCar);
            byte[] sendData = byteArrayOutputStream.toByteArray();

            // Sp�cifier l'adresse IP et le port du serveur
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 12000;

            // Cr�er un paquet UDP avec les donn�es de l'objet voiture
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);

            // Envoyer le paquet au serveur
            socket.send(sendPacket);

            // Fermer le socket
            socket.close();

            System.out.println("Objet voiture envoy� au serveur avec succ�s.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
