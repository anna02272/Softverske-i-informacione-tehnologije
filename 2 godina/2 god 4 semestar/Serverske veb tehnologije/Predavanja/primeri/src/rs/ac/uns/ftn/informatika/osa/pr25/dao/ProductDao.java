package rs.ac.uns.ftn.informatika.osa.pr25.dao;

import java.util.List;

import rs.ac.uns.ftn.informatika.osa.pr25.entity.Product;


public interface ProductDao extends GenericDao<Product, Integer> {

  public List<Product> findProducts(int categoryId);
  
}
