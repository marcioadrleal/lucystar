package br.com.lucystar.login.security.service;


import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.lucystar.login.dto.login.TokenDto;
import br.com.lucystar.login.dto.login.WebTokenDto;
import br.com.lucystar.login.entity.roles.RolesEntity;
import br.com.lucystar.login.security.details.UserDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtTokenService {

	private static final String SECRET_KEY = "4Z^XrroxR@dWxqf$mTTKwW$!@#qGr4P78fsrFRAS"; // Secret key

	private static final String ISSUER = "lucy-star";

	public TokenDto generateToken(UserDetail user) {

		Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
		String[] roles = user.getUser().getGroups().stream().flatMap(group -> group.getRoles().stream())
				.map(RolesEntity::getRoleCode).distinct().toArray(String[]::new);

		String token = JWT.create().withIssuer(ISSUER) // Define o emissor do token
				.withIssuedAt(creationDate()) // Define a data de emissão do token
				.withExpiresAt(expirationDate()) // Define a data de expiração do token
				.withClaim("name", user.getUser().getName())
				.withSubject(user.getUsername()).withArrayClaim("role", roles).sign(algorithm);

		return new TokenDto(token);

	}
	
	public WebTokenDto getSubjectFromToken(String token) {
        
		try {
            // Cria a key HMAC-SHA a partir da string
            byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
            Key key = Keys.hmacShaKeyFor(keyBytes);

            // Parse e valida assinatura / exp automaticamente
            Jws<Claims> jws = Jwts.parserBuilder()
                                  .setSigningKey(key)
                                  .build()
                                  .parseClaimsJws(token);

            Claims claims = jws.getBody();
            String name = claims.get("name", String.class);
            String sub = claims.getSubject();
            // papel/roles pode vir como List<?> — convertendo para List<String>
            Object rolesObj = claims.get("role");
            List<String> roles = null;
            if (rolesObj instanceof List) {
                roles = ((List<?>) rolesObj).stream()
                        .map(Object::toString)
                        .collect(Collectors.toList());
            }
            return new WebTokenDto(name, sub, roles);
		}catch (Exception e) {
			return null;
		}
		
    }

	private Instant creationDate() {
		return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toInstant();
	}

	private Instant expirationDate() {
		return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).plusHours(1).toInstant();
	}

}
