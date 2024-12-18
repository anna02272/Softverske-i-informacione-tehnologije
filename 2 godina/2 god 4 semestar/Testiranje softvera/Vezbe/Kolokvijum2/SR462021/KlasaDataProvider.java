package unittesting.parcijalni;

import org.testng.annotations.DataProvider;

public class KlasaDataProvider {

	 @DataProvider(name = "provider")
	    public static Object[][] getTransactionData() {
	        return new Object[][] {
	            {1000.0, 1500.0, 2000.0, "Small amount"},
	            {1000.0, 200.0, 100.0, "Not enough money"},
	            {1000.0, 500.0, 100.0, "Success"}
	        };
	}
	
}
