package EX1;

import java.net.*;
import java.util.Scanner;
public class ServeurUDP {
public static void main(String argv[]) {
int port = 0;
Scanner keyb = new Scanner(System.in);
try {
// on r�cup�re le param�tre : port d'�coute
System.out.println("port d'�coute : ");
port = keyb.nextInt();
DatagramPacket packet;
// cr�ation d'une socket li�e au port pr�cis� en param�tre
DatagramSocket socket = new DatagramSocket(port);
// tableau de 15 octets qui contiendra les donn�es re�ues
byte[] data = new byte[15];
// cr�ation d'un paquet en utilisant le tableau d'octets
packet = new DatagramPacket(data, data.length);

// attente de la r�ception d'un paquet. Le paquet re�u est plac�
// dans packet et ses donn�es dans data.

socket.receive(packet);

// r�cup�ration et affichage des donn�es (une cha�ne de caract�res)

String chaine = new String(packet.getData(), 0,
packet.getLength());
System.out.println(" recu : " + chaine);
System.out.println(" ca vient de : " + packet.getAddress() + ":" +

packet.getPort());

// on met une nouvelle donn�e dans le paquet
// (qui contient donc le couple @IP/port de la socket cot� client)
byte[] reponse = (new String("bien recu")).getBytes();
packet.setData(reponse);
packet.setLength(reponse.length);
// on envoie le paquet au client
socket.send(packet);
} catch (Exception e) {
System.err.println("Erreur : " + e);
}
}
}