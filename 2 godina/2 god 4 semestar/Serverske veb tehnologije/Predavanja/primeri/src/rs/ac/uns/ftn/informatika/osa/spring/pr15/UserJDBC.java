package rs.ac.uns.ftn.informatika.osa.spring.pr15;

import javax.sql.DataSource;

public interface UserJDBC {
  
	void setDataSource(DataSource ds);
	
	User login(String username, String password);
	
	User load(int userID);
	
	boolean insert(final User user);
	
	boolean update(final User user);
	
	boolean delete(final int userId);
}
