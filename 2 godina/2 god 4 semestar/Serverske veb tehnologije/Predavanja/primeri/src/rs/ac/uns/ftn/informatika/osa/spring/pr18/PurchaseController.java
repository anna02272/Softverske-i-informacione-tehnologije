package rs.ac.uns.ftn.informatika.osa.spring.pr18;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class PurchaseController {

  @Autowired
  Purchase purchase;
  
  @ResponseBody
  @PostMapping(value="/purchase")
	public String purchaseGet(@RequestParam("cardNumber") String cardNumber,
			@RequestParam("cardHolder") String cardHolder,
			@RequestParam("expiryYear") String expiryYear,
			@RequestParam("expiryMonth") String expiryMonth) throws IOException {
	  
	  try {
		  CreditCard card = new CreditCard(Integer.parseInt(expiryMonth), Integer.parseInt(expiryYear), cardHolder, 
	          cardNumber);
	      Order order = new Order(card, new Date(), new ArrayList<String>(), 
	          new ArrayList<Integer>(), new BigDecimal(200.00));
	      boolean status = purchase.processOrder(order);
	    
	      return printSuccess(status);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return printError(e);
		}
	}
  
  @ResponseBody
  @GetMapping(value="/purchase")
	public String purchasePost(@RequestParam("cardNumber") String cardNumber,
			@RequestParam("cardHolder") String cardHolder,
			@RequestParam("expiryYear") String expiryYear,
			@RequestParam("expiryMonth") String expiryMonth) throws IOException {
	  
	  return purchaseGet(cardNumber, cardHolder, expiryYear, expiryMonth);
	}
  
  
  
  private String printError(Exception ex) 
      throws IOException {
    StringBuilder retVal = new StringBuilder();
    retVal.append("<html><body><h1>Error</h1><pre>");
    StringWriter sw = new StringWriter();
    ex.printStackTrace(new PrintWriter(sw));
    retVal.append(sw.toString());
    retVal.append("</pre></body></html>");
    return retVal.toString();
  }

  private String printSuccess(boolean status) 
      throws IOException {
	 StringBuilder retVal = new StringBuilder();
	 retVal.append("<html><body><h1>Order processed</h1> Status: ");
	 retVal.append(status ? "processed" : "not processed");
	 retVal.append("</body></html>");
    return retVal.toString();
  }
}
