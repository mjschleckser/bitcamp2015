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

    public ArrayList<Integer> read() throws IOException{
    	/* Bytes are from -128 to 127 */
    	int maxBytes = audioInputStream.available();
    	long mean = 0, count = 0;
    	byte[] bytes = new byte[maxBytes];
    	ArrayList<Integer> meanVals = new ArrayList<Integer>();
    	
    	audioInputStream.read(bytes, 0, maxBytes);
    	
    	for(int i = 0; i < bytes.length; i++)
    	{
    		mean += Math.abs(bytes[i]);
    		if((i+1)%25 == 0){
    			mean /= 25;
    			meanVals.add((int) mean);
    			mean = 0;
    		}
    				
    		count++;
    	}
    	
    	System.out.println();
    	System.out.println("Total number of bytes read: " + count);
    	System.out.println("Mean values: " + meanVals);
    	ImagePrinter ip = new ImagePrinter(meanVals);
    	
		return meanVals;
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