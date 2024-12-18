package rs.ac.uns.ftn.informatika.osa.spring.pr17.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import rs.ac.uns.ftn.informatika.osa.spring.pr17.entity.Product;
import rs.ac.uns.ftn.informatika.osa.spring.pr17.entity.Supplier;
import rs.ac.uns.ftn.informatika.osa.spring.pr17.service.SupplierService;

@Controller
public class TestController {


		@Autowired
		private SupplierService supplier;

		@GetMapping("/")
		@ResponseBody
		@Transactional(readOnly = false)
		public String test() {
			// lazy fetching u okviru jedne sesije
	        System.out.println(
	            "\n==== TestApplication 1: Citamo sve dobavljace i njihove proizvode unutar jedne sesije");
	        List<Supplier> suppliers = supplier.findAll();
	        print(suppliers);
	        
	            
	        // u bazu dodajemo novog dobavljaca i dva njegova proizvoda
	        // NAPOMENA: proizvodi se nigde eksplicitno ne snimaju u bazu,
	        // zato sto je prisutna anotacija @OneToMany(cascade=ALL, ...) u
	        // klasi Supplier!
	        System.out.println(
	            "\n==== TestApplication 2: Dodajemo novog dobavljaca i dva proizvoda");

	        // napravimo dobavljaca
	        Supplier zika = new Supplier();
	        zika.setName("Zika boss");
	        zika.setAddress("Zikin bulevar 1");

	        // napravimo proizvode i povezemo ih sa dobavljacem
	        Product p1 = new Product();
	        p1.setName("xServer 3650");
	        p1.setVendor("IBM");
	        p1.setPrice(new BigDecimal("1950.00"));
	        p1.setDescription("manji server");
	        p1.setSupplier(zika);
	        zika.getProducts().add(p1);
	        Product p2 = new Product();
	        p2.setName("xServer 4500");
	        p2.setVendor("IBM");
	        p2.setPrice(new BigDecimal("4350.00"));
	        p2.setDescription("veci server");
	        p2.setSupplier(zika);
	        zika.getProducts().add(p2);

	        supplier.save(zika);
	        
	        return "Select * from suppliers";
	        
	      }
	      
	      private  void print(List<Supplier> list) {
	        for (Supplier s: list) {
	          System.out.println(s);
	          for (Product p: s.getProducts()) {
	            System.out.println("  " + p);
	          }
	        }
	      }


	
}
