package rs.ac.uns.ftn.informatika.osa.spring.pr25.service;

import java.util.List;

import rs.ac.uns.ftn.informatika.osa.spring.pr25.entity.Category;

public interface CategoryServiceInterface {

	List<Category> findByParent(Category parent);
	
	Category findOne(Integer categoryId);
	
	List<Category> findAll();
	
	Category save(Category category);
	
	void remove(Integer id);
	
}
