package rs.ac.uns.ftn.informatika.osa.spring.pr19.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import rs.ac.uns.ftn.informatika.osa.spring.pr19.entity.CreditCard;
import rs.ac.uns.ftn.informatika.osa.spring.pr19.entity.PurchaseOrder;
import rs.ac.uns.ftn.informatika.osa.spring.pr19.entity.User;
import rs.ac.uns.ftn.informatika.osa.spring.pr19.service.PurchaseServiceInterface;
import rs.ac.uns.ftn.informatika.osa.spring.pr19.service.UserServiceInterface;

@Controller
@SessionAttributes({"order", "user", "paymentStatus"})
public class PaymentServlet{

  @Autowired
  PurchaseServiceInterface purchaseService;
  
  @Autowired
  UserServiceInterface userService;

  @ModelAttribute("order")
  public PurchaseOrder populateOrder() {
	return new PurchaseOrder(); // populates order for the first time if its null
  }
  
  @ModelAttribute("user")
  public User populateUser() {
      return new User(); // populates user for the first time if its null
  }
  
  @ModelAttribute("paymentStatus")
  public StringBuilder populatePaymentStatus() {
      return new StringBuilder(); // populates paymentStatus for the first time if its null
  }
  
  @PostMapping("/purchase")
  public String purchase(@ModelAttribute("order") PurchaseOrder order,
		  	@ModelAttribute("user") User user,
		  	@ModelAttribute("paymentStatus") StringBuilder paymentStatus,
		  	@RequestParam("cardNumber") String cardNumber,
			@RequestParam("cardHolder") String cardHolder,
			@RequestParam("expiryYear") Integer expiryYear,
			@RequestParam("expiryMonth") Integer expiryMonth) {
    
    try {
     CreditCard card = new CreditCard(expiryMonth, expiryYear, cardHolder, 
          cardNumber);
      order.setDate(new Date());
      boolean status = purchaseService.processOrder(order, card);
      if (status) {
    	  userService.add(user, order);
    	  order.getItems().clear();
      }
      paymentStatus.setLength(0);
      String temp = status ? "Uspesno" : "Neuspesno";
      paymentStatus.append(temp);
    
    } catch (Exception ex) {
    }
    return "paymentStatus";
  }
  
  @GetMapping("/purchase")
  public String enterPurchasePage(){
    return "pay";
  }
  
}
