package Zad03_JtabbedPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class JTabbedPanePrimer extends JFrame {

	String sP = System.getProperty("file.separator");
	String putanjaOsoba, putanjaStudent, putanjaProfesor;
	ImageIcon slOsoba, slStudent, slProfesor;
	
	//GUI
	JTabbedPane tabbedPane;
	
	//PRVA KARTICA
	JPanel panel1;
	JLabel labela1;
	
	//DRUGA KARTICA
	JPanel panel2;
	JLabel labela2;
	
	//RECA KARTICA
	JPanel panel3;
	JLabel labela3;
	
  public JTabbedPanePrimer() {
	putanjaOsoba = "."+sP+"resource"+sP+"images"+sP+"persons"+sP+"osoba.jpg";
	putanjaStudent = "."+sP+"resource"+sP+"images"+sP+"persons"+sP+"student.jpg";
	putanjaProfesor = "."+sP+"resource"+sP+"images"+sP+"persons"+sP+"profesor.jpg";
	slOsoba = new ImageIcon(Toolkit.getDefaultToolkit().getImage(putanjaOsoba));
	slStudent = new ImageIcon(Toolkit.getDefaultToolkit().getImage(putanjaStudent));
	slProfesor = new ImageIcon(Toolkit.getDefaultToolkit().getImage(putanjaProfesor));
	
	setTitle("JTabbedPane primer");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);					
    postaviProstorZaCrtanjeKomponenti();
    pack();
    setMinimumSize(new Dimension(getWidth(), getHeight()));
    setVisible(true);
}

public void postaviProstorZaCrtanjeKomponenti(){
  
    tabbedPane = new JTabbedPane();
    getContentPane().add(tabbedPane, BorderLayout.CENTER);

    //PRVA KARTICA
    panel1 = new JPanel(false); //koristi double buffer koji zauzima dodatni memorisjki prostor sa ciljem da se omoguce brza promena komponenti panela 
    labela1 = new JLabel("Panel 1 - Prikaz i upravljanje sa osobama koje se nalaze na Fakultetu");
    panel1.add(labela1);
    
    tabbedPane.addTab("Tab 1", slOsoba, panel1, "Panel br. 1");
    tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
    
    //DRUGA KARTICA
    panel2 = new JPanel(false); 
    labela2 = new JLabel("Panel 2 - Prikaz i upravljanje sa studentima");
    panel2.add(labela2);
    
    tabbedPane.addTab("Tab 2", slStudent, panel2, "Panel br. 2");
    tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
    
    //TRECA KARTICA
    panel3 = new JPanel(false); 
    labela3 = new JLabel("Panel 3 - Prikaz i upravljanje sa profesorima");
    panel3.add(labela3);
    
    tabbedPane.addTab("Tab 3", slProfesor, panel3, "Panel br. 3");
    tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
  }
}
  