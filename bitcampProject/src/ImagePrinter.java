import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;

public class ImagePrinter{
	
	public ImagePrinter(ArrayList<SamplePoint> points){

		ArrayList<Color> pixels = new ArrayList<Color>();
		
		//The below for loop is currently being tested on RED only
		for(int i = 0; i < points.size(); i+=1){
			//pixels.add(new Color(bytes.get(i), bytes.get(i+1), bytes.get(i+2)));
			float[] hsv = new float[3];
			Color.RGBtoHSB(points.get(i).wavelength * 5, 0, 0, hsv);
//			System.out.println("points.get(" + i + "): " + points.get(i).wavelength);
			hsv[1] = (float)(((points.get(i).amplitude-30) * 5) * 0.01);
//			System.out.println("hsv[0] " + i + " : " + hsv[0]);
			if (hsv[1] > 1){
				hsv[1] = 1;
			} else if (hsv[1] < 0){
				hsv[1] = 0;
			}
			
			pixels.add(new Color(Color.HSBtoRGB(hsv[0], hsv[1], hsv[2])));
//			System.out.println("hsv[0] " + i + " : " + hsv[0]);
//			System.out.println("hsv[1] " + i + " : " + hsv[1]);
//			System.out.println("hsv[2] " + i + " : " + hsv[2]);
		}
		
		
        JFrame frame = new JFrame("Draw");
        
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