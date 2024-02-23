package EX1;
import java.net.*;
import java.util.Scanner;
public class ClientUDP {
public static void main(String argv[]) {
int port = 0;
String host = "";
Scanner keyb = new Scanner(System.in);
try {
// on r�cup�re les param�tres : nom de la machine serveur et
// num�ro de port
System.out.println("Adress du serveur : ");
host = keyb.next();
System.out.println("port d'�coute du serveur : ");
port = keyb.nextInt();
InetAddress adr;
DatagramPacket packet;
DatagramSocket socket;
// adr contient l'@IP de la partie serveur
adr = InetAddress.getByName(host);
// donn�es � envoyer : cha�ne de caract�res
byte[] data = (new String("Hello Word")).getBytes();
// cr�ation du paquet avec les donn�es et en pr�cisant l'adresse
// du serveur (@IP et port sur lequel il �coute)
packet = new DatagramPacket(data, data.length, adr, port);
// cr�ation d'une socket, sans la lier � un port particulier
socket = new DatagramSocket();
// envoi du paquet via la socket
socket.send(packet);
// cr�ation d'un tableau vide pour la r�ception
byte[] reponse = new byte[15];
packet.setData(reponse);
packet.setLength(reponse.length);
// attente paquet envoy� sur la socket du client
socket.receive(packet);
// r�cup�ration et affichage de la donn�e contenue dans le paquet
String chaine = new String(packet.getData(), 0,

packet.getLength());

System.out.println(" re�u du serveur : " + chaine);
} catch (Exception e) {
System.err.println("Erreur : " + e);
}
}
}