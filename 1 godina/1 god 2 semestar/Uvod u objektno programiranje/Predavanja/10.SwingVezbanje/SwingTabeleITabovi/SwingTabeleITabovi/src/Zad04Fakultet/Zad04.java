package Zad04Fakultet;

import Zad04Fakultet.DataClasses.KategorijaFinanasiranja;
import Zad04Fakultet.DataClasses.Profesor;
import Zad04Fakultet.DataClasses.Student;
import Zad04Fakultet.Database.HobiDB;
import Zad04Fakultet.Database.KategorijaFinanasiranjaDB;
import Zad04Fakultet.Database.ProfesorDB;
import Zad04Fakultet.Database.StudentDB;

public class Zad04 {


	public static void main(String[] args) {
		HobiDB.citajIzFajla();
		KategorijaFinanasiranjaDB.citajIzFajla();
		ProfesorDB.citajIzFajla();	
		StudentDB.citajIzFajla();

			
		OsnovniProzor proz = new OsnovniProzor();
		proz.setVisible(true);
		
		System.out.println(StudentDB.listaStudenta.size()+" studenti");
		for (Student el : StudentDB.listaStudenta) {
			System.out.println(el);
		}
		
		System.out.println(ProfesorDB.listaProfesora.size()+" profesori");
		for (Profesor el : ProfesorDB.listaProfesora) {
			System.out.println(el);
		}
	}
}
