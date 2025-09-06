package br.com.lucystar.login.service.roles;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lucystar.login.dto.roles.RolesAddDto;
import br.com.lucystar.login.dto.roles.RolesDto;
import br.com.lucystar.login.entity.roles.RolesEntity;
import br.com.lucystar.login.exceptions.NotFoundException;
import br.com.lucystar.login.repository.roles.RolesRepository;
import br.com.lucystar.login.service.system.InitializeSystemService;
import br.com.lucystar.login.utils.MessagesLocal;
import br.com.lucystar.login.utils.Util;
import jakarta.transaction.Transactional;

@Service
public class RolesService {

	@Autowired
	private RolesRepository rolesRepository;

	@Autowired
	private InitializeSystemService initializeSystemService;

	@Transactional
	public RolesDto addRole(RolesAddDto role) throws NotFoundException {
		RolesEntity roleEntity = new RolesEntity();
		roleEntity.setId(Util.generateId());
		roleEntity.setRoleCode(role.roleCode());
		roleEntity.setRoleName(role.roleName());
		roleEntity.setStatus(role.status());
		roleEntity.setSystemAdmin(initializeSystemService.findByLocalKey(role.secretLocalKey()));
		rolesRepository.save(roleEntity);
		return new RolesDto(roleEntity.getId(), roleEntity.getRoleCode(), roleEntity.getRoleName(),
				roleEntity.getStatus(), roleEntity.getSystemAdmin().getLocalSecretKey());
	}

	@Transactional
	public RolesDto updateRole(RolesAddDto role, String id) throws NotFoundException {
		RolesEntity roleEntity = rolesRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(MessagesLocal.ROLE_NOT_FOUND));
		roleEntity.setRoleCode(role.roleCode());
		roleEntity.setRoleName(role.roleName());
		roleEntity.setStatus(role.status());
		roleEntity.setSystemAdmin(initializeSystemService.findByLocalKey(role.secretLocalKey()));
		rolesRepository.save(roleEntity);
		return new RolesDto(roleEntity.getId(), roleEntity.getRoleCode(), roleEntity.getRoleName(),
				roleEntity.getStatus(), roleEntity.getSystemAdmin().getLocalSecretKey());
	}

	public List<RolesDto> findAll(String secretLocalKey) {
		List<RolesDto> lst = new ArrayList<>();
		rolesRepository.findAllRoles(secretLocalKey).forEach(list -> lst.add(new RolesDto(list.getId(),
				list.getRoleCode(), list.getRoleName(), list.getStatus(), list.getSystemAdmin().getLocalSecretKey())));

		return lst;
	}

	public RolesDto findById(String id) throws NotFoundException {
		RolesEntity roleEntity = rolesRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(MessagesLocal.ROLE_NOT_FOUND));
		return new RolesDto(roleEntity.getId(), roleEntity.getRoleCode(), roleEntity.getRoleName(),
				roleEntity.getStatus(), roleEntity.getSystemAdmin().getLocalSecretKey());
	}

	public String deleteRole(String id) {
		rolesRepository.deleteById(id);
		return MessagesLocal.DELETE_SUCCESS;
	}

	public RolesDto findByCode(String code, String key) throws NotFoundException {
		RolesEntity roleEntity = rolesRepository.findByRoleCode(code, key)
				.orElseThrow(() -> new NotFoundException(MessagesLocal.ROLE_NOT_FOUND));
		return new RolesDto(roleEntity.getId(), roleEntity.getRoleCode(), roleEntity.getRoleName(),
				roleEntity.getStatus(), roleEntity.getSystemAdmin().getLocalSecretKey());
	}

}
