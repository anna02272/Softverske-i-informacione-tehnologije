package prodavnica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import artikli.Artikal;
import artikli.Disk;
import artikli.Knjiga;
import artikli.Kompozicija;
import osobe.Pol;
import osobe.Prodavac;

public class Prodavnica {

	private ArrayList<Prodavac> prodavci;
	private ArrayList<Knjiga> knjige;
	private ArrayList<Disk> diskovi;

	public Prodavnica() {
		this.prodavci = new ArrayList<Prodavac>();
		this.knjige = new ArrayList<Knjiga>();
		this.diskovi = new ArrayList<Disk>();
	}

	public ArrayList<Prodavac> getProdavci() {
		return prodavci;
	}

	public void dodajProdavca(Prodavac prodavac) {
		this.prodavci.add(prodavac);
	}

	public void obrisiProdavca(Prodavac prodavac) {
		this.prodavci.remove(prodavac);
	}

	public Prodavac nadjiProdavca(String korisnickoIme) {
		for (Prodavac prodavac : prodavci) {
			if (prodavac.getKorisnickoIme().equals(korisnickoIme)) {
				return prodavac;
			}
		}
		return null;
	}
	
	public Prodavac login(String korisnickoIme, String lozinka) {
		for(Prodavac prodavac : prodavci) {
			if(prodavac.getKorisnickoIme().equalsIgnoreCase(korisnickoIme) &&
					prodavac.getLozinka().equals(lozinka) && !prodavac.isObrisan()) {
				return prodavac;
			}
		}
		return null;
	}

	public ArrayList<Disk> getDiskovi() {
		return diskovi;
	}

	public void dodajDisk(Disk disk) {
		this.diskovi.add(disk);
	}

	public void obrisiDisk(Disk disk) {
		this.diskovi.remove(disk);
	}

	public ArrayList<Knjiga> getKnjige() {
		return knjige;
	}

	public void dodajKnjigu(Knjiga knjiga) {
		this.knjige.add(knjiga);
	}

	public void obrisiKnjigu(Knjiga knjiga) {
		this.knjige.remove(knjiga);
	}

	public void obrisiArtikal(Artikal a) {
		if (a instanceof Disk) {
			obrisiDisk((Disk) a);
		} else {
			obrisiKnjigu((Knjiga) a);
		}
	}

	public Artikal nadjiArtikal(String identifikacioniKod) {
		for (Disk disk : sviNeobrisaniDiskovi()) {
			if (disk.getIdentifikacioniKod().equals(identifikacioniKod)) {
				return disk;
			}
		}
		for (Knjiga knjiga : sveNeobrisaneKnjige()) {
			if (knjiga.getIdentifikacioniKod().equals(identifikacioniKod)) {
				return knjiga;
			}
		}
		return null;
	}

	public Disk pronadjiDisk(Kompozicija k) {
		for (Disk disk : sviNeobrisaniDiskovi()) {
			if (disk.getKompozicije().contains(k)) {
				return disk;
			}
		}
		return null;
	}

	public Disk pronadjiDisk(String id) {
		for (Disk disk : sviNeobrisaniDiskovi()) {
			if(disk.getIdentifikacioniKod().equals(id)) {
				return disk;
			}
		}
		return null;
	}
	
	public Knjiga pronadjiKnjigu(String id) {
		for (Knjiga knjiga : sveNeobrisaneKnjige()) {
			if(knjiga.getIdentifikacioniKod().equals(id)) {
				return knjiga;
			}
		}
		return null;
	}
	
	public ArrayList<Knjiga> sveNeobrisaneKnjige() {
		ArrayList<Knjiga> neobrisane = new ArrayList<Knjiga>();
		for(Knjiga knjiga : knjige) {
			if(!knjiga.isObrisan()) {
				neobrisane.add(knjiga);
			}
		}
		return neobrisane;
	}
	
	public ArrayList<Disk> sviNeobrisaniDiskovi() {
		ArrayList<Disk> neobrisani = new ArrayList<Disk>();
		for (Disk disk : diskovi) {
			if(!disk.isObrisan()) {
				neobrisani.add(disk);
			}
		}
		return neobrisani;
	}
	
	public void snimiKnjige(String imeFajla) {
		try {
			File file = new File("src/fajlovi/" + imeFajla);
			String content = "";
			for (Knjiga knjiga : knjige) {
				content += knjiga.getIdentifikacioniKod() + "|" + knjiga.getCena() + "|"
						+ knjiga.getNaziv() + "|" + knjiga.isObrisan() + "|"
						+ knjiga.getAutor() + "|" + knjiga.getBrojStrana() + "|" + knjiga.isTvrdeKorice() + "\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja knjiga.");
		}
	}

	public void snimiDiskove(String imeFajla) {
		try {
			File artikliFile = new File("src/fajlovi/" + imeFajla);
			String content = "";
			for (Disk disk : diskovi) {
				content += disk.getIdentifikacioniKod() + "|" + disk.getCena() + "|" 
						+ disk.getNaziv() + "|" + disk.isObrisan() + "|"
						+ disk.getIzvodjac() + "|" + disk.getZanr() + "\n";
			}

			BufferedWriter writer = new BufferedWriter(new FileWriter(artikliFile));
			writer.write(content);
			writer.close();
		} catch (Exception e) {
			System.out.println("Greska prilikom snimanja diskova.");
		}
	}

