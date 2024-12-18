package com.ftn.PrviMavenVebProjekat.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ftn.PrviMavenVebProjekat.dao.ProjekcijaDAO;
import com.ftn.PrviMavenVebProjekat.model.Film;
import com.ftn.PrviMavenVebProjekat.model.Projekcija;

@Repository
public class ProjekcijaDAOImpl implements ProjekcijaDAO{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private class ProjekcijaRowMapper implements RowMapper<Projekcija> {

		@Override
		public Projekcija mapRow(ResultSet rs, int rowNum) throws SQLException {
			int index = 1;
			Long projekcijaId = rs.getLong(index++);
			LocalDateTime projekcijaDatumIVreme = rs.getTimestamp(index++).toLocalDateTime();
			String projekcijaTip = rs.getString(index++);
			Integer projekcijaSala = rs.getInt(index++);
			Double projekcijaCenaKarte = rs.getDouble(index++);

			Long filmId = rs.getLong(index++);
			String filmNaziv = rs.getString(index++);
			Integer filmTrajanje = rs.getInt(index++);
			Film film = new Film(filmId, filmNaziv, filmTrajanje);

			Projekcija projekcija = new Projekcija(projekcijaId, projekcijaDatumIVreme, film, projekcijaSala, projekcijaTip, projekcijaCenaKarte);
			return projekcija;
		}

	}
	@Override
	public Projekcija findOne(Long id) {
		String sql = 
				"SELECT p.id, p.datumIVreme, p.tip, p.sala, p.cenaKarte, f.id, f.naziv, f.trajanje FROM projekcije p " + 
				"LEFT JOIN filmovi f ON p.filmId = f.id " + 
				"WHERE p.id = ? " + 
				"ORDER BY p.id";
		return jdbcTemplate.queryForObject(sql, new ProjekcijaRowMapper(), id);
	}
	@Override
	public List<Projekcija> findAll() {
		String sql = 
				"SELECT p.id, p.datumIVreme, p.tip, p.sala, p.cenaKarte, f.id, f.naziv, f.trajanje FROM projekcije p " + 
				"LEFT JOIN filmovi f ON p.filmId = f.id " + 
				"ORDER BY p.id";
		return jdbcTemplate.query(sql, new ProjekcijaRowMapper());
	}
	@Override
	public List<Projekcija> find(LocalDateTime datumIVremeOd, LocalDateTime datumIVremeDo, Long filmId, String tip, Integer sala, Double cenaKarteOd, Double cenaKarteDo) {
		
		ArrayList<Object> listaArgumenata = new ArrayList<Object>();
		
		String sql = "SELECT p.id, p.datumIVreme, p.tip, p.sala, p.cenaKarte, f.id, f.naziv, f.trajanje FROM projekcije p " + 
				"LEFT JOIN filmovi f ON p.filmId = f.id ";
		
		StringBuffer whereSql = new StringBuffer(" WHERE ");
		boolean imaArgumenata = false;
		
		if(datumIVremeOd!=null) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("p.datumIVreme >= ?");
			imaArgumenata = true;
			listaArgumenata.add(datumIVremeOd);
		}
		
