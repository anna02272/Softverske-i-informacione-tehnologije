package rs.ac.uns.ftn.informatika.osa.spring.pr25.service;

import java.util.List;

import rs.ac.uns.ftn.informatika.osa.spring.pr25.entity.Supplier;

public interface SupplierServiceInterface {

	Supplier findOne(Integer productId);
	
	List<Supplier> findAll();
	
	Supplier save(Supplier category);
	
	void remove(Integer id);

}
