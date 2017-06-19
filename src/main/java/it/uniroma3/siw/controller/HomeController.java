package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {
  
  //Mostra la index come pagina iniziale dell'app
  @GetMapping("/")
	public String index() {
    return "index";
  }

  //Reindirizzamento alla index
  @RequestMapping("/index.html")
  public String admin() {
    return "index";
  }
  
  //Reindirizzamento alla dashboard
  @GetMapping("/dash")
  public String dashboard() {
	  return "dash";
  }
}