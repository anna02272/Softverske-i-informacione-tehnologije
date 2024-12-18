package rs.ac.uns.ftn.informatika.osa.spring.pr19.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.informatika.osa.spring.pr19.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