	public void ucitajKnjige(String imeFajla) {
		try {
			File file = new File("src/fajlovi/" + imeFajla);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				String idKod = split[0];
				String cenString = split[1];
				double cena = Double.parseDouble(cenString);
				String naslov = split[2];
				boolean obrisana = Boolean.parseBoolean(split[3]);
				String autor = split[4];
				String brojStranaString = split[5];
				int brojStrana = Integer.parseInt(brojStranaString);
				boolean korice = Boolean.parseBoolean(split[6]);
				Knjiga knjiga = new Knjiga(idKod, cena, naslov, obrisana, autor, brojStrana, korice);
				knjige.add(knjiga);
				// 003|560.0|Test Knjiga|false|Test autor|14|false
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja podataka o knjigama");
			e.printStackTrace();
		}
	}

	public void ucitajDiskove(String imeFajla) {
		try {
			File file = new File("src/fajlovi/" + imeFajla);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				String idKod = split[0];
				String cenString = split[1];
				double cena = Double.parseDouble(cenString);
				String naslov = split[2];
				boolean obrisan = Boolean.parseBoolean(split[3]);
				String izvodjac = split[4];
				String zanr = split[5];
				ArrayList<Kompozicija> kompozicije = new ArrayList<Kompozicija>();
				Disk disk = new Disk(idKod, cena, naslov, obrisan, izvodjac, zanr, kompozicije);
				diskovi.add(disk);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Prodavac> sviNeobrisaniZaposleni() {
		ArrayList<Prodavac> neobrisani = new ArrayList<Prodavac>();
		for (Prodavac prodavac : prodavci) {
			if(!prodavac.isObrisan()) {
				neobrisani.add(prodavac);
			}
		}
		return neobrisani;
	}
	
	public void ucitajZaposlene(String imeFajla) {
		try {
			File prodavciFile = new File("src/fajlovi/" + imeFajla);
			BufferedReader br = new BufferedReader(new FileReader(prodavciFile));
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] split = line.split("\\|");
				String ime = split[0];
				String prezime = split[1];
				int polInt = Integer.parseInt(split[2]);
				Pol pol = Pol.values()[polInt];
				String korisnickoIme = split[3];
				String sifra = split[4];
				boolean obrisan = Boolean.parseBoolean(split[5]);
				Prodavac prodavac = new Prodavac(ime, prezime, pol, korisnickoIme, sifra, obrisan);
				prodavci.add(prodavac);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void snimiZaposlene(String imeFajla) {
		try {
			File file = new File("src/fajlovi/" + imeFajla);
			BufferedWriter br = new BufferedWriter(new FileWriter(file));
			String sadrzaj = "";
			for (Prodavac prodavac : prodavci) {
				sadrzaj += prodavac.getIme() + "|" + prodavac.getPrezime() + "|" + prodavac.getPol().ordinal() + "|" 
						+ prodavac.getKorisnickoIme() + "|" + prodavac.getLozinka() + "|" + prodavac.isObrisan() + "\n";
			}
			br.write(sadrzaj);
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Kompozicija> sveNeobrisaneKompozicije() {
		ArrayList<Kompozicija> neobrisane = new ArrayList<Kompozicija>();
		for (Disk disk : diskovi) {
			for (Kompozicija kompozicija : disk.getKompozicije()) {
				if(!kompozicija.isObrisana()) {
					neobrisane.add(kompozicija);
				}
			}
		}
		return neobrisane;
	}
	
	public void ucitajKompozicije(String imeFajla) {
		try {
			File kompozicijeFile = new File("src/fajlovi/" + imeFajla);
			BufferedReader br = new BufferedReader(new FileReader(kompozicijeFile));
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] split = line.split("\\|");
				String naziv = split[0];
				int trajanje = Integer.parseInt(split[1]);
				String diskID = split[2];
				Disk d = (Disk) pronadjiDisk(diskID);
				boolean obrisana = Boolean.parseBoolean(split[3]);
				Kompozicija k = new Kompozicija(naziv, trajanje, obrisana);
				if (d != null) {
					d.getKompozicije().add(k);
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void snimiKompozicije(String imeFajla) {
		try {
			File kompozicijeFile = new File("src/fajlovi/kompozicije.txt");
			BufferedWriter br = new BufferedWriter(new FileWriter(kompozicijeFile));
			String sadrzaj = "";
			for (Disk disk : diskovi) {
				for (Kompozicija kompozicija : disk.getKompozicije()) {
					sadrzaj += kompozicija.getNaziv() + "|" + kompozicija.getTrajanje() + "|"
							+ disk.getIdentifikacioniKod() + "|" + kompozicija.isObrisana();
					;
					sadrzaj += "\n";
				}
			}
			br.write(sadrzaj);
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Kompozicija pronadjiKompoziciju(String naziv) {
		for (Disk disk : diskovi) {
			for(Kompozicija kompozicija : disk.getKompozicije()) {
				if(kompozicija.getNaziv().equals(naziv)) {
					return kompozicija;
				}
			}
		}
		return null;
	}
}