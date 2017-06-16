package it.uniroma3.siw.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	
	@Autowired
	private AutoreService autoreService;
	
	@Autowired
	private OperaService operaService;
	
	@ModelAttribute("allAuthors")
	public Iterable<Autore> populateAuthors() {
		return this.autoreService.findAll();
	}
	
	//mapping index alla form per aggiungere un autore
	@GetMapping("/autore")
	public String showForm(Autore autore) {
		return "addAutore";
	}
	
	//controlla i valori della form e se è tutto ok restituisce "showAutore"
	@PostMapping("/autore")
    public String checkAutoreInfo(@Valid @ModelAttribute Autore autore, 
    									BindingResult bindingResult, Model model) {
    	
        if (bindingResult.hasErrors()) {
            return "addAutore";
        }
        else {
        	model.addAttribute(autore);
            autoreService.add(autore);
        }
        return "showAutore";
    }
	
	@RequestMapping(value = "deleteAutore/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable long id) {
		autoreService.delete(id);
		return new ModelAndView("redirect:/autori");
	}
	
	@RequestMapping(value = "updateAutore/{id}", method = RequestMethod.GET)
	public String update(@PathVariable long id,
						Model model) {
		Autore autore = autoreService.findById(id);
		model.addAttribute("autore", autore);
		return "editAutore";
	}
	
	@RequestMapping(value = "/updateAutore", method = RequestMethod.POST)
	public ModelAndView updateAutore(@RequestParam("autore_id") long id, 
									@RequestParam("autore_nome") String nome,
									@RequestParam("autore_cognome") String cognome,
									@RequestParam("autore_nazionalita") String nazionalita) {
		
		Autore autore = autoreService.findById(id);
		autore.setNome(nome);
		autore.setCognome(cognome);
		autore.setNazionalita(nazionalita);
		
		autoreService.save(autore);
		
		return new ModelAndView("redirect:/autori");
	}
	
	//metodo opereAutore
	@RequestMapping(value = "opereAutore/{id}", method = RequestMethod.GET)
	public String opere(@PathVariable long id,
						Model model) {
		Autore autore = autoreService.findById(id);
		List<Opera> opere = operaService.findByAutore(autore);
		
		model.addAttribute("autore", autore);
		model.addAttribute("opereByAutore", opere);
		return "opereByAutore";
	}
	
	@RequestMapping(value = "showAutore/{id}", method = RequestMethod.GET)
	public String showAutore(@PathVariable long id,
						Model model) {
		
		Autore autore = autoreService.findById(id);
		
		model.addAttribute("autore", autore);
		return "showAutore";
	}
}