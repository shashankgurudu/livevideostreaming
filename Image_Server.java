import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
 
public class Image_Server {
 
	private static final int port = 31298;	
 	
	public static void main(String []args) throws IOException {
 
		ServerSocket server = null;
 
		try {
			server = new ServerSocket(port);
			System.out.println("Server socket ready on port: " + port);
		} catch (IOException e) {
			System.err.println("Could not listen on port: " + port);
			System.exit(-1);
		}
 
		Socket socket = server.accept();
 
		int i=1;
		while (true) {
			File fi = new File("a"+i%200+".jpg");
			
			byte[] byteImage = Files.readAllBytes(fi.toPath());
 
			System.out.println(byteImage.toString());
 
			OutputStream os = socket.getOutputStream();
 
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(byteImage);
			try {
				Thread.sleep(120);
			} catch (InterruptedException e1) {
			}
			i++;
		}
	}
}
