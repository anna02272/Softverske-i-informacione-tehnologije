package rs.ac.uns.ftn.informatika.osa.pr25.dao;

import java.util.List;

import rs.ac.uns.ftn.informatika.osa.pr25.entity.Category;


public interface CategoryDao extends GenericDao<Category, Integer>{
  
  public List<Category> findRoots();
  public Category loadWithChildren(int categoryId);
  
}
