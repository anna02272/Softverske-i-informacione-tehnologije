package rs.ac.uns.ftn.informatika.osa.spring.pr19.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import rs.ac.uns.ftn.informatika.osa.spring.pr19.entity.Category;
import rs.ac.uns.ftn.informatika.osa.spring.pr19.service.CategoryServiceInterface;

@Controller
@SessionAttributes("category")
public class PickCategoryServlet {
  
  @Autowired
  private CategoryServiceInterface categoryService;
  
  @ModelAttribute("category")
  public Category populateCategory() {
      return new Category(); // populates category for the first time if its null
  }
  
  @GetMapping("/cat")
  public String pickCategory(
		  @ModelAttribute("category") Category category,
		  @RequestParam(value="id") Integer categoryId) 
  {
    
    try {
    Category categoryTemp = categoryService.find(categoryId);
    if(categoryTemp != null){
    	  category.setId(categoryTemp.getId());
    	  category.setName(categoryTemp.getName());
    	  category.setProducts(categoryTemp.getProducts());
    	  category.setParent(categoryTemp.getParent());
    	  category.setChildren(categoryTemp.getChildren());
    	  category.setDescription(categoryTemp.getDescription());
      }
      
      return "category";
    } catch (Exception ex) {
      return "login";
    }
  }
  
  @GetMapping("/index")
  public String enterIndexPage() 
  {
    return "index";
  }

}
