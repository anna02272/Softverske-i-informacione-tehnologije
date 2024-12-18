package toolkit;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFrame;

class MainFrame extends JFrame {
	
	public MainFrame() {
		
		// Preuzimamo dimenzije ekrana
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		// Podesavamo dimenzije prozora na polovinu dimenzija ekrana
		setSize(screenWidth / 2, screenHeight / 2);
		setLocation(screenWidth / 4, screenHeight / 4);
		// Dodeljujemo ikonu
		Image img = kit.getImage(System.getProperty("user.dir") + "/file_1.jpg");
		setIconImage(img);
		// Podesavamo naslov
		setTitle("My Second GUI App");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void repositionOnScreen(Window window) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		Dimension frameSize = window.getSize();
		window.setLocation((screenSize.width - frameSize.width) / 8, (screenSize.height - frameSize.height) / 8);
	}
	
	public static void resizeOnScreen(Window window) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();		
		int osminaProzoraW = screenSize.width / 8;
		int osminaProzoraH = screenSize.height / 8;
		
		window.setSize(screenSize.width- 2*osminaProzoraW, screenSize.height - 2*osminaProzoraH);	
	}

	public static void main(String[] args) {
		MainFrame mf = new MainFrame();
//		repositionOnScreen(mf);
//		resizeOnScreen(mf);
		mf.setVisible(true);
	}
}
