package rs.ac.uns.ftn.informatika.osa.spring.pr19.service;

import java.util.List;

import rs.ac.uns.ftn.informatika.osa.spring.pr19.entity.Category;

public interface CategoryServiceInterface {

	List<Category> findByParent(Category parent);
	
	Category find(Integer categoryId);
	
}
