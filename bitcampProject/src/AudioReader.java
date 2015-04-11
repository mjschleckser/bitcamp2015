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
    
    public void read() throws IOException{
    	int max_bytes = audioInputStream.available();
    	byte[] bytes = new byte[max_bytes];
    	audioInputStream.read(bytes, 0, max_bytes);
    	
    	for(int i = 0; i < bytes.length; i++){
    		System.out.println(bytes[i]);
    	}
    
    	return;
    }
    
    public void close() throws IOException{
    	audioInputStream.close();
    	return;
    }
    
    public static void main(String[] args){
    	try {
			AudioReader ar = new AudioReader(new File("computer_blow.wav"));
			
			ar.read();
			
			
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
    	
    	
    }
	
		
}