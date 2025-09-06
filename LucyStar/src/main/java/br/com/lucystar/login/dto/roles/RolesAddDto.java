package br.com.lucystar.login.dto.roles;

import br.com.lucystar.login.enums.StatuEnum;
import br.com.lucystar.login.utils.MessagesLocal;
import jakarta.validation.constraints.NotBlank;

public record RolesAddDto(

		@NotBlank(message = MessagesLocal.VALID_CODE_ROLE) String roleCode,

		@NotBlank(message = MessagesLocal.VALID_NAME_ROLE) String roleName,

		StatuEnum status,

		@NotBlank(message = MessagesLocal.VALID_LOCAL_KEY_ROLE) String secretLocalKey

) {

}
