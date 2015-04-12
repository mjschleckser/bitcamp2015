import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

// some of this is taken from:
// http://web.archive.org/web/20120531113946/http://www.builogic.com/java/javasound-read-write.html

public class AudioReader {
	
	private AudioInputStream audioInputStream;
    private AudioFormat format;
	
    public AudioReader(File file) throws UnsupportedAudioFileException, IOException{
        audioInputStream = AudioSystem.getAudioInputStream(file);
        format = audioInputStream.getFormat();
    }
    
    public AudioFormat getFormat() {
        return format;
    }

    public ArrayList<SamplePoint> read() throws IOException{
    	/* Bytes are from -128 to 127 */
    	int maxBytes = audioInputStream.available();
    	long mean = 0, count = 0;
    	byte[] bytes = new byte[maxBytes];

    	ArrayList<SamplePoint> points = new ArrayList<SamplePoint>();
    	
    	audioInputStream.read(bytes, 0, maxBytes);

    	ArrayList<Double> fftList = new ArrayList<Double>();
    	for(int i = 0; i < bytes.length; i++)
    	{
    		fftList.add((double) bytes[i]);
    		    		
    		mean += Math.abs(bytes[i]);
    		if((i+1)%50 == 0){
    			// Put into the ArrayList
    			mean /= 50;
    			SamplePoint sp = new SamplePoint();
    			sp.amplitude = (int)( mean );    			
    			sp.wavelength = -1;
    			
    			points.add(sp);
    			
    			// Reset values
    			mean = 0;
    			fftList.clear();
    		}
    				
    		count++;
    	}
    	
    	System.out.println();
    	System.out.println("Total number of bytes read: " + count);

    	ImagePrinter ip = new ImagePrinter(points);
    	
		return points;
    }
    
    public void close() throws IOException
    {
    	audioInputStream.close();
    }
    
    public static void main(String[] args)
    {
    	try 
    	{
			AudioReader ar = new AudioReader(new File("sequential_spaced_beeps.wav"));
			ar.read();	
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
    }
}