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
    	
    	int max_bytes = audioInputStream.available();
    	byte[] bytes = new byte[max_bytes];
    	audioInputStream.read(bytes, 0, max_bytes);
    	
    	int count = 0;
    	for(int i = 0; i < bytes.length; i++){
    		// bytes[i] = (byte) (bytes[i] + 128);
    		String s = bytes[i] + "\t";
    		if(bytes[i] >= 0) s = " " + s;		// add a space in the front to positive nums
    		
    		System.out.print(s);
    		
    		if((i+1)%10 == 0) System.out.println();
    		count++;
    	}
    	System.out.println();
    	System.out.println("Total number of bytes read: " + count);
    	
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