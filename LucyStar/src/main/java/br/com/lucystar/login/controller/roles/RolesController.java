package br.com.lucystar.login.controller.roles;

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
import br.com.lucystar.login.dto.roles.RolesAddDto;
import br.com.lucystar.login.dto.roles.RolesDto;
import br.com.lucystar.login.exceptions.DeleteException;
import br.com.lucystar.login.exceptions.NotFoundException;
import br.com.lucystar.login.service.roles.RolesService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(GeneralController.ROLE_REQUEST_MAPPING)
public class RolesController extends GeneralController<RolesDto, RolesAddDto, String> {

	@Autowired
	private RolesService rolesService;

	@Override
	@PostMapping(GeneralController.ADD)
	public ResponseEntity<RolesDto> add(@Valid @RequestBody RolesAddDto role) throws NotFoundException {
		return new ResponseEntity<RolesDto>(rolesService.addRole(role), HttpStatus.CREATED);
	}

	@Override
	@GetMapping(GeneralController.FIND_ALL)
	public ResponseEntity<List<RolesDto>> findAll(@PathVariable String localSecretKey) {
		return new ResponseEntity<List<RolesDto>>(rolesService.findAll(localSecretKey), HttpStatus.OK);
	}

	@Override
	@PutMapping(GeneralController.UPDATE)
	public ResponseEntity<RolesDto> update(@PathVariable String id,@Valid @RequestBody RolesAddDto role) throws NotFoundException {
		return new ResponseEntity<RolesDto>(rolesService.updateRole(role, id), HttpStatus.OK);
	}

	@Override
	@DeleteMapping(GeneralController.DELETE)
	public ResponseEntity<String> delete(@PathVariable String id) throws DeleteException {
		return new ResponseEntity<String>(rolesService.deleteRole(id), HttpStatus.OK);
	}

	@Override
	@GetMapping(GeneralController.FIND_BY_ID)
	public ResponseEntity<RolesDto> findById(@PathVariable String id) throws NotFoundException {
		return new ResponseEntity<RolesDto>(rolesService.findById(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<RolesDto> findByCode(@PathVariable String code, @PathVariable String key) throws NotFoundException {
		// TODO Auto-generated method stub
		return new ResponseEntity<RolesDto>(rolesService.findByCode(code, key), HttpStatus.OK);
	}
	
	

}
