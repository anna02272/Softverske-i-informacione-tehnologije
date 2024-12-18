package com.ftn.PrviMavenVebProjekat.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ftn.PrviMavenVebProjekat.dao.KorisnikDAO;
import com.ftn.PrviMavenVebProjekat.model.Korisnik;

@Repository
public class KorisnikDAOImpl implements KorisnikDAO{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private class KorisnikRowMapper implements RowMapper<Korisnik> {

		@Override
		public Korisnik mapRow(ResultSet rs, int rowNum) throws SQLException {
			int index = 1;
			String korisnickoIme = rs.getString(index++);
			String eMail = rs.getString(index++);
			String pol = rs.getString(index++);
			Boolean administrator = rs.getBoolean(index++);

			Korisnik korisnik = new Korisnik(korisnickoIme, null, eMail, pol, administrator);
			return korisnik;
		}

	}
	@Override
	public Korisnik findOne(String korisnickoIme) {
		try {
			String sql = "SELECT korisnickoIme, eMail, pol, administrator FROM korisnici WHERE korisnickoIme = ?";
			return jdbcTemplate.queryForObject(sql, new KorisnikRowMapper(), korisnickoIme);
		} catch (EmptyResultDataAccessException ex) {
			// ako korisnik nije pronađen
			return null;
		}
	}
	@Override
	public Korisnik findOne(String korisnickoIme, String lozinka) {
		try {
			String sql = "SELECT korisnickoIme, eMail, pol, administrator FROM korisnici WHERE korisnickoIme = ? AND lozinka = ?";
			return jdbcTemplate.queryForObject(sql, new KorisnikRowMapper(), korisnickoIme, lozinka);
		} catch (EmptyResultDataAccessException ex) {
			// ako korisnik nije pronađen
			return null;
		}
	}
	@Override
	public List<Korisnik> findAll() {
		String sql = "SELECT korisnickoIme, eMail, pol, administrator FROM korisnici";
		return jdbcTemplate.query(sql, new KorisnikRowMapper());
	}
	@Override
	public List<Korisnik> find(String korisnickoIme, String eMail, String pol, Boolean administrator) {
		
		ArrayList<Object> listaArgumenata = new ArrayList<Object>();
		
		String sql = "SELECT korisnickoIme, eMail, pol, administrator FROM korisnici ";
		
		StringBuffer whereSql = new StringBuffer(" WHERE ");
		boolean imaArgumenata = false;
		
		if(korisnickoIme!=null) {
			korisnickoIme = "%" + korisnickoIme + "%";
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("korisnickoIme LIKE ?");
			imaArgumenata = true;
			listaArgumenata.add(korisnickoIme);
		}
		
		if(eMail!=null) {
			eMail = "%" + eMail + "%";
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("eMail LIKE ?");
			imaArgumenata = true;
			listaArgumenata.add(eMail);
		}
		
		if(pol!=null) {
			pol = "%" + pol + "%";
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("pol LIKE ?");
			imaArgumenata = true;
			listaArgumenata.add(pol);
		}
		
		if(administrator!=null) {	
			//vraća samo administratore ili sve korisnike sistema
			String administratorSql = (administrator)? "administrator = 1": "administrator >= 0";
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append(administratorSql);
			imaArgumenata = true;
		}
		
		
		if(imaArgumenata)
			sql=sql + whereSql.toString()+" ORDER BY korisnickoIme";
		else
			sql=sql + " ORDER BY korisnickoIme";
		System.out.println(sql);
		
		return jdbcTemplate.query(sql, listaArgumenata.toArray(), new KorisnikRowMapper());
	}
	@Override
	public void save(Korisnik korisnik) {
		String sql = "INSERT INTO korisnici (korisnickoIme, lozinka, eMail, pol, administrator) VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, korisnik.getKorisnickoIme(), korisnik.getLozinka(), korisnik.getEMail(), korisnik.getPol(), korisnik.isAdministrator());
	}
	@Override
	public void update(Korisnik korisnik) {
		if (korisnik.getLozinka() == null) {
			String sql = "UPDATE korisnici SET eMail = ?, pol = ?, administrator = ? WHERE korisnickoIme = ?";
			jdbcTemplate.update(sql, korisnik.getEMail(), korisnik.getPol(), korisnik.isAdministrator(), korisnik.getKorisnickoIme());
		} else {
			String sql = "UPDATE korisnici SET lozinka = ?, eMail = ?, pol = ?, administrator = ? WHERE korisnickoIme = ?";
			jdbcTemplate.update(sql, korisnik.getLozinka(), korisnik.getEMail(), korisnik.getPol(), korisnik.isAdministrator(), korisnik.getKorisnickoIme());
		}
	}
	@Override
	public void delete(String korisnickoIme) {
		String sql = "DELETE FROM korisnici WHERE korisnickoIme = ?";
		jdbcTemplate.update(sql, korisnickoIme);
	}
	
}
