package br.com.lucystar.login.dto.roles;

import br.com.lucystar.login.enums.StatuEnum;
import br.com.lucystar.login.utils.MessagesLocal;
import jakarta.validation.constraints.NotBlank;

public record GroupAddDto(

		@NotBlank(message = MessagesLocal.VALID_CODE_GROUP)
		String groupCode,
		
		@NotBlank(message = MessagesLocal.VALID_NAME_GROUP)
		String groupName,
		
		StatuEnum status,
		
		@NotBlank(message = MessagesLocal.VALID_LOCAL_KEY_GROUP)
		String  clientId
		) {

}
