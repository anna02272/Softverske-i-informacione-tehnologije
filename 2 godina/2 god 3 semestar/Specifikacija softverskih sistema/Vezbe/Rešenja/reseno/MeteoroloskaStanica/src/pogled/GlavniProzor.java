package pogled;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dogadjaj.Observer;
import dogadjaj.UpdateEvent;
import kontroler.KontrolerStanice;
import model.DnevnaTemperatura;
import repozitorijum.DnevnaTemperaturaRepozitorijum;

public class GlavniProzor extends JFrame implements Observer {

	private static GlavniProzor instance = null;

	private JButton dugmeDodaj;
	private JButton dugmeObrisi;

	public static GlavniProzor getInstance() {
		if (instance == null) {
			instance = new GlavniProzor();
		}
		return instance;
	}

	private JTable tabelaDnevnihTemperatura;
	private KontrolerStanice kontrolerStanice;
	private DnevnaTemperaturaRepozitorijum dnevnaTemperaturaRepozitorijum;

	private GlavniProzor() {
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenDimension.width / 2, screenDimension.height / 2);
		setLocationRelativeTo(null);
		setTitle("Izmerene dnevne temperature");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		dnevnaTemperaturaRepozitorijum = new DnevnaTemperaturaRepozitorijum();
		kontrolerStanice = new KontrolerStanice(dnevnaTemperaturaRepozitorijum);
		tabelaDnevnihTemperatura = new DnevneTemperatureTabela(dnevnaTemperaturaRepozitorijum);

		dnevnaTemperaturaRepozitorijum.addObserver(this);

		initializeActions();

		JScrollPane scrollPane = new JScrollPane(tabelaDnevnihTemperatura);
		add(scrollPane, BorderLayout.CENTER);

		setVisible(true);
	}

	private void initializeActions() {
		JPanel panelTop = new JPanel();
		dugmeDodaj = new JButton("Dodaj novo merenje");
		dugmeObrisi = new JButton("Obrisi merenje");

		panelTop.add(dugmeDodaj);
		panelTop.add(dugmeObrisi);

		dugmeObrisi.setEnabled(false);

		this.add(panelTop, BorderLayout.NORTH);

		dugmeDodaj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Date danasnjiDatum = new Date();
				DnevnaTemperatura dnevnaTemp = new DnevnaTemperatura();

				for (DnevnaTemperatura dt : dnevnaTemperaturaRepozitorijum.getDnevneTemperature()) {
					if (istiDan(danasnjiDatum, dt.getDatum()))
						dnevnaTemp = dt;
				}

				ProzorMeteoroloskeStanice prozorStanice = new ProzorMeteoroloskeStanice(dnevnaTemp, kontrolerStanice);
			}
		});

		dugmeObrisi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BrisanjeTemperatureDijalog brisanjeTemperatureDijalog = new BrisanjeTemperatureDijalog(kontrolerStanice,
						tabelaDnevnihTemperatura.getSelectedRow());
			}
		});

		tabelaDnevnihTemperatura.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent event) {
				if (tabelaDnevnihTemperatura.getSelectedRow() > -1) {
					dugmeObrisi.setEnabled(true);
				}
			}
		});

	}

	public JTable getTabelaDnevnihTemperatura() {
		return tabelaDnevnihTemperatura;
	}

	public static boolean istiDan(Date d1, Date d2) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		return fmt.format(d1).equals(fmt.format(d2));
	}

	@Override
	public void updatePerformed(UpdateEvent e) {
		dugmeObrisi.setEnabled(false);
	}

}
