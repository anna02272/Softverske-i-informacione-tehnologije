package prozor;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import automat.Konstante;
import automat.KontrolerParkinga;
import automat.event.UpdateEvent;
import automat.event.UpdateListener;

/**
 * Testni prozor koji omogucava isprobavanje rada kontrolera parkinga 
 *
 */

public class TestProzor extends JDialog {
	
	private JButton btnUsaoAutomobil;
	private JButton btnIzasaoAutomobil;		
	
	private KontrolerParkinga kontroler;	
	
	public TestProzor() {
		setTitle("Test prozor");		
		setSize(300, 400);		
		setLayout(new GridLayout(2, 2));
		
		kontroler = new KontrolerParkinga();
		
		kreirajPanele();
		kreirajDugmad();
	}
	
	//Kreiranje panela koji predstavljaju semafor i displej za prikaz raspolozivih mesta 
	private void kreirajPanele() {
		SemaforPanel pnlSemafor = new SemaforPanel();
		add(pnlSemafor);
		
		BrojMestaPanel pnlBrojMesta = new BrojMestaPanel();
		add(pnlBrojMesta);
		
		//panel za prikaz broja mesta se registruje za slusanje dogadjaja o promeni podataka u kontroleru parkinga 
		kontroler.addListener(pnlBrojMesta);
		
		//panel koji simulira svetla na semaforu se registruje za slusanje dogadjaja o promeni podataka u kontroleru parkinga
		kontroler.addListener(pnlSemafor);
	}
	
	
	//Kreiranje dugmadi koja simuliraju ulaske i izlaske automobila:
	private void kreirajDugmad() {
		
		JPanel btnPanel = new JPanel();		
		
		btnUsaoAutomobil = new JButton();
		btnUsaoAutomobil.setText("Ulaz automobila");		
		
		btnUsaoAutomobil.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				kontroler.usaoAutomobil();
			}
		});
		
		btnPanel.add(btnUsaoAutomobil);
		
		btnIzasaoAutomobil = new JButton();
		btnIzasaoAutomobil.setText("Izlaz automobila");
		
		btnIzasaoAutomobil.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				kontroler.izasaoAutomobil();
			}
		});		
		
		btnPanel.add(btnIzasaoAutomobil);
		add(btnPanel);
	}	
	

	public static void main(String[] args) {
		TestProzor prozor = new TestProzor();	
		prozor.setVisible(true);				
	}

}
