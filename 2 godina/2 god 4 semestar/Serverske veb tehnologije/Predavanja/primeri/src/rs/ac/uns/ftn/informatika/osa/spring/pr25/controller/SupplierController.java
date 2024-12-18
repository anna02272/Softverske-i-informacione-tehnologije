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

import rs.ac.uns.ftn.informatika.osa.spring.pr25.dto.SupplierDTO;
import rs.ac.uns.ftn.informatika.osa.spring.pr25.entity.Supplier;
import rs.ac.uns.ftn.informatika.osa.spring.pr25.service.SupplierServiceInterface;

@RestController
@RequestMapping(value="api/suppliers")
public class SupplierController {
	
	@Autowired
	private SupplierServiceInterface supplierService;
	
	@GetMapping
	public ResponseEntity<List<SupplierDTO>> getCategories() {
		List<Supplier> suppliers = supplierService.findAll();
		//convert suppliers to DTOs
		List<SupplierDTO> suppliersDTO = new ArrayList<SupplierDTO>();
		for (Supplier c : suppliers) {
			suppliersDTO.add(new SupplierDTO(c));
		}
		return new ResponseEntity<List<SupplierDTO>>(suppliersDTO, HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<SupplierDTO> getSupplier(@PathVariable("id") Integer id){
		Supplier supplier = supplierService.findOne(id);
		if(supplier == null){
			return new ResponseEntity<SupplierDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<SupplierDTO>(new SupplierDTO(supplier), HttpStatus.OK);
	}
	
	@PostMapping(consumes="application/json")
	public ResponseEntity<SupplierDTO> saveSupplier(@RequestBody SupplierDTO supplierDTO){
		Supplier supplier = new Supplier();
		supplier.setName(supplierDTO.getName());
		supplier.setAddress(supplierDTO.getAddress());
	
		supplier = supplierService.save(supplier);
		return new ResponseEntity<SupplierDTO>(new SupplierDTO(supplier), HttpStatus.CREATED);	
	}
	
	@PutMapping(value="/{id}", consumes="application/json")
	public ResponseEntity<SupplierDTO> updateSupplier(@RequestBody SupplierDTO supplierDTO, @PathVariable("id") Integer id){
		//a supplier must exist
		Supplier supplier = supplierService.findOne(supplierDTO.getId()); 
		
		if (supplier == null) {
			return new ResponseEntity<SupplierDTO>(HttpStatus.BAD_REQUEST);
		}
		
		supplier.setName(supplierDTO.getName());
		supplier.setAddress(supplierDTO.getAddress());
	
		supplier = supplierService.save(supplier);
		
		return new ResponseEntity<SupplierDTO>(new SupplierDTO(supplier), HttpStatus.OK);	
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deleteSupplier(@PathVariable("id") Integer id){
		Supplier supplier = supplierService.findOne(id);
		if (supplier != null){
			supplierService.remove(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
