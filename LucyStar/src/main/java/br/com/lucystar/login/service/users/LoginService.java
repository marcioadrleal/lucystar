package br.com.lucystar.login.service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lucystar.login.dto.login.LoginDto;
import br.com.lucystar.login.dto.login.TokenDto;

@Service
public class LoginService {

	private static final String SECRET_KEY = "4Z^XrroxR@dWxqf$mTTKwW$!@#qGr4P78fsrFRAS"; // Secret key
	
	@Autowired
	private JwtTokenService jwtTokenService;
	
	public TokenDto authenticateUser(LoginDto dto) {
		
	}
	
}
