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
import br.com.lucystar.login.dto.roles.GroupAddDto;
import br.com.lucystar.login.dto.roles.GroupDto;
import br.com.lucystar.login.exceptions.DeleteException;
import br.com.lucystar.login.exceptions.NotFoundException;
import br.com.lucystar.login.service.roles.GroupService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(GeneralController.GROUP_REQUEST_MAPPING)
public class GroupController extends GeneralController<GroupDto, GroupAddDto, String>{

	@Autowired
	private GroupService groupService;
	
	@Override
	@PostMapping(GeneralController.ADD)
	public ResponseEntity<GroupDto> add(@Valid @RequestBody GroupAddDto dto) throws NotFoundException {
		return new ResponseEntity<GroupDto>(groupService.addGroup(dto),HttpStatus.CREATED);
	}

	@Override
	@GetMapping(GeneralController.FIND_ALL)
	public ResponseEntity<List<GroupDto>> findAll(@PathVariable String localSecretKey) {
		return new ResponseEntity<List<GroupDto>>(groupService.findAll(localSecretKey),HttpStatus.OK);
	}

	@Override
	@PutMapping(GeneralController.UPDATE)
	public ResponseEntity<GroupDto> update(@PathVariable String id,@Valid @RequestBody GroupAddDto dto) throws NotFoundException {
		return new ResponseEntity<GroupDto>(groupService.updateGroup(dto,id),HttpStatus.OK);
	}

	@Override
	@DeleteMapping(GeneralController.DELETE)
	public ResponseEntity<String> delete(@PathVariable String id) throws DeleteException {
		return new ResponseEntity<String>(groupService.deleteGroup(id),HttpStatus.OK);
	}

	@Override
	@GetMapping(GeneralController.FIND_BY_ID)
	public ResponseEntity<GroupDto> findById(@PathVariable String id) throws NotFoundException {
		return new ResponseEntity<GroupDto>(groupService.findById(id),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<GroupDto> findByCode(@PathVariable String code, @PathVariable String key) throws NotFoundException {
		// TODO Auto-generated method stub
		return new ResponseEntity<GroupDto>(groupService.findByCodeGroup(code, key),HttpStatus.OK);
	}

}
