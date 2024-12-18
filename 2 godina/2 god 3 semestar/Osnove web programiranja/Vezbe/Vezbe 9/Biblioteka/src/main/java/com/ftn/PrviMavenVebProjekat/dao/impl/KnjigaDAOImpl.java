package com.ftn.PrviMavenVebProjekat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ftn.PrviMavenVebProjekat.dao.KnjigaDAO;
import com.ftn.PrviMavenVebProjekat.model.Knjiga;

@Repository
public class KnjigaDAOImpl implements KnjigaDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private class KnjigaRowCallBackHandler implements RowCallbackHandler {

		private Map<Long, Knjiga> knjige = new LinkedHashMap<>();
		
		@Override
		public void processRow(ResultSet resultSet) throws SQLException {
			int index = 1;
			Long id = resultSet.getLong(index++);
			String naziv = resultSet.getString(index++);
			String registarskiBrojPrimerka = resultSet.getString(index++);
			String jezik = resultSet.getString(index++);
			Integer brojStranica = resultSet.getInt(index++);
			boolean izdata = resultSet.getBoolean(index++);

			Knjiga knjiga = knjige.get(id);
			if (knjiga == null) {
				knjiga = new Knjiga(id, naziv, registarskiBrojPrimerka, jezik, brojStranica, izdata);
				knjige.put(knjiga.getId(), knjiga); // dodavanje u kolekciju
			}
		}

		public List<Knjiga> getKnjige() {
			return new ArrayList<>(knjige.values());
		}

	}

	@Override
	public Knjiga findOne(Long id) {
		String sql = 
				"SELECT k.id, k.naziv, k.registarskiBrojPrimerka, k.jezik, k.brojStranica, k.izdata FROM knjige k " + 
				"WHERE k.id = ? " + 
				"ORDER BY k.id";

		KnjigaRowCallBackHandler rowCallbackHandler = new KnjigaRowCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, id);

		return rowCallbackHandler.getKnjige().get(0);
	}

	@Override
	public List<Knjiga> findAll() {
		String sql = 
				"SELECT k.id, k.naziv, k.registarskiBrojPrimerka, k.jezik, k.brojStranica, k.izdata FROM knjige k " + 
				"ORDER BY k.id";

		KnjigaRowCallBackHandler rowCallbackHandler = new KnjigaRowCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler);

		return rowCallbackHandler.getKnjige();
	}

	@Transactional
	@Override
	public int save(Knjiga knjiga) {
		PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				String sql = "INSERT INTO knjige (naziv, registarskiBrojPrimerka, jezik, brojStranica, izdata) VALUES (?, ?, ?, ?, ?)";

				PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				preparedStatement.setString(index++, knjiga.getNaziv());
				preparedStatement.setString(index++, knjiga.getRegistarskiBrojPrimerka());
				preparedStatement.setString(index++, knjiga.getJezik());
				preparedStatement.setInt(index++, knjiga.getBrojStranica());
				preparedStatement.setBoolean(index++, knjiga.isIzdata());

				return preparedStatement;
			}

		};
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		boolean uspeh = jdbcTemplate.update(preparedStatementCreator, keyHolder) == 1;
		return uspeh?1:0;
	}
	
	@Transactional
	@Override
	public int update(Knjiga knjiga) {		
		String sql = "UPDATE knjige SET naziv = ?, registarskiBrojPrimerka = ?, jezik = ?, brojStranica = ?, izdata = ? WHERE id = ?";	
		boolean uspeh = jdbcTemplate.update(sql, knjiga.getNaziv(), knjiga.getRegistarskiBrojPrimerka(), knjiga.getJezik(), knjiga.getBrojStranica(), knjiga.isIzdata(), knjiga.getId()) == 1;
		
		return uspeh?1:0;
	}
	
	@Transactional
	@Override
	public int delete(Long id) {
		String sql = "DELETE FROM knjige WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}
	
}
