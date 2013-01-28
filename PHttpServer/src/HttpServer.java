import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class HttpServer 
{
	private static final String newLine = "\r\n";
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		//final String newLine = "\r\n";
		final int port = 8080;
		

		ServerSocket serverSocket = new ServerSocket (port);
		
		while (true) {
			
			Socket socket = serverSocket.accept();
			
			String fileName = getFileName(socket.getInputStream());
			writeHeader(socket.getOutputStream(), fileName);
			writeFile(socket.getOutputStream(), fileName);
			socket.close();
			
		}
		//serverSocket.close();
	}
	
		private static String getFileName(InputStream inputStream){
			Scanner scanner = new Scanner (inputStream);
			
			String fileName = "index.html";
			//String fileName = " ";
			
			
			//Linea GET
			while(true){
				String line = scanner.nextLine();
				System.out.println(line);
				if (line.contains("GET")){ //GET /index.html HTTP 1/1
					fileName=line.split(" ")[1].substring(1); //Se obtiene index.html
					
					
					
					//String
					//int index = 5;
					//while(line.charAt[index] != ' ')
					 	//fileName += line.charAt(index++);
					
					
					
					//Pattern pattern = Pattern.compile("GET /(?<filename>.*) HTTP/1.[01"];
					// Matcher marcher = pattern.matcher(line);
					// filename = matcher.group(1); //from 1.7 -> marcher. group("filename");
					 
					fileName = line.substring(5, line.indexOf(" ", 5));
					 
					
					System.out.println("fileName=" + fileName);
				}
				if (line.equals("")){
					break;
				}
					
			}
			return fileName;		
		}
		
		private static void writeHeader(OutputStream outputStream, String fileName) throws IOException{
			final String response200 = "HTTP/1.0 200 OK";
			final String response404 = "HTTP/1.0 404 Not Found";
			File file = new File (fileName);
			String response = file.exists() ? response200 : response404;
			String header = response + newLine + newLine;
			byte[] headerBuffer = header.getBytes();
			
			outputStream.write(headerBuffer);
		}
		
		private static void writeFile(OutputStream outputStream, String fileName) throws IOException, InterruptedException{
			final String fileNameError404 = "fileError404.html";
			
			File file = new File(fileName);
			String responseFileName = file.exists() ? fileName : fileNameError404;
			
			final int bufferSize = 2048;
			byte[] buffer = new byte[bufferSize];
			
			FileInputStream fileInputStream = new FileInputStream(responseFileName);
			
			int count;
			
			while ((count = fileInputStream.read(buffer)) != -1){
				//Retardo
				Thread.sleep (3000); 
				outputStream.write(buffer, 0, count);
			}
			
			fileInputStream.close();
		}
}