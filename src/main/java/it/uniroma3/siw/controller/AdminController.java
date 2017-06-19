package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AdminController {

  //Admin page
  @RequestMapping("/admin")
  public String admin() {
    return "admin";
  }

}