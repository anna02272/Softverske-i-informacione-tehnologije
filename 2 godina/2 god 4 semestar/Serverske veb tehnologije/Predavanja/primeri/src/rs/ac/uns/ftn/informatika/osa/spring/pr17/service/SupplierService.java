package rs.ac.uns.ftn.informatika.osa.spring.pr17.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.osa.spring.pr17.entity.Supplier;
import rs.ac.uns.ftn.informatika.osa.spring.pr17.repository.SupplierRepository;

@Service
public class SupplierService implements SupplierServiceInterface {
	
	@Autowired
	SupplierRepository supplierRepository;
	
	@Override
	public Supplier findOne(Integer supplierId){
		return supplierRepository.findOne(supplierId);
	}
	
	@Override
	public List<Supplier> findAll() {
		return supplierRepository.findAll();
	}

	@Override
	public Supplier save(Supplier supplier) {
		return supplierRepository.save(supplier);
	}

	@Override
	public void remove(Integer id) {
		supplierRepository.delete(id);
	}

}
