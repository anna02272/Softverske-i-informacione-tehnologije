package rs.ac.uns.ftn.informatika.osa.spring.pr25.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.osa.spring.pr25.dto.ProductDTO;
import rs.ac.uns.ftn.informatika.osa.spring.pr25.entity.Product;
import rs.ac.uns.ftn.informatika.osa.spring.pr25.service.CategoryServiceInterface;
import rs.ac.uns.ftn.informatika.osa.spring.pr25.service.ProductServiceInterface;
import rs.ac.uns.ftn.informatika.osa.spring.pr25.service.SupplierServiceInterface;

@RestController
@RequestMapping(value="api/products")
public class ProductController {
	
	@Autowired
	private ProductServiceInterface productService;
	
	@Autowired
	private CategoryServiceInterface categoryService;
	
	@Autowired
	private SupplierServiceInterface supplierService;
	
	@GetMapping
	public ResponseEntity<List<ProductDTO>> getProducts() {
		List<Product> products = productService.findAll();
		//convert products to DTOs
		List<ProductDTO> productsDTO = new ArrayList<ProductDTO>();
		for (Product p : products) {
			productsDTO.add(new ProductDTO(p));
		}
		return new ResponseEntity<List<ProductDTO>>(productsDTO, HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") Integer id){
		Product product = productService.findOne(id);
		if(product == null){
			return new ResponseEntity<ProductDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<ProductDTO>(new ProductDTO(product), HttpStatus.OK);
	}
	
	@PostMapping(consumes="application/json")
	public ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductDTO productDTO){
		Product product = new Product();
		product.setName(productDTO.getName());
		product.setVendor(productDTO.getVendor());
		product.setDescription(productDTO.getDescription());
		product.setPrice(productDTO.getPrice());
		product.setCategory(categoryService.findOne(productDTO.getId()));
		product.setSupplier(supplierService.findOne(productDTO.getId()));
	
		product = productService.save(product);
		return new ResponseEntity<ProductDTO>(new ProductDTO(product), HttpStatus.CREATED);	
	}
	
	@PutMapping(value="/{id}", consumes="application/json")
	public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO, @PathVariable("id") Integer id){
		//a product must exist
		Product product = productService.findOne(id); 
		
		if (product == null) {
			return new ResponseEntity<ProductDTO>(HttpStatus.BAD_REQUEST);
		}
		
		product.setName(productDTO.getName());
		product.setVendor(productDTO.getVendor());
		product.setDescription(productDTO.getDescription());
		product.setPrice(productDTO.getPrice());
		product.setCategory(categoryService.findOne(productDTO.getId()));
		product.setSupplier(supplierService.findOne(productDTO.getId()));
	
		product = productService.save(product);
		
		return new ResponseEntity<ProductDTO>(new ProductDTO(product), HttpStatus.OK);	
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable("id") Integer id){
		Product product = productService.findOne(id);
		if (product != null){
			productService.remove(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
