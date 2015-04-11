import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class ImagePrinter{
	
	public ImagePrinter(byte[] bytes){
		int[] colors = new int[bytes.length];
		for(int i = 0; i < bytes.length-3; i++)
			if(bytes[i] <= 0)
				colors[i] = Math.abs(new Integer(bytes[i]));
			else
				colors[i] = new Integer(bytes[i]);
		ArrayList<Color> pixels = new ArrayList<Color>();
		for(int i = 0; i < bytes.length-3; i+=3)
			pixels.add(new Color(colors[i], colors[i+1], colors[i+2]));
	}
	
	public void write(){
		BufferedImage bi;
		File f = new File("Image.png");
	}
	
	
}