package br.com.lucystar.login.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.lucystar.login.exceptions.DeleteException;
import br.com.lucystar.login.exceptions.NotFoundException;

public abstract class GeneralController<T,U,Y> {
	
	public static final String SYSTEM_REQUEST_MAPPING = "/lucystar/api/system";
	public static final String CLIENT_REQUEST_MAPPING = "/lucystar/api/client";
	public static final String ROLE_REQUEST_MAPPING = "/lucystar/api/role";
	public static final String GROUP_REQUEST_MAPPING = "/lucystar/api/group";
	public static final String GROUP_ROLES_REQUEST_MAPPING = "/lucystar/api/rolegroup";
	public static final String GROUP_USERS_REQUEST_MAPPING = "/lucystar/api/user";
	
	public static final String ADD = "/add";
	
	public static final String ADD_GROUP_ROLE = "/add/{idgroup}";
	
	public static final String FIND_ALL = "/all/{localSecretKey}";
	
	public static final String UPDATE = "/update/{id}";
	
	public static final String DELETE = "/{id}";
	
	public static final String DELETE_GROUP_ROLE = "/{idGroup}/{idRole}";
	
	public static final String FIND_BY_ID = "/id/{id}";
	
	public static final String FIND_BY_CODE = "/{code}/{key}";
	
	public static final String LOGIN = "/login";
	
	public abstract ResponseEntity<T> add( U dto ) throws NotFoundException;
	
	public abstract ResponseEntity<List<T>> findAll(String localSecretKey);
	
	public abstract ResponseEntity<T> update(Y id  ,U dto ) throws NotFoundException;

	public abstract ResponseEntity<String> delete( Y id ) throws DeleteException;
	
	public abstract ResponseEntity<T> findById(Y id) throws NotFoundException;
	
	public abstract ResponseEntity<T> findByCode( String code , String key) throws NotFoundException;
	
}
