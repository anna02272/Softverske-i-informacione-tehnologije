package rs.ac.uns.ftn.informatika.osa.spring.pr25.service;

import java.util.List;

import rs.ac.uns.ftn.informatika.osa.spring.pr25.entity.Product;

public interface ProductServiceInterface {

	Product findOne(Integer productId);
	
	List<Product> findAll();
	
	Product save(Product category);
	
	void remove(Integer id);

}
