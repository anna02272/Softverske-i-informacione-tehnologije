package com.ftn.PrviMavenVebProjekat.dao;

import java.util.List;

import com.ftn.PrviMavenVebProjekat.model.Knjiga;

public interface KnjigaDAO {
	
	public Knjiga findOne(Long id);

	public List<Knjiga> findAll();

	public int save(Knjiga knjiga);

	public int update(Knjiga knjiga);

	public int delete(Long id);
}
