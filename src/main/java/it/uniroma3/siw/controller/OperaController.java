package it.uniroma3.siw.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Autore;
import it.uniroma3.siw.model.Opera;
import it.uniroma3.siw.service.OperaService;
import it.uniroma3.siw.service.AutoreService;

@Controller
public class OperaController {
	
	@Autowired
	private OperaService operaService;
	
	@Autowired
	private AutoreService autoreService;
	
	@ModelAttribute("allAuthors")
	public Iterable<Autore> populateAuthors() {
		return this.autoreService.findAll();
	}
	
	//mapping alla form
	@GetMapping("/opera")
	public String showForm(Opera opera) {
		return "addOpera";
	}
	
	//controlla i valori della form e se è tutto ok restituisce "showOpera"
	@PostMapping("/opera")
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
	
	@GetMapping("/deleteOpera")
	public String showGalleria() {
		return "opere";
	}
	
	@PostMapping("/deleteOpera")
	public String deleteOpera(@Valid @ModelAttribute Opera opera) {
		operaService.delete(opera);
		return "opere";
	}
}