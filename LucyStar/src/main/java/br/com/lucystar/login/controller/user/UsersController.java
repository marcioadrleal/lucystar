package br.com.lucystar.login.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucystar.login.controller.GeneralController;
import br.com.lucystar.login.dto.user.UserAddDto;
import br.com.lucystar.login.dto.user.UserDto;
import br.com.lucystar.login.exceptions.NotFoundException;
import br.com.lucystar.login.service.users.UserService;

@RestController
@RequestMapping(GeneralController.GROUP_USERS_REQUEST_MAPPING)
public class UsersController {

	@Autowired
	private UserService userService;

	@PostMapping(GeneralController.ADD)
	public ResponseEntity<UserDto> add(@RequestBody UserAddDto dto) throws NotFoundException {
		return new ResponseEntity<UserDto>(userService.add(dto), HttpStatus.CREATED);
	}

	@PutMapping(GeneralController.UPDATE)
	public ResponseEntity<UserDto> update(@PathVariable String id, @RequestBody UserAddDto dto) throws NotFoundException {
		return new ResponseEntity<UserDto>(userService.update(id , dto), HttpStatus.CREATED);
	}

	@GetMapping(GeneralController.FIND_BY_ID)
	public ResponseEntity<UserDto> findById(@PathVariable String id) throws NotFoundException {
		return new ResponseEntity<UserDto>(userService.findById(id),HttpStatus.OK);
	}

	

}
