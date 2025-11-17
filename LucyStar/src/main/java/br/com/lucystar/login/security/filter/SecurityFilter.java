package br.com.lucystar.login.security.filter;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.lucystar.login.dto.login.WebTokenDto;
import br.com.lucystar.login.entity.users.UserEntity;
import br.com.lucystar.login.exceptions.NotFoundException;
import br.com.lucystar.login.repository.users.UserRepository;
import br.com.lucystar.login.security.config.SecurityConfiguration;
import br.com.lucystar.login.security.details.UserDetail;
import br.com.lucystar.login.security.service.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtTokenService jwtTokenService;
	
	
	@Autowired
	private UserRepository userRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (checkIfEndpointIsNotPublic(request)) {
			String token = recoveryToken(request); // Recupera o token do cabeçalho Authorization da requisição
            if (token != null) {
              WebTokenDto dto =  jwtTokenService.getSubjectFromToken(token);	
				try {
					UserEntity user = userRepository.findByUserName(dto.subject()).orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
					UserDetail detail = new UserDetail(user);
					
					
				} catch (NotFoundException e) {
					throw new IOException(e.getMessage());
				} 
				
            }
		}

		filterChain.doFilter(request, response);

	}

	private boolean checkIfEndpointIsNotPublic(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		return !Arrays.asList(SecurityConfiguration.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).contains(requestURI);
	}

	private String recoveryToken(HttpServletRequest request) {
		String authorizationHeader = request.getHeader("Authorization");
		if (authorizationHeader != null) {
			return authorizationHeader.replace("Bearer ", "");
		}
		return null;
	}

}
