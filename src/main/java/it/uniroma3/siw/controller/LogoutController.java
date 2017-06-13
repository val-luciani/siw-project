package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// controller to access the login page
@Controller
public class LogoutController {

  // Login form
  @RequestMapping("/logout")
  public String logOut() {
    return "login";
  }

}