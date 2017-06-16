package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Autore;
import it.uniroma3.siw.model.Opera;

public interface OperaRepository extends CrudRepository<Opera, Long> {
	
	List<Opera> findByTitolo(String titolo);
	List<Opera> findByAutore(Autore autore);
}
