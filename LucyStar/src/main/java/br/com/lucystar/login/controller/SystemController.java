package br.com.lucystar.login.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lucystar/api")
public class SystemController {
	
	
	
	@GetMapping("/reset")
	public String resetSystem() {
	  return "OK";	
	}

}
