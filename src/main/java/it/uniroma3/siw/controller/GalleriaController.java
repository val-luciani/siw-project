package it.uniroma3.siw.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.exceptions.TemplateProcessingException;

import it.uniroma3.siw.model.Autore;
import it.uniroma3.siw.model.Opera;
import it.uniroma3.siw.service.AutoreService;
import it.uniroma3.siw.service.OperaService;

@Controller
public class GalleriaController {
	
	//Logger
	private static final Logger logger = Logger.getLogger(OperaController.class);
	
	
	@Autowired
	private OperaService operaService;
	
	@Autowired
	private AutoreService autoreService;
	
	@ModelAttribute("allOpere")
	public Iterable<Opera> populateOpere() {
		return this.operaService.findAll();
	}
	
	/*
	@ModelAttribute("autore")
	public Autore populateAutore(Long id){
		return this.autoreService.findById(id);
	} */
	
	@ModelAttribute("allAuthors")
	public Iterable<Autore> populateAuthors() {
		return this.autoreService.findAll();
	}
	
	//mapping alla galleria
	@GetMapping("/galleria")
	public String showGalleria() {
		return "opere";
	}
	
	/*Gestione eccezione: Archivio Opere Vuoto*/
	@ExceptionHandler(SQLGrammarException.class)
	public String handleSQLGrammarException(SQLGrammarException ex, HttpServletRequest request) {
		logger.info("\n\n\n"
				+ "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"
				+ "\n\nECCEZIONE: La tabella richiesta non è ancora stata creata\nErrorMsg: "+ ex.getMessage() +"\n\n"
				+ "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"
				+"\n\n\n");
		return "redirect:/admin?codErr=1";
	}
	
//	/*Fix Bug: mancano autori quindi non trova autore.id per la funzionalità dettagliAutore*/
//	@ExceptionHandler(TemplateProcessingException.class)
//	public String handleTemplateProcessingException(TemplateProcessingException ex, HttpServletRequest request) {
//		logger.info("\n\n\n"
//				+ "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"
//				+ "\n\nECCEZIONE: Sono presenti opere senza autore nel db\n\n"
//				+ "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n\n\n");
//		return "redirect:/admin?codErr=3";
//	}
	
	
	@GetMapping("/autori")
	public String showAutori() {
		return "autori";
	}
}
