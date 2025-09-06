package br.com.lucystar.login.dto.roles;

import br.com.lucystar.login.enums.StatuEnum;

public record RolesDto(
		
		 String id ,
		 String roleCode ,
		 String roleName ,
		 StatuEnum status ,
		 String secretLocalKey 
		
		
		) {

}
