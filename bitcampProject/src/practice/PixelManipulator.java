package practice;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.color.*;
import java.awt.image.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PixelManipulator extends JPanel {
	 
	public PixelManipulator(byte[] bytes){
		
	}
	
	@Override
	public void paint(Graphics g) {
//		Graphics2D g2 = (Graphics2D)g;
//		g2.setColor(Color.blue);
//		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
// 
//		g2.setColor(Color.lightGray);
//		g2.fillRect(10, 10, this.getWidth() - 20, this.getHeight() - 20);
// 
//		g2.setColor(Color.red);
// 
//		g2.drawString("Hello", 30, this.getHeight() / 2);

//		BufferedImage image = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D g2 = (Graphics2D)g;
		Color indigo = new Color(75, 0, 130);
		Color violet = new Color(159, 0, 255);
		
		g2.setColor(Color.black);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g2.setColor(Color.red);
		for (int x = 0; x < 1000; x += 7){
			for (int y = 0; y < 1000; y += 1){
				g2.fillRect(x, y, 1, 1);
			}
		}
		g2.setColor(Color.orange);
		for (int x = 1; x < 1000; x += 7){
			for (int y = 0; y < 1000; y += 1){
				g2.fillRect(x, y, 1, 1);
			}
		}
		g2.setColor(Color.yellow);
		for (int x = 2; x < 1000; x += 7){
			for (int y = 0; y < 1000; y += 1){
				g2.fillRect(x, y, 1, 1);
			}
		}
		g2.setColor(Color.green);
		for (int x = 3; x < 1000; x += 7){
			for (int y = 0; y < 1000; y += 1){
				g2.fillRect(x, y, 1, 1);
			}
		}
		g2.setColor(Color.blue);
		for (int x = 4; x < 1000; x += 7){
			for (int y = 0; y < 1000; y += 1){
				g2.fillRect(x, y, 1, 1);
			}
		}
		g2.setColor(indigo);
		for (int x = 5; x < 1000; x += 7){
			for (int y = 0; y < 1000; y += 1){
				g2.fillRect(x, y, 1, 1);
			}
		}
		g2.setColor(violet);
		for (int x = 6; x < 1000; x += 7){
			for (int y = 0; y < 1000; y += 1){
				g2.fillRect(x, y, 1, 1);
			}
		}
		
	}
 
//	public static void main(String [] args) {
//		JFrame frame = new JFrame("Pixel Manipulator");
//		frame.setSize(1000, 1000);
// 
//		PixelManipulator p = new PixelManipulator(null);
//		frame.add(p);
// 
//		frame.setVisible(true);
//	}
 
}
