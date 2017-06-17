package it.uniroma3.siw.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.uniroma3.siw.model.Autore;
import it.uniroma3.siw.model.Opera;
import it.uniroma3.siw.service.OperaService;
import it.uniroma3.siw.service.AutoreService;

@Controller
public class OperaController {
	
	//Logger
	private static final Logger logger = Logger.getLogger(OperaController.class);
	
	@Autowired
	private OperaService operaService;
	
	@Autowired
	private AutoreService autoreService;
	
	
	@ModelAttribute("allAuthors")
	public Iterable<Autore> populateAuthors() {
 		return this.autoreService.findAll();
 	}
	
	/*Inserisci nuova opera*/
	@RequestMapping(value = "/opera", method = RequestMethod.GET)
	public String showForm(Opera opera, HttpServletRequest request) {
		return "addOpera";
	}

	//controlla i valori della form e se è tutto ok restituisce "showOpera"
	@RequestMapping( value = "/opera", method = RequestMethod.POST)
    public String checkOperaInfo(@Valid @ModelAttribute Opera opera, 
    									BindingResult bindingResult, Model model) {
    	
        if (bindingResult.hasErrors()) {
            return "addOpera";
        }
        else {
        	model.addAttribute(opera);
            operaService.add(opera);
        }
        return "showOpera";
    }
	
	/*Gestisce eccezione record duplicati*/
	@ExceptionHandler(DataIntegrityViolationException.class)
	public String handleConstrainViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {
		logger.info("\n\n\n"
				+ "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"
				+ "\n\nECCEZIONE: L'inserzione violerebbe i vincoli del modello\nErrorMsg:"+ ex.getMessage() +"\n\n"
				+ "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"
				+"\n\n\n");
		return "redirect:/opera?codErr=2";
	}
	
	/*Gestione eccezione: Archivio Opere Vuoto*/
	@ExceptionHandler(SQLGrammarException.class)
	public String handleSQLGrammarException(SQLGrammarException ex, HttpServletRequest request) {
		logger.info("\n\n\n"
				+ "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"
				+ "\n\nECCEZIONE: La tabella richiesta non è ancoa stata creata\nErrorMsg: "+ ex.getMessage() +"\n\n"
				+ "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"
				+"\n\n\n");
		return "redirect:/admin?codErr=1";
	}
	
	/*Rimuovi opera con un certo ID*/
	@RequestMapping(value = "deleteOpera/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable long id) {
		operaService.delete(id);
		return new ModelAndView("redirect:/galleria");
	}
	
	/*Aggiorna dati opera*/
	@RequestMapping(value = "/updateOpera", method = RequestMethod.POST)
	public ModelAndView updateOpera(@RequestParam("opera_id") long id, 
									@RequestParam("opera_titolo") String titolo,
									@RequestParam("opera_anno") Integer anno,
									@RequestParam("opera_tecnica") String tecnica,
									@RequestParam("opera_dimensioni") String dimensioni,
									@RequestParam("opera_autore") Autore autore) {
		
		Opera opera = operaService.findById(id);
		opera.setAnno(anno);
		opera.setAutore(autore);
		opera.setDimensioni(dimensioni);
		opera.setTecnica(tecnica);
		opera.setTitolo(titolo);
		
		operaService.save(opera);
		
		return new ModelAndView("redirect:/galleria");
	}

	/*Aggiorna dati opera avente ID*/
	@RequestMapping(value = "updateOpera/{id}", method = RequestMethod.GET)
	public String update(@PathVariable long id,
						Model model) {
		Opera opera = operaService.findById(id);
		model.addAttribute("opera", opera);
		return "editOpera";
	}
	
	/* Mostra le opere con una determinata tecnica (cliccando sulla tecnica nella lista opere) */
	@RequestMapping(value = "opereTecnica/{id}", method = RequestMethod.GET)
	public String opereByTecnica(@PathVariable long id,
						Model model) {
		
		Opera opera = operaService.findById(id);
		String tecnica = opera.getTecnica();
		List<Opera> opere = operaService.findByTecnica(tecnica);
		
		model.addAttribute("opera_tec", opera);
		model.addAttribute("opereByTecnica", opere);
		return "opereByTecnica";
	}

}