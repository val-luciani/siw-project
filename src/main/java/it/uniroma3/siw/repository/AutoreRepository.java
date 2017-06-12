package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.*;

import it.uniroma3.siw.model.Autore;

public interface AutoreRepository extends CrudRepository<Autore, Long> {
	
	List<Autore> findByNome(String nome);
	List<Autore> findByCognome(String cognome);
}
