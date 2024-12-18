package rs.ac.uns.ftn.informatika.osa.pr19.dao;

import java.util.List;

import rs.ac.uns.ftn.informatika.osa.pr19.entity.Category;


public interface CategoryDao extends GenericDao<Category, Integer>{
  
  public List<Category> findRoots();
  public Category loadWithChildren(int categoryId);
  
}
