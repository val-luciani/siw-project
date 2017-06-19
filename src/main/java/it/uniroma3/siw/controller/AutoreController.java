package it.uniroma3.siw.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import it.uniroma3.siw.service.AutoreService;
import it.uniroma3.siw.service.OperaService;

@Controller
public class AutoreController {
	
	//Logger
	private static final Logger logger = Logger.getLogger(OperaController.class);
	
	@Autowired
	private AutoreService autoreService;
	
	@Autowired
	private OperaService operaService;
	
	@ModelAttribute("allAuthors")
	public Iterable<Autore> populateAuthors() {
		return this.autoreService.findAll();
	}
	
	//Mapping alla form per aggiungere un autore
	@GetMapping("/autore")
	public String showForm(Autore autore) {
		return "addAutore";
	}
	
	//Controlla i valori della form e se è tutto ok restituisce "showAutore"
	@PostMapping("/autore")
    public String checkAutoreInfo(@Valid @ModelAttribute Autore autore, 
    									BindingResult bindingResult,
    									Model model,
    									@RequestParam("data_nascita") String dataDiNascita,
    									@RequestParam("data_morte") String dataDiMorte) {
		
		Date dataNascita, dataMorte;
		
		try {
			dataNascita = new SimpleDateFormat("yyyy-MM-dd").parse(dataDiNascita);
			dataMorte = new SimpleDateFormat("yyyy-MM-dd").parse(dataDiMorte);
		} catch(Exception e) {
			dataNascita = null;
			dataMorte = null;
		}
    	
        if (bindingResult.hasErrors()) {
            return "addAutore";
        }
        else {
        	autore.setDataDiNascita(dataNascita);
        	autore.setDataDiMorte(dataMorte);
        	
        	model.addAttribute(autore);
            autoreService.add(autore);
        }
        return "showAutore";
    }
	
	//Reindirizza a pagina eliminazione autore
	@RequestMapping(value = "deleteAutore/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable long id) {
		autoreService.delete(id);
		return new ModelAndView("redirect:/autori");
	}
	
	//Reindirizza a pagina modifica autore
	@RequestMapping(value = "updateAutore/{id}", method = RequestMethod.GET)
	public String update(@PathVariable long id,
						Model model) {
		Autore autore = autoreService.findById(id);
		model.addAttribute("autore", autore);
		return "editAutore";
	}
	
	//Modifica autore
	@RequestMapping(value = "/updateAutore", method = RequestMethod.POST)
	public ModelAndView updateAutore(@RequestParam("autore_id") long id, 
									@RequestParam("autore_nome") String nome,
									@RequestParam("autore_cognome") String cognome,
									@RequestParam("autore_nazionalita") String nazionalita,
									@RequestParam("data_nascita") String dataDiNascita,
									@RequestParam("data_morte") String dataDiMorte) {
		
		Date dataNascita, dataMorte;
		
		try {
			dataNascita = new SimpleDateFormat("yyyy-MM-dd").parse(dataDiNascita);
			dataMorte = new SimpleDateFormat("yyyy-MM-dd").parse(dataDiMorte);
		} catch(Exception e) {
			dataNascita = null;
			dataMorte = null;
		}
		
		Autore autore = autoreService.findById(id);
		
		autore.setNome(nome);
		autore.setCognome(cognome);
		autore.setNazionalita(nazionalita);
		autore.setDataDiNascita(dataNascita);
		autore.setDataDiMorte(dataMorte);
		
		autoreService.save(autore);
		
		return new ModelAndView("redirect:/autori");
	}
	
	//Reindirizza alla pagina che mostra le opere di un determinato autore
	@RequestMapping(value = "opereAutore/{id}", method = RequestMethod.GET)
	public String opereByAutore(@PathVariable long id,
						Model model) {
		Autore autore = autoreService.findById(id);
		List<Opera> opere = operaService.findByAutore(autore);
		
		model.addAttribute("autore", autore);
		model.addAttribute("opereByAutore", opere);
		return "opereByAutore";
	}
	
	//Reindirizza alla pagina che mostra i dettagli di un autore
	@RequestMapping(value = "showAutore/{id}", method = RequestMethod.GET)
	public String showAutore(@PathVariable long id,
						Model model) {
		
		Autore autore = autoreService.findById(id);
		
		model.addAttribute("autore", autore);
		return "showAutore";
	}
	
	
	//Gestione eccezione: Archivio Opere Vuoto
	@ExceptionHandler(SQLGrammarException.class)
	public String handleSQLGrammarException(SQLGrammarException ex, HttpServletRequest request) {
		logger.info("\n\n\n"
				+ "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"
				+ "\n\nECCEZIONE: La tabella richiesta non è ancoa stata creata\nErrorMsg: "+ ex.getMessage() +"\n\n"
				+ "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"
				+"\n\n\n");
		return "redirect:./admin?codErr=1";
	}
	
	//Gestisce eccezione record duplicati
	@ExceptionHandler(DataIntegrityViolationException.class)
	public String handleConstrainViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {
		logger.info("\n\n\n"
				+ "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"
				+ "\n\nECCEZIONE: L'inserzione violerebbe i vincoli del modello\nErrorMsg:"+ ex.getMessage() +"\n\n"
				+ "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"
				+"\n\n\n");
		return "redirect:./autore?codErr=2";
	}
}