package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import prodavnica.Prodavnica;
import util.WindowUtils;

public class MainFrame extends JFrame {
	
	private JMenuBar meni;
	private JMenu korisniciMeni;
	private JMenuItem korisniciItem; 
	
	public MainFrame() {
		setTitle("Prodavnica");
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		WindowUtils.centerOnScreen(this);
		initGui();
	}
	
	private void initGui() {
		meni = new JMenuBar();
		setJMenuBar(meni);
		korisniciMeni = new JMenu("Korisnici");
		meni.add(korisniciMeni);
		korisniciItem = new JMenuItem("Korisnici");
		korisniciMeni.add(korisniciItem);
		
		korisniciItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				KorisniciFrame kf = new KorisniciFrame();
				kf.setVisible(true);
			}
		});
	}
}
