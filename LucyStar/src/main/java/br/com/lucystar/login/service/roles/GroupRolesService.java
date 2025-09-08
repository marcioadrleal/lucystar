package br.com.lucystar.login.service.roles;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lucystar.login.entity.roles.GroupRolesEntity;
import br.com.lucystar.login.entity.roles.GroupsEntity;
import br.com.lucystar.login.entity.roles.RolesEntity;
import br.com.lucystar.login.enums.StatuEnum;
import br.com.lucystar.login.exceptions.HasBeenRegisteredException;
import br.com.lucystar.login.exceptions.NotFoundException;
import br.com.lucystar.login.exceptions.RolesGroupException;
import br.com.lucystar.login.repository.roles.GroupRepository;
import br.com.lucystar.login.repository.roles.GroupRolesRepository;
import br.com.lucystar.login.repository.roles.RolesRepository;
import br.com.lucystar.login.utils.MessagesLocal;
import br.com.lucystar.login.utils.Util;
import jakarta.transaction.Transactional;

@Service
public class GroupRolesService {

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private RolesRepository rolesRepository;

	@Autowired
	private GroupRolesRepository groupRolesRepository;

	@Transactional
	public void addRolesGroup(String idGroup, List<String> idRole)
			throws NotFoundException, HasBeenRegisteredException , RolesGroupException {
		GroupsEntity group = groupRepository.findById(idGroup)
				.orElseThrow(() -> new NotFoundException(MessagesLocal.GROUP_NOT_FOUND));
		List<RolesEntity> role = rolesRepository.findAllById(idRole);
		if ((role == null) || (role.size() <= 0)) {
			throw new NotFoundException(MessagesLocal.ROLE_NOT_FOUND);
		}

		if (idRole.size() != role.size()) {
			throw new NotFoundException(MessagesLocal.ROLE_NOT_FOUND_LIST);
		}
		List<GroupRolesEntity> verify = groupRolesRepository.findGroupRoles(group.getId(), idRole);
		if ((verify != null) && (verify.size() > 0)) {
			throw new HasBeenRegisteredException(MessagesLocal.ROLE_REGISTERED);
		}
		
		
		
		List<GroupRolesEntity> lst = new ArrayList<>();
		role.forEach(rol -> { 
		    if ( rol.getSystemAdmin().getId().equals(group.getClientEntity().getSystemAdmin()) ) {
		      throw new RolesGroupException("It Roles must be from the same admin's group");   	
		    }
		    
		    lst.add(new GroupRolesEntity(
				 Util.generateId(), group, rol, StatuEnum.ACTIVE));
		});
		groupRolesRepository.saveAll(lst);
	}

	@Transactional
	public void deleteRolesGroup(String idGroup, String idRole) throws NotFoundException {
		GroupsEntity group = groupRepository.findById(idGroup)
				.orElseThrow(() -> new NotFoundException(MessagesLocal.GROUP_NOT_FOUND));
		RolesEntity role = rolesRepository.findById(idRole)
				.orElseThrow(() -> new NotFoundException(MessagesLocal.ROLE_NOT_FOUND));
		GroupRolesEntity gre = groupRolesRepository.findGroupRolesUnique(group.getId(), role.getId())
				.orElseThrow(() -> new NotFoundException(MessagesLocal.ROLE_NOT_FOUND));
		groupRolesRepository.delete(gre);
	}

}
