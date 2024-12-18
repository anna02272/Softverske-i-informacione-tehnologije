package rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.util;


/**
 * Simple check that the credit card number is valid
 */
public class CreditCardValidator  {
        

	/**
	* Checks if the number is valid according to Luhn algorithm.
	*
	* @return true if the number is valid, false otherwise.
	*/
    public static boolean isValid(String number, CreditCardType type) { 	
    	if (number == null) {
            return false;
        }
    	
    	if (type.equals(CreditCardType.VISA)) {
    		// IIN has the pattern 4xxxxx
            return Luhn.isValidNumber(number) && number.substring(0, 1).equals("4");
        } else if (type.equals(CreditCardType.MASTER_CARD)) {
        	// IIN has the pattern 51xxxx-55xxxx
            return Luhn.isValidNumber(number) && (number.substring(0, 2).equals("51") || number.substring(0, 2).equals("55"));
        } else {
            return Luhn.isValidNumber(number);
        }
    }
}

