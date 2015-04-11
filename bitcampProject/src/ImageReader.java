import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.sound.sampled.AudioSystem;

//reads in an image and stores a matrix (?) of the pixels 
//also converts to an audio file 
public class ImageReader {

	Color[][] imgPixels; 
	
	//reads in the file and stores 
	public ImageReader(String fileName) throws IOException {
		
		BufferedImage img= ImageIO.read(new File(fileName)); 
		int height= img.getHeight(); 
		int width= img.getWidth(); 
		imgPixels= new Color[height][width];
		
		//stores the pixels. 
		for(int r= 0; r < height; r++)
			for(int c= 0; c < width; c++)
				imgPixels[r][c]= new Color(img.getRGB(c, r)); 
	}
	
	public Color[][] getImageArray() { return imgPixels; }
}


