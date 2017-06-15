package it.uniroma3.siw.controller;

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
import org.springframework.web.servlet.ModelAndView;

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
	
	@RequestMapping(value = "deleteOpera/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable long id) {
		operaService.delete(id);
		return new ModelAndView("redirect:/galleria");
	}

}