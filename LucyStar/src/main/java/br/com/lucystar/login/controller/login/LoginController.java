package br.com.lucystar.login.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucystar.login.controller.GeneralController;
import br.com.lucystar.login.dto.login.LoginDto;
import br.com.lucystar.login.dto.login.TokenDto;
import br.com.lucystar.login.service.users.LoginService;

@RestController
@RequestMapping(GeneralController.INITIAL_PATH)
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping(GeneralController.LOGIN)
	public ResponseEntity<TokenDto> generateToken(@RequestBody LoginDto login ){
	  return new ResponseEntity<TokenDto>(loginService.authenticateUser(login),HttpStatus.OK);	
		
	}

}
