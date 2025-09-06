package br.com.lucystar.login.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucystar.login.controller.GeneralController;
import br.com.lucystar.login.dto.system.InitializeSystemDTO;
import br.com.lucystar.login.exceptions.ResetSystemException;
import br.com.lucystar.login.service.system.InitializeSystemService;
import br.com.lucystar.login.utils.MessagesLocal;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping(GeneralController.SYSTEM_REQUEST_MAPPING)
@Validated
public class SystemController {
	
	
	@Autowired
	private InitializeSystemService initialService;
	
	@GetMapping("/reset/{email}")
	public ResponseEntity<InitializeSystemDTO> resetSystem(
			
			@PathVariable
			@NotBlank(message = MessagesLocal.VALID_SYSTEM_ADMIN_EMAIL)
			@Email(message = MessagesLocal.VALID_SYSTEM_ADMIN_EMAIL_FORMAT)
			String email) throws ResetSystemException {
       return new ResponseEntity<InitializeSystemDTO>( initialService.resetSystem(email) , HttpStatus.CREATED );
	}
	
	

}
