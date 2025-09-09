package br.com.lucystar.login.service.users;

import org.springframework.stereotype.Service;

@Service
public class JwtTokenService {

	private static final String SECRET_KEY = "4Z^XrroxR@dWxqf$mTTKwW$!@#qGr4P78fsrFRAS"; // Secret key
	
	private static final String ISSUER = "lucy-star";
}
