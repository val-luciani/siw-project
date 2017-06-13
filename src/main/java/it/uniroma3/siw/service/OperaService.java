package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	public Opera findById(Long id){
		return this.operaRepository.findOne(id);
	}
	
	@Transactional
	public void delete(final Opera opera){
		this.operaRepository.delete(opera);
	}
}
