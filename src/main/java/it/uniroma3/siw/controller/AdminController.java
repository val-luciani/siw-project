package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// controller to access the login page
@Controller
public class AdminController {

  // Login form
  @RequestMapping("/admin")
  public String admin() {
    return "admin";
  }

}