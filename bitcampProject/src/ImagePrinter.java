import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;

public class ImagePrinter{
	
	public ImagePrinter(ArrayList<SamplePoint> points){

		ArrayList<Color> pixels = new ArrayList<Color>();
		
		for(int i = 0; i < points.size()-3; i+=3)
			pixels.add(new Color(points.get(i).amplitude,
					points.get(i+1).amplitude, 
					points.get(i+2).amplitude));
		
        JFrame frame = new JFrame("Draw");
        
        int len = 1; //(int) Math.sqrt(pixels.size());
        //DrawPane panel = new DrawPane(len, len, pixels);
        DrawPane panel = new DrawPane(600, 300, pixels);
        
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	public void write(){
		BufferedImage bi;
		File f = new File("Image.png");
	}
	
	
}