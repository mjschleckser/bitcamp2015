import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawPane extends JPanel {

	private BufferedImage canvas;

	public DrawPane(int width, int height, ArrayList<Color> pixels) 
	{

        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        
        fillPixels(pixels);
	}
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(canvas, null, null);
    }
    
    public Dimension getPreferredSize() 
    {
        return new Dimension(canvas.getWidth(), canvas.getHeight());
    }
	public void fillPixels(ArrayList<Color> pixels) 
	{
		int i = 0;
		for(int x = 0; x < canvas.getWidth(); x+=3)
			for(int y = 0; y < canvas.getHeight(); y+=3)
			{
				try {
				int color = pixels.get(i).getRGB();
				canvas.setRGB(x, y, color);
				canvas.setRGB(x+1, y, color);
				canvas.setRGB(x+2, y, color);
				canvas.setRGB(x, y+1, color);
				canvas.setRGB(x+1, y+1, color);
				canvas.setRGB(x+2, y+1, color);
				canvas.setRGB(x, y+2, color);
				canvas.setRGB(x+1, y+2, color);
				canvas.setRGB(x+2, y+2, color);
				i++;
				} catch (IndexOutOfBoundsException ex) {
					break;
				}
			}
		repaint();
	}
}