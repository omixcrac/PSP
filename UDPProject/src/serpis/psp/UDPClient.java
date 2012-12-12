package serpis.psp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

import javax.swing.text.html.parser.Parser;


public class UDPClient {


	public static void main(String[] args) throws IOException {
		
		Scanner teclado = new Scanner(System.in);
		System.out.println("Introduce texto a mandar:");
		String text = teclado.next();
		byte[] buf = text.getBytes();
		InetAddress inetAddress = InetAddress.getByName("192.168.35.111");
		int puerto = 10001;
		
		
		
		DatagramPacket packet = new DatagramPacket(buf, buf.length, inetAddress, puerto);
		
	    DatagramSocket socket = new DatagramSocket();
	    socket.send(packet);
	    socket.close();
	    
	    System.out.println("FIN DE LA COMUNICACIÓN UDP");
		
	}

}