package rs.ac.uns.ftn.informatika.osa.spring.pr19.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import rs.ac.uns.ftn.informatika.osa.spring.pr19.entity.OrderItem;
import rs.ac.uns.ftn.informatika.osa.spring.pr19.entity.Product;
import rs.ac.uns.ftn.informatika.osa.spring.pr19.entity.PurchaseOrder;

@Controller
@SessionAttributes({"order", "product"})
public class AddToCartServlet {
  
	  @ModelAttribute("product")
	  public Product populateProduct() {
		  return new Product(); // populates product for the first time if its null
	  }
	
	  @ModelAttribute("order")
	  public PurchaseOrder populateOrder() {
		return new PurchaseOrder();// populates order for the first time if its null
	  }
	
	@PostMapping("/add")
	public String addToCart(
			@ModelAttribute("order") PurchaseOrder order,
			@ModelAttribute("product") Product product,
			@RequestParam(value="quantity") Integer quantity) {

    
    try {
      
      OrderItem item = new OrderItem();
      item.setOrder(order);
      item.setProduct(product);
      item.setQuantity(quantity);
      order.add(item);

      return "cart";
    } catch (Exception ex) {
      return "login";
    }
  }

}
