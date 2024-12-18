package rs.ac.uns.ftn.informatika.osa.spring.pr25.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.osa.spring.pr25.entity.Category;
import rs.ac.uns.ftn.informatika.osa.spring.pr25.repository.CategoryRepository;

@Service
public class CategoryService implements CategoryServiceInterface{

	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public List<Category> findByParent(Category parent){
		return categoryRepository.findByParent(parent);
	}
	
	@Override
	public Category findOne(Integer categoryId){
		return categoryRepository.findOne(categoryId);
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category save(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public void remove(Integer id) {
		categoryRepository.delete(id);
	}
}
