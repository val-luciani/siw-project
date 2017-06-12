package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import it.uniroma3.siw.model.Opera;
import it.uniroma3.siw.service.OperaService;

@Controller
public class GalleriaController {
	
	@Autowired
	private OperaService operaService;
	
	@ModelAttribute("allOpere")
	public Iterable<Opera> populateOpere() {
		return this.operaService.findAll();
	}
	
	//mapping alla galleria
		@GetMapping("/galleria")
		public String showGalleria() {
			return "opere";
		}
}
