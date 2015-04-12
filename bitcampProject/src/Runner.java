import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import audioReader.AudioReader;

public class Runner {

	public static void main(String[] args) {

		AudioReader ar;

		String welcomeText = "<html><center>Welcome to AudioReader!<br>"
				+ "<br>"
				+ "Our program takes a .WAV file, and visualizes it in a single image, "
				+ "using the frequency and amplitude of the original .WAV.<br>"
				+ "<br>"
				+ "Press \"OK\" to choose a .WAV file to visualize.</center></html>";

		JOptionPane optionPane = new JOptionPane(new JLabel(welcomeText,
				JLabel.CENTER));
		JDialog dialog = optionPane.createDialog("AudioReader");
		dialog.setModal(true);
		dialog.setVisible(true);

		JFileChooser chooser = new JFileChooser("C:\\Users\\Matt\\Desktop\\Matt Files\\Music\\WAV files");
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				".WAV Sound Files", "wav");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				ar = new AudioReader(chooser.getSelectedFile());
				ar.read();
				ar.close();
			} catch (UnsupportedAudioFileException | IOException e) {
				e.printStackTrace();
			}
		}

	}
}