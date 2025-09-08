package br.com.lucystar.login.controller.roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucystar.login.controller.GeneralController;
import br.com.lucystar.login.dto.roles.RolesArrayDto;
import br.com.lucystar.login.exceptions.HasBeenRegisteredException;
import br.com.lucystar.login.exceptions.NotFoundException;
import br.com.lucystar.login.service.roles.GroupRolesService;
import br.com.lucystar.login.utils.MessagesLocal;

@RestController
@RequestMapping(GeneralController.GROUP_ROLES_REQUEST_MAPPING)
public class GroupRoleController {
  
	@Autowired
	private GroupRolesService groupRoleService;
	
	@PostMapping(GeneralController.ADD_GROUP_ROLE)
	public ResponseEntity<String> add( @PathVariable String idgroup , @RequestBody RolesArrayDto rolesGroup ) throws NotFoundException, HasBeenRegisteredException{
	  groupRoleService.addRolesGroup(idgroup, rolesGroup.roles());	
	  return new ResponseEntity<String>(MessagesLocal.SUCCESS , HttpStatus.CREATED );  	
	}
	
	@DeleteMapping(GeneralController.DELETE_GROUP_ROLE)
	public ResponseEntity<String> remove( @PathVariable String idgroup , @PathVariable String idRole ) throws NotFoundException, HasBeenRegisteredException{
	  groupRoleService.deleteRolesGroup(idgroup, idRole);	
	  return new ResponseEntity<String>(MessagesLocal.SUCCESS , HttpStatus.OK );  	
	}
	
	
	
}
