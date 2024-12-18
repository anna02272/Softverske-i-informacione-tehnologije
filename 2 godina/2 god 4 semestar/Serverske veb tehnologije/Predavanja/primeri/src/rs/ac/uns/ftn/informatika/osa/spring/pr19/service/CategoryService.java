package rs.ac.uns.ftn.informatika.osa.spring.pr19.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.osa.spring.pr19.entity.Category;
import rs.ac.uns.ftn.informatika.osa.spring.pr19.repository.CategoryRepository;

@Service
public class CategoryService implements CategoryServiceInterface{

	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public List<Category> findByParent(Category parent){
		return categoryRepository.findByParent(parent);
	}
	
	@Override
	public Category find(Integer categoryId){
		return categoryRepository.findOne(categoryId);
	}
}
