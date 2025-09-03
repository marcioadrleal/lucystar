package br.com.lucystar.login.dto.client;

import br.com.lucystar.login.enums.StatuEnum;
import br.com.lucystar.login.utils.MessagesLocal;
import jakarta.validation.constraints.NotBlank;

public record ClientAddDTO( 
		
		
	    @NotBlank(message = MessagesLocal.VALID_CODE_CLIENT)
		String codeClient , 
		
		
	    @NotBlank(message = MessagesLocal.VALID_NAME_CLIENT)
		String clientName , 
		
		
	//    @NotBlank(message = MessagesLocal.VALID_STATUS_CLIENT)
		StatuEnum status , 
		
		
	    @NotBlank(message = MessagesLocal.VALID_LOCAL_KEY_CLIENT)
		String localKey ) {

}
