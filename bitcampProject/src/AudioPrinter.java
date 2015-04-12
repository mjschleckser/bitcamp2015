import java.io.File;
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.sound.sampled.*;

//creates and stores information for creating a new wav file 
public class AudioPrinter {

	private File wavFile; 

	//constructor that takes in name of the wavFile to be created
	public AudioPrinter(String fileName){

		wavFile= new File(fileName); 

		
	}


}
