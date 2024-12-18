package rs.ac.uns.ftn.informatika.osa.spring.pr19.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import rs.ac.uns.ftn.informatika.osa.spring.pr19.entity.Category;
import rs.ac.uns.ftn.informatika.osa.spring.pr19.entity.User;
import rs.ac.uns.ftn.informatika.osa.spring.pr19.service.CategoryServiceInterface;
import rs.ac.uns.ftn.informatika.osa.spring.pr19.service.UserServiceInterface;


@Controller
@SessionAttributes({"user", "roots"})
public class LoginServlet {
  
  @Autowired
  private UserServiceInterface userService;
  
  @Autowired
  private CategoryServiceInterface categoryService;
 
  @ModelAttribute("user")
  public User populateUser() {
      return new User(); // populates user for the first time if its null
  }
  
  @ModelAttribute("roots")
  public ArrayList<Category> populateRoots() {
      return new ArrayList<Category>(); // populates roots for the first time if its null
  }
  
  @PostMapping("/logIn")
  public String login(@ModelAttribute("user") User user,
		  	@ModelAttribute("roots") ArrayList<Category> roots,
		  	@RequestParam(value="username") String username, 
		  	@RequestParam(value="password") String password) {
	  if (username == null || password == null)
		  return "login";
	  
	  User userTemp = userService.findByUsernameAndPassword(username, password);
	  if (userTemp == null) {
		  return "login";
	  } else {
		  user.setId(userTemp.getId());
		  user.setFirstName(userTemp.getFirstName());
		  user.setLastName(userTemp.getLastName());
	  }
	  
	  roots.clear();
	  roots.addAll((ArrayList)categoryService.findByParent(null));
	  
	  return "index";
  }
  
  @GetMapping("/")
  public String enterLoginPage() {
	  return "login";
  }

}
