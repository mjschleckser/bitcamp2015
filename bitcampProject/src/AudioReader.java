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
    
    public byte[] read() throws IOException{
    	/* Bytes are from -128 to 127 */
    	int maxBytes = audioInputStream.available();
    	int count = 0;
    	long mean = 0;
    	
    	byte[] bytes = new byte[maxBytes];
    	ArrayList<Long> meanVals = new ArrayList<Long>();
    	
    	audioInputStream.read(bytes, 0, maxBytes);
    	    	
    	for(int i = 0; i < bytes.length; i++){
    		String s = bytes[i] + "\t";
    		if(bytes[i] >= 0) s = " " + s;		// add a space in the front to positive nums
    		
    		System.out.print(s);
    		
    		mean += Math.abs((long)(bytes[i]));
    		
    		// calculates the mean of every 1000 bytes
    		if((i+1)%10 == 0) System.out.println();
    		if((i+1)%1000 == 0){
    			mean /= 1000;
    			meanVals.add(mean);
    			mean = 0;
    		}
    				
    		count++;
    	}
    	System.out.println();
    	System.out.println("Total number of bytes read: " + count);
    	
    	System.out.println(meanVals);
    	
    	return bytes;
    }
    
    public void close() throws IOException{
    	audioInputStream.close();
    	return;
    }
    
    public static void main(String[] args){
    	try {
			AudioReader ar = new AudioReader(new File("computer_blow.wav"));
			
			ar.read();
			
			AudioReader ab = new AudioReader(new File("sequential_spaced_beeps.wav"));
			
			ab.read();
			
			
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
    	
    	
    }
	
		
}