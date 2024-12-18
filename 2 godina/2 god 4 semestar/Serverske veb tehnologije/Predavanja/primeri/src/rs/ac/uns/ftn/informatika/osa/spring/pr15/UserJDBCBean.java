package rs.ac.uns.ftn.informatika.osa.spring.pr15;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

@Repository
public class UserJDBCBean implements UserJDBC {

	private JdbcTemplate jdbcTemplate;
		
	@Override
    @Autowired
    public void setDataSource(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }
    
    /**
     * Ucitava korisnika sa datim username i password.
     * @return User ako postoji
     */
    @Override
    public User login(String username, String password) {
      User user = null;
      try {
        user = jdbcTemplate.query("SELECT user_id, username, pasword FROM users WHERE username='" + username + "' AND pasword='" + password + "'", new ResultSetExtractor<User>(){  
            @Override  
            public User extractData(ResultSet rs) throws SQLException,  
                   DataAccessException {  
            	User retVal = null;
               if(rs.next()){  
            	   retVal =  new User(rs.getInt(1), rs.getString(2), rs.getString(3));
               }
               return retVal;
            }  
        }); 
      } catch (Exception ex) {
        ex.printStackTrace();
      } finally {
      }
      return user;
    }
    
    /**
     * Ucitava podatke o korisniku na osnovu njegovog userID-a
     * @param userID identifikator korisnika
     * @return User ako je operacija uspesna
     */
    @Override
    public User load(int userID) {
    	User user = null;
        try {
          user = jdbcTemplate.query("SELECT user_id, username, pasword FROM users WHERE user_id=" + userID, new ResultSetExtractor<User>(){  
              @Override  
              public User extractData(ResultSet rs) throws SQLException,  
                     DataAccessException {  
              	User retVal = null;
                 if(rs.next()){  
              	   retVal =  new User(rs.getInt(1), rs.getString(2), rs.getString(3));
                 }
                 return retVal;
              }  
          }); 
        } catch (Exception ex) {
          ex.printStackTrace();
        } finally {
        }
        return user;
    }
    
    /**
     * Dodaje korisnika u bazu.
     * @return true ako je operacija uspesna
     */
    @Override
    public boolean insert(final User user) {
      boolean success = false;
      try {
        int rowsAffected = jdbcTemplate.update("INSERT INTO users (user_id, username, pasword) VALUES (?, ?, ?)", new PreparedStatementSetter() {
              public void setValues(PreparedStatement preparedStatement) throws SQLException {
            	  preparedStatement.setInt(1, user.getUserID());
            	  preparedStatement.setString(2, user.getUsername());
            	  preparedStatement.setString(3, user.getPassword());
              }
            });
        success = rowsAffected > 0;
      } catch (Exception ex) {
        ex.printStackTrace();
      } finally {
      }
      return success;
      
    }
    
    /**
     * Azurira korisnikove podatke u bazi.
     * @return true ako je operacija uspesna
     */
    @Override
    public boolean update(final User user) {
    	boolean success = false;
        try {
          int rowsAffected = jdbcTemplate.update("UPDATE users SET username=?, pasword=? WHERE user_id=?", new PreparedStatementSetter() {
                public void setValues(PreparedStatement preparedStatement) throws SQLException {
                  preparedStatement.setString(1, user.getUsername());
                  preparedStatement.setString(2, user.getPassword());
                  preparedStatement.setInt(3, user.getUserID());
                }
              });
          success = rowsAffected > 0;
        } catch (Exception ex) {
          ex.printStackTrace();
        } finally {
        }
        return success;
    }
    
    /**
     * Brise korisnika iz baze.
     * @return true ako je operacija uspesna
     */
    @Override
    public boolean delete(final int userId) {
    	boolean success = false;
        try {
          int rowsAffected = jdbcTemplate.update("DELETE FROM users WHERE user_id=?", new PreparedStatementSetter() {
                public void setValues(PreparedStatement preparedStatement) throws SQLException {
                  preparedStatement.setInt(1, userId);
                }
              });
          success = rowsAffected > 0;
        } catch (Exception ex) {
          ex.printStackTrace();
        } finally {
        }
        return success;
    	
    }
}
