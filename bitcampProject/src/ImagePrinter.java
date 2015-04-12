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
			float[] hsv = new float[3];
			Color.RGBtoHSB(points.get(i).wavelength * 5, 0, 0, hsv);
			hsv[1] = (float)(((points.get(i).amplitude-30) * 5) * 0.01);
			if (hsv[1] > 1){
				hsv[1] = 1;
			} else if (hsv[1] < 0){
				hsv[1] = 0;
			}
			System.out.println("Wavelength: " + points.get(i).wavelength );
			pixels.add(new Color(Color.HSBtoRGB(hsv[0], hsv[1], hsv[2])));
		}
		
		
        JFrame frame = new JFrame("Draw");
        System.out.println("Number of points: " + points.size());
        DrawPane panel = null;
        if(points.size() < 1000){
        	 panel = new DrawPane(300, 150, pixels);
        }else if( points.size() < 2000){
        	panel = new DrawPane(600, 300, pixels);
        }else if( points.size() < 3000){
        	panel = new DrawPane(800, 400, pixels);
        }else{
        	panel = new DrawPane(1000, 500, pixels);
        }
        
        
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