package audioReader;
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
    private float sampleRate;
	
    public AudioReader(File file) throws UnsupportedAudioFileException, IOException{
        audioInputStream = AudioSystem.getAudioInputStream(file);
        format = audioInputStream.getFormat();
        sampleRate = format.getSampleRate();
    }
    
    public AudioFormat getFormat() {
        return format;
    }
    
    public float getSampleRate() {
    	return sampleRate;
    }

    public ArrayList<SamplePoint> read() throws IOException{
    	/* Bytes are from -128 to 127 */
    	int maxBytes = audioInputStream.available();
    	int bytesPerSample = 128;
    	long mean = 0, count = 0;
    	byte[] bytes = new byte[maxBytes];

    	ArrayList<SamplePoint> points = new ArrayList<SamplePoint>();
    	
    	audioInputStream.read(bytes, 0, maxBytes);

    	ArrayList<Double> fftList = new ArrayList<Double>();
    	for(int i = 0; i < bytes.length; i++)
    	{
    		fftList.add((double) bytes[i]);
    		mean += Math.abs(bytes[i]);
    		
    		if((i+1)%bytesPerSample == 0){
    			// Put into the ArrayList
    			mean /= bytesPerSample;
    			SamplePoint sp = new SamplePoint();
    			sp.amplitude = (int)( mean );
    			
    			// START OF FFT BULLSHIT CODE
    			double[] reals = new double[fftList.size()];
    			double[] imaginaries = new double[fftList.size()];
    			
    			for(int iz = 0; iz < fftList.size(); iz++){
    				reals[iz] = fftList.get(iz);
    				imaginaries[iz] = 0;
    			}
    			
    			double[] results = FFTbase.fft(reals, imaginaries, true);
    			
    			double max_magnitude = 0;
    	    	int max_index = 0;
    	    	for(int ix = 0; ix < results.length-1; ix+=2){
    	    		if(Math.sqrt(results[ix]*results[ix] + results[ix+1]*results[ix+1]) > max_magnitude){
    	    			max_index = ix;
    	    			max_magnitude = Math.sqrt(results[ix]*results[ix] + results[ix+1]*results[ix+1]);
    	    		}
    	    	}
    	    	
    	    	double frequency = max_index * this.sampleRate / reals.length;
    	    	
    	    	// Comment one of these two lines.
    			sp.wavelength = (int) (frequency * bytesPerSample);
//    	    	sp.wavelength = (int) frequency;
    			
    			// END OF FFT BULLSHIT CODE
    			
    			points.add(sp);
    			
    			// Reset values
    			mean = 0;
    			fftList.clear();
    		}
    				
    		count++;
    	}
    	
    	//System.out.println("Total number of bytes read: " + count);

    	ImagePrinter ip = new ImagePrinter(points);
    	
		return points;
    }
    
    public void close() throws IOException
    {
    	audioInputStream.close();
    }
    
}