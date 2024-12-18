package t1.primer04;

public class ControlFlowIfSwitch01 {

	public static void main(String[] args) {
		// IF naredba
		int ocena;
		int bodovi = 44;
		if (bodovi >= 91)
			ocena = 10;
		else if (bodovi >= 81)
			ocena = 9;
		else if (bodovi >= 71)
			ocena = 8;
		else if (bodovi >= 61)
			ocena = 7;
		else if (bodovi >= 51)
			ocena = 6;
		else
			ocena = 5;

		System.out.println("Ocena je " + ocena);

		switch (1213124) {
			case 5:
				System.out.println("Odlican");
				break;
			case 4:
				System.out.println("Vrlo dobar");
				break;
			default:
				System.out.println("Nepostojeca");
		}

		// SWITCH naredba
		int mesec = 2;
		// menjati vrednost meseca 1..13
		switch (mesec) {
		case 1:
			System.out.println("Januar");
			break;
		case 2:
			System.out.println("Februar");
			break;
		case 3:
			System.out.println("Mart");
			break;
		case 4:
			System.out.println("April");
			break;
		case 5:
			System.out.println("Maj");
			break;
		case 6:
			System.out.println("Juni");
			break;
		case 7:
		case 8:
		case 9:
			System.out.println("Letnji meseci");
			break;
		case 10:
			System.out.println("Oktobar");
			break;
		case 11:
			System.out.println("November");
			break;
		case 12:
			System.out.println("Decembar");
			break;
		default:
			System.out.println("Nepostojeci mesec");
			break;
		}
	}
}
