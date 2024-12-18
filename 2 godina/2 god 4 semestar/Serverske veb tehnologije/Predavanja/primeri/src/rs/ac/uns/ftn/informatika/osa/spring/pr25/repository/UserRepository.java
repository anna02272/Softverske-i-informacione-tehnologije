package rs.ac.uns.ftn.informatika.osa.spring.pr25.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.informatika.osa.spring.pr25.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username);
	
}
