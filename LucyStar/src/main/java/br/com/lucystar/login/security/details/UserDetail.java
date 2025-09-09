package br.com.lucystar.login.security.details;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.lucystar.login.entity.roles.RolesEntity;
import br.com.lucystar.login.entity.users.UserEntity;

public class UserDetail implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 997174756017065634L;

	private UserEntity user;

	public UserDetail(UserEntity user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getGroups().stream()
		        .flatMap(group -> group.getRoles().stream()) 
		        .map(RolesEntity::getRoleCode)               
		        .distinct()                                  
		        .map( x -> new SimpleGrantedAuthority( x )).collect(Collectors.toList());
		
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUserName();
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

}
