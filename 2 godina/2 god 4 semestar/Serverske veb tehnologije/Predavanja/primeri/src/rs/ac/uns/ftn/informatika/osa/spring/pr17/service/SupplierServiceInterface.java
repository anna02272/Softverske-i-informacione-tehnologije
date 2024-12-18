package rs.ac.uns.ftn.informatika.osa.spring.pr17.service;

import java.util.List;

import rs.ac.uns.ftn.informatika.osa.spring.pr17.entity.Supplier;

public interface SupplierServiceInterface {

	Supplier findOne(Integer productId);
	
	List<Supplier> findAll();
	
	Supplier save(Supplier category);
	
	void remove(Integer id);

}
