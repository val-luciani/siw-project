package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// controller to access the login page
@Controller
public class LoginController {

  // Login form
  @RequestMapping("/login")
  public String login() {
    return "login";
  }

  /*Dovrebbe essere superfluo questo pezzo stando alla SecurityConfig
  
  // Login form with error
  @RequestMapping("/error.html")
  public String loginError(Model model) {
    model.addAttribute("loginError", true);
    return "login";
  } */

}