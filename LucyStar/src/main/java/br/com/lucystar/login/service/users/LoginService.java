package br.com.lucystar.login.service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.lucystar.login.dto.login.LoginDto;
import br.com.lucystar.login.dto.login.TokenDto;
import br.com.lucystar.login.security.details.UserDetail;
import br.com.lucystar.login.security.service.JwtTokenService;

@Service
public class LoginService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenService jwtTokenService;

	public TokenDto authenticateUser(LoginDto dto) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				dto.userName() == null ? dto.email() : dto.userName(), dto.password());
		Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		UserDetail user = (UserDetail) authentication.getPrincipal();
		return jwtTokenService.generateToken(user);
	}

}
