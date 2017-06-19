package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {

  // Login form
  @RequestMapping("/login")
  public String login() {
    return "login";
  }

}