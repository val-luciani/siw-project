package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// controller to access the login page
@Controller
public class HomeController {

  // Login form
  @RequestMapping("/index.html")
  public String admin() {
    return "index";
  }

  @GetMapping("/dash")
  public String dashboard() {
	  return "dash";
  }
}