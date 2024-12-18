package com.ftn.PrviMavenVebProjekat.dao.impl;

import java.lang.reflect.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.ftn.PrviMavenVebProjekat.bean.SecondConfiguration.ConnectionManager;
import com.ftn.PrviMavenVebProjekat.dao.ZanrDAO;
import com.ftn.PrviMavenVebProjekat.model.Zanr;

@Repository
@Qualifier("zanrDAOOldCode")
public class ZanrDAOImplOldCode implements ZanrDAO{

	@Autowired
	ConnectionManager connectionManager;
	
	@Override
	public Zanr findOne(Long id) {
		Zanr zanr = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT id, naziv FROM zanrovi WHERE id = ?";
			System.out.println(sql);
			stmt = connectionManager.getConnection().prepareStatement(sql);
			int index = 1;
			stmt.setLong(index++, id);
			rset = stmt.executeQuery();

			if (rset.next()) {
				zanr = new Zanr(id, rset.getString(2));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
			try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
			try {connectionManager.closeConnection();} catch (Exception ex) {ex.printStackTrace();}
		}

		return zanr;
	}
	
	@Override
	public List<Zanr> findAll() {
		List<Zanr> zanrovi = new ArrayList<Zanr>();

		Statement stmt = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT id, naziv FROM zanrovi";
			System.out.println(sql);
			stmt = connectionManager.getConnection().createStatement();
			rset = stmt.executeQuery(sql);

			while (rset.next()) {
				int index = 1;
				Long id = rset.getLong(index++);
				String naziv = rset.getString(index++);

				Zanr zanr = new Zanr(id, naziv);
				zanrovi.add(zanr); 
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
			try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
			try {connectionManager.closeConnection();} catch (Exception ex) {ex.printStackTrace();}
		}
		return zanrovi;
	}
	
	@Override
	public List<Zanr> find(String naziv) {
		List<Zanr> zanrovi = new ArrayList<Zanr>();

		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT id, naziv FROM zanrovi WHERE naziv LIKE ?";
			System.out.println(sql);
			stmt = connectionManager.getConnection().prepareStatement(sql);
			int index = 1;
			naziv = "%" + naziv + "%";
			stmt.setString(index++, naziv);
			System.out.println(stmt);

			rset = stmt.executeQuery();

			while (rset.next()) {
				Zanr zanr = new Zanr(rset.getLong(1), rset.getString(2));
				zanrovi.add(zanr); 
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
			try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
			try {connectionManager.closeConnection();} catch (Exception ex) {ex.printStackTrace();}
		}

		return zanrovi;
	}
	
	
	@Override
	public int save (Zanr zanr) {
		PreparedStatement stmt = null;
		int rezultat = 0;
		try {
			String sql = "INSERT INTO zanrovi (naziv) VALUES (?)";
			System.out.println(sql);
			stmt = connectionManager.getConnection().prepareStatement(sql);
			int index = 1;
			stmt.setString(index++, zanr.getNaziv());
			rezultat = stmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
			try {connectionManager.closeConnection();} catch (Exception ex) {ex.printStackTrace();}
		}
		return rezultat; 
	}
	
	@Override
	public int[] save(ArrayList<Zanr> zanrovi) {
		PreparedStatement stmt = null;
		int [] result = new int [zanrovi.size()];
		try {
			String sql = "INSERT INTO zanrovi (naziv) VALUES (?)";
			System.out.println(sql);
			stmt = connectionManager.getConnection().prepareStatement(sql);
			for (int i=0; i<zanrovi.size();i++) {
				int index = 1;
				stmt.setString(index++, zanrovi.get(i).getNaziv());
				result[i] = stmt.executeUpdate();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
			try {connectionManager.closeConnection();} catch (Exception ex) {ex.printStackTrace();}
		}
		return result;
	}
	
	@Override
	public int update(Zanr zanr) {
		
		PreparedStatement stmt = null;
		int rezultat = 0;
		try {
			String sql = "UPDATE zanrovi SET naziv = ? WHERE id = ?";
			System.out.println(sql);
			stmt = connectionManager.getConnection().prepareStatement(sql);
			int index = 1;
			stmt.setString(index++, zanr.getNaziv());
			stmt.setLong(index++, zanr.getId());
			rezultat = stmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
			try {connectionManager.closeConnection();} catch (Exception ex) {ex.printStackTrace();}
		}
		return rezultat;
	}
	@Override
	public int delete(Long id) {
		Statement stmt = null;
		int rezultat = 0;
		try {
			String sql = "DELETE FROM proizvodi WHERE id = " + id;
			System.out.println(sql);

			stmt = connectionManager.getConnection().createStatement();
			rezultat = stmt.executeUpdate(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
			try {connectionManager.closeConnection();} catch (Exception ex) {ex.printStackTrace();}
		}
		return rezultat;
	}
}
