package look.and.feel;

import java.util.Properties;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class TestLookAndFeel {

	public static void main(String[] args) throws Exception {

		// koristi look and feel prilagodjen sistemu
//		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		// koristi standardni Javin look and feel ("metal")
//		UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		// koristi Synth look and feel (od Jave 1.5)
//		 UIManager.setLookAndFeel("javax.swing.plaf.synth.SynthLookAndFeel");
		// koristi Motif look and feel
//		UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		// koristi Windows look and feel
//		 UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		// koristi GTK look and feel
//		 UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		// koristi Mac look and feel
//		 UIManager.setLookAndFeel("com.sun.java.swing.plaf.mac.MacLookAndFeel");

		//isto sto i Metal
//		UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		
//		UIManager.setLookAndFeel(new javax.swing.plaf.nimbus.NimbusLookAndFeel());
		int answer1 = JOptionPane.showConfirmDialog(null, "Da li ste sigurni?", "Pitanje",
				JOptionPane.YES_NO_CANCEL_OPTION);

		int answer2 = JOptionPane.showConfirmDialog(null, "Da li ste sigurni?", "Pitanje",
				JOptionPane.OK_CANCEL_OPTION);
	}
}
