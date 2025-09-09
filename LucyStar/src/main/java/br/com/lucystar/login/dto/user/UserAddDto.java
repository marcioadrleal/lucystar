package br.com.lucystar.login.dto.user;

import java.util.List;

import br.com.lucystar.login.enums.StatuEnum;
import br.com.lucystar.login.utils.MessagesLocal;
import jakarta.validation.constraints.NotBlank;

public record UserAddDto(
		
		@NotBlank(message = MessagesLocal.VALID_NAME_USER)
		String name,
		
		@NotBlank(message = MessagesLocal.VALID_USER_NAME)
		String login,
		
		@NotBlank(message = MessagesLocal.VALID_USER_EMAIL)
		String email,
		
		StatuEnum status,
		
		@NotBlank(message = MessagesLocal.VALID_PASSWORD)
		String password ,
		
		List<String> group
		) {

}
