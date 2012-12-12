package serpis.psp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class UDPServer {
	
	
	public static void main(String[] args) throws IOException{
		
		DatagramSocket serverSocket = new DatagramSocket(10001);
	    String texto;
	    
		byte[] recibirDatos = new byte[2048];
		
		while(true){
			DatagramPacket serverPacket = new DatagramPacket(recibirDatos, recibirDatos.length);
	    	serverSocket.receive(serverPacket);
	    	serverSocket.setReuseAddress(true);
	    	
	    	texto = new String(serverPacket.getData());
	    	
	    	System.out.println("El mensaje recibido es:");
	    	System.out.println(texto);
	    	
		}
		
		
		
	}

}