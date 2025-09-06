package br.com.lucystar.login.dto.roles;

import java.util.List;

import br.com.lucystar.login.enums.StatuEnum;

public record GroupDto(
		
		String id ,
		String groupCode,
		String groupName,
		StatuEnum status,
		String  clientId,
		String clientCode,
		String clientName,
		List<RolesDto> roles
		
		) {

}
