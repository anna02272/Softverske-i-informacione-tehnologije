package rs.ac.uns.ftn.informatika.osa.spring.pr19.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.osa.spring.pr19.entity.Product;
import rs.ac.uns.ftn.informatika.osa.spring.pr19.repository.ProductRepository;

@Service
public class ProductService implements ProductServiceInterface {
	
	@Autowired
	ProductRepository productRepository;
	
	@Override
	public Product find(Integer productId){
		return productRepository.findOne(productId);
	}

}