		if(datumIVremeDo!=null) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("p.datumIVreme <= ?");
			imaArgumenata = true;
			listaArgumenata.add(datumIVremeDo);
		}
		
		if(filmId!=null) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("p.filmId = ?");
			imaArgumenata = true;
			listaArgumenata.add(filmId);
		}
		
		if(tip!=null) {
			tip = "%" + tip + "%";
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("p.tip LIKE ?");
			imaArgumenata = true;
			listaArgumenata.add(tip);
		}
		
		if(sala!=null) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("p.sala = ?");
			imaArgumenata = true;
			listaArgumenata.add(sala);
		}
		
		if(cenaKarteOd!=null) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("p.cenaKarte >= ?");
			imaArgumenata = true;
			listaArgumenata.add(cenaKarteOd);
		}
		
		if(cenaKarteDo!=null) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("p.cenaKarte <= ?");
			imaArgumenata = true;
			listaArgumenata.add(cenaKarteDo);
		}
		
		if(imaArgumenata)
			sql=sql + whereSql.toString()+" ORDER BY p.id";
		else
			sql=sql + " ORDER BY p.id";
		System.out.println(sql);
		
		return jdbcTemplate.query(sql, listaArgumenata.toArray(), new ProjekcijaRowMapper());
	}
	
	@Override
	public List<Projekcija> find(HashMap<String, Object> mapaArgumenata) {
		
		ArrayList<Object> listaArgumenata = new ArrayList<Object>();
		
		String sql = "SELECT p.id, p.datumIVreme, p.tip, p.sala, p.cenaKarte, f.id, f.naziv, f.trajanje FROM projekcije p " + 
				"LEFT JOIN filmovi f ON p.filmId = f.id ";
		
		StringBuffer whereSql = new StringBuffer(" WHERE ");
		boolean imaArgumenata = false;
		
		if(mapaArgumenata.containsKey("datumIVremeOd")) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("p.datumIVreme >= ?");
			imaArgumenata = true;
			listaArgumenata.add(mapaArgumenata.get("datumIVremeOd"));
		}
		
		if(mapaArgumenata.containsKey("datumIVremeDo")) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("p.datumIVreme <= ?");
			imaArgumenata = true;
			listaArgumenata.add(mapaArgumenata.get("datumIVremeDo"));
		}
		
		if(mapaArgumenata.containsKey("filmId")) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("p.filmId = ?");
			imaArgumenata = true;
			listaArgumenata.add(mapaArgumenata.get("filmId"));
		}
		
		if(mapaArgumenata.containsKey("tip")) {
			String tip = "%" + mapaArgumenata.get("tip") + "%";
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("p.tip LIKE ?");
			imaArgumenata = true;
			listaArgumenata.add(tip);
		}
		
		if(mapaArgumenata.containsKey("sala")) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("p.sala = ?");
			imaArgumenata = true;
			listaArgumenata.add(mapaArgumenata.get("sala"));
		}
		
		if(mapaArgumenata.containsKey("cenaKarteOd")) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("p.cenaKarte >= ?");
			imaArgumenata = true;
			listaArgumenata.add(mapaArgumenata.get("cenaKarteOd"));
		}
		
		if(mapaArgumenata.containsKey("cenaKarteOd")) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("p.cenaKarte <= ?");
			imaArgumenata = true;
			listaArgumenata.add(mapaArgumenata.get("cenaKarteDo"));
		}
		
		if(imaArgumenata)
			sql=sql + whereSql.toString()+" ORDER BY p.id";
		else
			sql=sql + " ORDER BY p.id";
		System.out.println(sql);
		
		return jdbcTemplate.query(sql, listaArgumenata.toArray(), new ProjekcijaRowMapper());
	}
	
	@Override
	public int save(Projekcija projekcija) {
		String sql = "INSERT INTO projekcije (datumIVreme, filmId, tip, sala, cenaKarte) VALUES (?, ?, ?, ?, ?)";
		return jdbcTemplate.update(sql, projekcija.getDatumIVreme(), projekcija.getFilm().getId(), projekcija.getTip(), projekcija.getSala(), projekcija.getCenaKarte());
	}
	@Override
	public int update(Projekcija projekcija) {
		String sql = "UPDATE projekcije SET datumIVreme = ?, filmID = ?, tip = ?, sala = ?, cenaKarte = ? WHERE id  = ?";
		return jdbcTemplate.update(sql, projekcija.getDatumIVreme(), projekcija.getFilm().getId(), projekcija.getTip(), projekcija.getSala(), projekcija.getCenaKarte(), projekcija.getId());
	}
	@Override
	public int delete(Long id) {
		String sql = "DELETE FROM projekcije WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}
	
}
