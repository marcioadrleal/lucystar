package br.com.lucystar.login.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucystar.login.dto.client.ClientAddDTO;
import br.com.lucystar.login.dto.client.ClientDto;
import br.com.lucystar.login.exceptions.DeleteException;
import br.com.lucystar.login.exceptions.NotFoundException;
import br.com.lucystar.login.service.client.ClientService;
import br.com.lucystar.login.utils.MessagesLocal;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/lucystar/api/client")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	
	@PostMapping("/add")
	public ResponseEntity<ClientDto> addClient(@Valid @RequestBody ClientAddDTO client ) throws NotFoundException {
	  return new ResponseEntity<ClientDto>(clientService.addClient(client),HttpStatus.CREATED); 	
	}
	
	@GetMapping("{localSecretKey}")
	public ResponseEntity<List<ClientDto>> findAllClient(@PathVariable String localSecretKey){
	  return new ResponseEntity<List<ClientDto>>(clientService.findAll(localSecretKey),HttpStatus.OK);	
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ClientDto> updateClient(@PathVariable String id  ,@Valid @RequestBody ClientAddDTO client ) throws NotFoundException {
	  return new ResponseEntity<ClientDto>(clientService.updateClient(client,id),HttpStatus.CREATED); 	
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> updateClient( @PathVariable String id ) throws DeleteException {
	  clientService.deleteClient(id);	
	  return new ResponseEntity<String>(MessagesLocal.DELETE_SUCCESS,HttpStatus.CREATED); 	
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<ClientDto> findById(@PathVariable String id) throws NotFoundException{
	  return new ResponseEntity<ClientDto>(clientService.findById(id),HttpStatus.OK);	
	}
	
	
	

}
