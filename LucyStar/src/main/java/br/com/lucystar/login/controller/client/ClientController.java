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

import br.com.lucystar.login.controller.GeneralController;
import br.com.lucystar.login.dto.client.ClientAddDTO;
import br.com.lucystar.login.dto.client.ClientDto;
import br.com.lucystar.login.exceptions.DeleteException;
import br.com.lucystar.login.exceptions.NotFoundException;
import br.com.lucystar.login.service.client.ClientService;
import br.com.lucystar.login.utils.MessagesLocal;
import jakarta.validation.Valid;

@RestController
@RequestMapping(GeneralController.CLIENT_REQUEST_MAPPING)
public class ClientController extends GeneralController<ClientDto, ClientAddDTO, String> {

	@Autowired
	private ClientService clientService;

	@Override
	@PostMapping(GeneralController.ADD)
	public ResponseEntity<ClientDto> add(@Valid @RequestBody ClientAddDTO client) throws NotFoundException {
		return new ResponseEntity<ClientDto>(clientService.addClient(client), HttpStatus.CREATED);
	}

	@Override
	@GetMapping(GeneralController.FIND_ALL)
	public ResponseEntity<List<ClientDto>> findAll(@PathVariable String localSecretKey) {
		return new ResponseEntity<List<ClientDto>>(clientService.findAll(localSecretKey), HttpStatus.OK);
	}

	@Override
	@PutMapping(GeneralController.UPDATE)
	public ResponseEntity<ClientDto> update(@PathVariable String id, @Valid @RequestBody ClientAddDTO client)
			throws NotFoundException {
		return new ResponseEntity<ClientDto>(clientService.updateClient(client, id), HttpStatus.CREATED);
	}

	@Override
	@DeleteMapping(GeneralController.DELETE)
	public ResponseEntity<String> delete(@PathVariable String id) throws DeleteException {
		clientService.deleteClient(id);
		return new ResponseEntity<String>(MessagesLocal.DELETE_SUCCESS, HttpStatus.CREATED);
	}

	@Override
	@GetMapping(GeneralController.FIND_BY_ID)
	public ResponseEntity<ClientDto> findById(@PathVariable String id) throws NotFoundException {
		return new ResponseEntity<ClientDto>(clientService.findById(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ClientDto> findByCode(String code, String key) throws NotFoundException {
		return new ResponseEntity<ClientDto>(clientService.findByCodeClient(code, key), HttpStatus.OK);
	}

}
