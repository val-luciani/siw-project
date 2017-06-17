package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Autore;
import it.uniroma3.siw.model.Opera;
import it.uniroma3.siw.repository.OperaRepository;

@Service
public class OperaService {
	
	@Autowired
	private OperaRepository operaRepository;
	
	public Iterable<Opera> findAll(){
		return this.operaRepository.findAll();
	}
	
	@Transactional
	public void add(final Opera opera){
		this.operaRepository.save(opera);
	}
	
	public Opera findById(long id){
		return this.operaRepository.findOne(id);
	}
	
	public List<Opera> findByAutore(Autore autore){
		return this.operaRepository.findByAutore(autore);
	}
	
	@Transactional
	public void save(Opera opera){
		this.operaRepository.save(opera);
	}
	
	@Transactional
	public void delete(final Opera opera){
		this.operaRepository.delete(opera);
	}
	
	@Transactional
	public void delete(long id){
		this.operaRepository.delete(id);
	}

	public List<Opera> findByTecnica(String tecnica) {
		return this.operaRepository.findByTecnica(tecnica);
	}
	
	
}
