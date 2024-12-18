package rs.ac.uns.ftn.informatika.osa.pr19.dao;

import rs.ac.uns.ftn.informatika.osa.pr19.entity.Admin;

public interface AdminDao extends GenericDao<Admin, Integer> {

  public Admin login(String username, String password); 
  
}
