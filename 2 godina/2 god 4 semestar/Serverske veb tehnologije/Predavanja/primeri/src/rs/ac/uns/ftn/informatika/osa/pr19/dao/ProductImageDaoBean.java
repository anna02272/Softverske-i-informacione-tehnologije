package rs.ac.uns.ftn.informatika.osa.pr19.dao;

import javax.ejb.Local;
import javax.ejb.Stateless;

import rs.ac.uns.ftn.informatika.osa.pr19.entity.ProductImage;


@Stateless
@Local(ProductImageDao.class)
public class ProductImageDaoBean extends GenericDaoBean<ProductImage, Integer> implements ProductImageDao {

}
