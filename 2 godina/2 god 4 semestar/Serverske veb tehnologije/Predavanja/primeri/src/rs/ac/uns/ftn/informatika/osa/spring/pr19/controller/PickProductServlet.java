package rs.ac.uns.ftn.informatika.osa.spring.pr19.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import rs.ac.uns.ftn.informatika.osa.spring.pr19.entity.Product;
import rs.ac.uns.ftn.informatika.osa.spring.pr19.service.ProductServiceInterface;

@Controller
@SessionAttributes("product")
public class PickProductServlet{
  
  @Autowired
  private ProductServiceInterface productService;
  
  @ModelAttribute("product")
  public Product populateProduct() {
      return new Product(); // populates product for the first time if its null
  }
  
  @GetMapping("/prod")
  public String pickCategory(
		  @ModelAttribute("product") Product product,
		  @RequestParam(value="id") Integer productId) 
  {
	  try {
	      Product productTemp = productService.find(productId);
	      if(productTemp!=null){
	    	  product.setId(productTemp.getId());
	    	  product.setName(productTemp.getName());
	    	  product.setCategory(productTemp.getCategory());
	    	  product.setVendor(productTemp.getVendor());
	    	  product.setDescription(productTemp.getDescription());
	    	  product.setPrice(productTemp.getPrice());
	      }
	      
	      return "product";
	    } catch (Exception ex) {
	      return "login";
	    }
  }

}
