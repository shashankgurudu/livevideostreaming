import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.opencv_highgui;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameGrabber;

public class webcam{
	static IplImage bimg;
	public static void main(String ar[])throws Exception{
        CanvasFrame canvas = new CanvasFrame("sending");
       
        FrameGrabber grabber = new OpenCVFrameGrabber("");
		try{
				int i=1;
				grabber.start();
				IplImage img;
				while (true) {
					img = grabber.grab();
					if (img != null) {
						opencv_highgui.cvSaveImage("a"+i%200+".jpg", img); 
					    bimg = opencv_highgui.cvLoadImage("a"+i%200+".jpg");
					    canvas.showImage(bimg);
					i++;
					}
				}
		}
		catch (Exception e) {
		}
    }
}
