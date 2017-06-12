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
import it.uniroma3.siw.service.AutoreService;

@Controller
public class AutoreController {
	
	@Autowired
	private AutoreService autoreService;
	
	//mapping dalla index alla form
	@GetMapping("/autore")
	public String showForm(Autore autore) {
		return "addAutore";
	}
	
	//controlla i valori della form e se è tutto ok restituisce "results"
	@PostMapping("/autore")
    public String checkOperaInfo(@Valid @ModelAttribute Autore autore, 
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
}