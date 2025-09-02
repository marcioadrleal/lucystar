package br.com.lucystar.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucystar.login.dto.InitializeSystemDTO;
import br.com.lucystar.login.exceptions.ResetSystemException;
import br.com.lucystar.login.service.InitializeSystemService;

@RestController
@RequestMapping("/lucystar/api")
public class SystemController {
	
	
	@Autowired
	private InitializeSystemService initialService;
	
	
	@GetMapping("/reset")
	public ResponseEntity<InitializeSystemDTO> resetSystem() throws ResetSystemException {
       return initialService.resetSystem();
	}

}
