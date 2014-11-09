
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;

import org.bytedeco.javacv.CanvasFrame;
 
public class Client_Camera_Image {

	private static Socket socket;
	private static String host = "localhost";
 
	public static void main(String[] args) {
				Client_Camera_Image inst = new Client_Camera_Image();			
	}
 
	public Client_Camera_Image() {
		Thread cgiT = new Thread(new GetCameraImage(), "Camera 3");
		cgiT.start();
	}
 
	
 
	protected class GetCameraImage implements Runnable {
 
		@Override
		public void run() {
			try {
				socket = new Socket(host, 31298);
				System.out.println("Connection to host established.");
			} catch (UnknownHostException e) {
				System.err.println("Don't know about host: " + host);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			CanvasFrame canvas = new CanvasFrame("Receiving");
			try {
				int i=1;
				int count = 1;
				while (count != 0) {
					ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
					byte[] byteImage = (byte[])ois.readObject();
					
					InputStream in = new ByteArrayInputStream(byteImage);
					 BufferedImage b = ImageIO.read(in);
					
					 canvas.showImage(b);
					 i++;

				} count++;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}	
		}
 
	}
 
 
}
