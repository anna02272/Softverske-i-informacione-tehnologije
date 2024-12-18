package com.ftn.PrviMavenVebProjekat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ftn.PrviMavenVebProjekat.dao.FilmDAO;
import com.ftn.PrviMavenVebProjekat.dao.ZanrDAO;
import com.ftn.PrviMavenVebProjekat.model.Film;
import com.ftn.PrviMavenVebProjekat.model.Zanr;

@Repository
public class FilmDAOImpl implements FilmDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ZanrDAO zanrDAO; 

	private class FilmZanrRowCallBackHandler implements RowCallbackHandler {

		private Map<Long, Film> filmovi = new LinkedHashMap<>();
		
		@Override
		public void processRow(ResultSet resultSet) throws SQLException {
			int index = 1;
			Long filmId = resultSet.getLong(index++);
			String filmNaziv = resultSet.getString(index++);
			Integer filmTrajanje = resultSet.getInt(index++);

			Film film = filmovi.get(filmId);
			if (film == null) {
				film = new Film(filmId, filmNaziv, filmTrajanje);
				filmovi.put(film.getId(), film); // dodavanje u kolekciju
			}

			Long zanrId = resultSet.getLong(index++);
			String zanrNaziv = resultSet.getString(index++);
			Zanr zanr = new Zanr(zanrId, zanrNaziv);
			film.getZanrovi().add(zanr);
		}

		public List<Film> getFilmovi() {
			return new ArrayList<>(filmovi.values());
		}

	}
	
	@Override
	public Film findOne(Long id) {
		String sql = 
				"SELECT f.id, f.naziv, f.trajanje, z.id, z.naziv FROM filmovi f " + 
				"LEFT JOIN filmZanr fz ON fz.filmId = f.id " + 
				"LEFT JOIN zanrovi z ON fz.zanrId = z.id " + 
				"WHERE f.id = ? " + 
				"ORDER BY f.id";

		FilmZanrRowCallBackHandler rowCallbackHandler = new FilmZanrRowCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, id);

		return rowCallbackHandler.getFilmovi().get(0);
	}
	
	@Override
	public List<Film> findAll() {
		String sql = 
				"SELECT f.id, f.naziv, f.trajanje, z.id, z.naziv FROM filmovi f " + 
				"LEFT JOIN filmZanr fz ON fz.filmId = f.id " + 
				"LEFT JOIN zanrovi z ON fz.zanrId = z.id " + 
				"ORDER BY f.id";

		FilmZanrRowCallBackHandler rowCallbackHandler = new FilmZanrRowCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler);

		return rowCallbackHandler.getFilmovi();
	}

	@Transactional
	@Override
	public int save(Film film) {
		PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				String sql = "INSERT INTO filmovi (naziv, trajanje) VALUES (?, ?)";

				PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				preparedStatement.setString(index++, film.getNaziv());
				preparedStatement.setInt(index++, film.getTrajanje());

				return preparedStatement;
			}

		};
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		boolean uspeh = jdbcTemplate.update(preparedStatementCreator, keyHolder) == 1;
		if (uspeh) {
			String sql = "INSERT INTO filmZanr (filmId, zanrId) VALUES (?, ?)";
			for (Zanr itZanr: film.getZanrovi()) {	
				uspeh = uspeh && jdbcTemplate.update(sql, keyHolder.getKey(), itZanr.getId()) == 1;
			}
		}
		return uspeh?1:0;
	}

	@Transactional
	@Override
	public int update(Film film) {
		
		String sql = "DELETE FROM filmZanr WHERE filmId = ?";
		jdbcTemplate.update(sql, film.getId());
	
		boolean uspeh = true;
		sql = "INSERT INTO filmZanr (filmId, zanrId) VALUES (?, ?)";
		for (Zanr itZanr: film.getZanrovi()) {	
			uspeh = uspeh &&  jdbcTemplate.update(sql, film.getId(), itZanr.getId()) == 1;
		}

		sql = "UPDATE filmovi SET naziv = ?, trajanje = ? WHERE id = ?";	
		uspeh = uspeh &&  jdbcTemplate.update(sql, film.getNaziv(), film.getTrajanje(), film.getId()) == 1;
		
		return uspeh?1:0;
	}

	@Transactional
	@Override
	public int delete(Long id) {
		String sql = "DELETE FROM filmZanr WHERE filmId = ?";
		jdbcTemplate.update(sql, id);

		sql = "DELETE FROM filmovi WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}

	private class FilmRowMapper implements RowMapper<Film> {

		@Override
		public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
			int index = 1;
			Long filmId = rs.getLong(index++);
			String filmNaziv = rs.getString(index++);
			Integer filmTrajanje = rs.getInt(index++);

			Film film = new Film(filmId, filmNaziv, filmTrajanje);
			return film;
		}

	}
	
	@Override
	public List<Film> find(String naziv, Long zanrId, Integer trajanjeOd, Integer trajanjeDo) {
		
		ArrayList<Object> listaArgumenata = new ArrayList<Object>();
		
		String sql = "SELECT f.id, f.naziv, f.trajanje FROM filmovi f "; 
		
		StringBuffer whereSql = new StringBuffer(" WHERE ");
		boolean imaArgumenata = false;
		
		if(naziv!=null) {
			naziv = "%" + naziv + "%";
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("f.naziv LIKE ?");
			imaArgumenata = true;
			listaArgumenata.add(naziv);
		}

		if(trajanjeOd!=null) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("f.trajanje >= ?");
			imaArgumenata = true;
			listaArgumenata.add(trajanjeOd);
		}
		
		if(trajanjeDo!=null) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("f.trajanje <= ?");
			imaArgumenata = true;
			listaArgumenata.add(trajanjeDo);
		}
		
		if(imaArgumenata)
			sql=sql + whereSql.toString()+" ORDER BY f.id";
		else
			sql=sql + " ORDER BY f.id";
		System.out.println(sql);
		
		List<Film> filmovi = jdbcTemplate.query(sql, listaArgumenata.toArray(), new FilmRowMapper());
		for (Film film : filmovi) {
			
			film.setZanrovi(findFilmZanr(film.getId(), null));
		}
		//ako se treži film sa određenim žanrom  
		// tada se taj žanr mora nalaziti u listi žanrova od filma
		if(zanrId!=null)
			for (Iterator iterator = filmovi.iterator(); iterator.hasNext();) {
				Film film = (Film) iterator.next();
				boolean zaBrisanje = true;
				for (Zanr zanr : film.getZanrovi()) {
					if(zanr.getId() == zanrId) {
						zaBrisanje = false;
						break;
					}
				}
				if(zaBrisanje)
					iterator.remove();
			}
		
		return filmovi;
	}
	
	private class FilmZanrRowMapper implements RowMapper<Long []> {

		@Override
		public Long [] mapRow(ResultSet rs, int rowNum) throws SQLException {
			int index = 1;
			Long filmId = rs.getLong(index++);
			Long zanrId = rs.getLong(index++);

			Long [] filmZanr = {filmId, zanrId};
			return filmZanr;
		}
	}
	
	private List<Zanr> findFilmZanr(Long filmId, Long zanrId) {
		
		List<Zanr> znaroviFilma = new ArrayList<Zanr>();
		
		ArrayList<Object> listaArgumenata = new ArrayList<Object>();
		
		String sql = 
				"SELECT fz.filmId, fz.zanrId FROM filmZanr fz ";
		
		StringBuffer whereSql = new StringBuffer(" WHERE ");
		boolean imaArgumenata = false;
		
		if(filmId!=null) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("fz.filmId = ?");
			imaArgumenata = true;
			listaArgumenata.add(filmId);
		}
		
		if(zanrId!=null) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("fz.zanrId = ?");
			imaArgumenata = true;
			listaArgumenata.add(zanrId);
		}

		if(imaArgumenata)
			sql=sql + whereSql.toString()+" ORDER BY fz.filmId";
		else
			sql=sql + " ORDER BY fz.filmId";
		System.out.println(sql);
		
		List<Long[]> filmZanrovi = jdbcTemplate.query(sql, listaArgumenata.toArray(), new FilmZanrRowMapper()); 
				
		for (Long[] fz : filmZanrovi) {
			znaroviFilma.add(zanrDAO.findOne(fz[1]));
		}
		return znaroviFilma;
	}
}
