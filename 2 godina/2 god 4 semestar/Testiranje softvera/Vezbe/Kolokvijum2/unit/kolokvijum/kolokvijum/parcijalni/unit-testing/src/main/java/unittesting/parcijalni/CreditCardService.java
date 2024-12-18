package unittesting.parcijalni;

public class CreditCardService {

	private CreditCardDAO creditCardDAO;
	
	/**
	 * Metoda koja menja stanje na racunu kada se desi uplata/isplata
	 * @param balanceOnAccount stanje na kreditnoj kartici
	 * @param amount suma novca koja se prenosi transakcijom
	 * @param payout true ako se desila isplata, false ako se desila uplata
	 */
	public double calculateAmount(double balanceOnAccount, double amount, boolean payout){
		if(payout){
			return balanceOnAccount - amount;
		}
	    return balanceOnAccount + amount;
	}
	
	/**
	 * Metoda koja proverava da li je isplata moguca
	 * @param balanceOnAccount stanje na kreditnoj kartici
	 * @param amount suma novca koja se prenosi transakcijom
	 */
	public boolean canPayout(double balanceOnAccount, double amount){
		double newBalance = balanceOnAccount - amount;
		if(newBalance > 0){
			return true;
		}
		return false;
	}
	
	/**
	 * Metoda izvrsava novcanu transakciju 
	 * 
	 * Ukoliko je minimalni iznos transakcije veci od sume novca koja se prenosi, 
	 * metoda vraca "Small amount"
	 * 
	 * Ukoliko se na računu kreditne kartice nalazi manje novca od sume koja se prenosi,
	 * metoda vraca "Not enough money"
	 *
	 * Ukoliko je moguće izvršiti transakciju metoda vraca "Sucess"
	 * 
	 * @param balanceOnAccount stanje na kreditnoj kartici
	 * @param amount suma novca koja se prenosi transakcijom
	 * @param minimum minimalan iznos po transakciji
	 */
	public String transaction(double balanceOnAccount, double amount, double minimum) {

		if (minimum > amount) {
			return "Small amount";
		}

		if (balanceOnAccount < amount) {
			return "Not enough money";
		}

		return "Success";
	}

	/**
	 * Metoda proverava da li prosleđeni PIN odgovara prosleđenom broju kreditne
	 * kartice
	 * 
	 * Ukoliko kreditna kartica ne postoji u sistemu, baca NullPointerException()
	 * 
	 * Ukoliko PIN nije ispavan i broj pokušaja je manji od 3, vraća False
	 * 
	 * Ukoliko PIN nije isparav i broj pokušaja je veći od 3, baca False
	 * 
	 * Ukoliko je PIN isparav vraća True
	 * 
	 */
	public boolean tryPIN(Long creditCardNumber, int PIN) {

		CreditCard creditCard = creditCardDAO.findByCreditCardNumber(creditCardNumber);

		if (creditCard.getPin() != PIN && creditCard.getNumOfTries() < 3) {
			return false;
		} else if (creditCard.getPin() != PIN && creditCard.getNumOfTries() >= 3) {
			return false;
		}

		return true;
	}

	public void setCreditCardDAO(CreditCardDAO creditCardDAO) {
		this.creditCardDAO = creditCardDAO;
	}

}
