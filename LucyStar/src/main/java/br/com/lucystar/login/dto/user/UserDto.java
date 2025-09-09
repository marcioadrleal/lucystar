package br.com.lucystar.login.dto.user;

import java.util.List;

import br.com.lucystar.login.dto.roles.GroupDto;
import br.com.lucystar.login.enums.StatuEnum;

public record UserDto(
		String id,
		String name,
		String userName,
		String email,
		StatuEnum status,
		String createdAt,
		String updatedAt,
		String deletedAt,
		List<GroupDto> group
		) {

}
