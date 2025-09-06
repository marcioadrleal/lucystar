package br.com.lucystar.login.service.roles;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lucystar.login.dto.roles.GroupAddDto;
import br.com.lucystar.login.dto.roles.GroupDto;
import br.com.lucystar.login.dto.roles.RolesAddDto;
import br.com.lucystar.login.dto.roles.RolesDto;
import br.com.lucystar.login.entity.roles.GroupsEntity;
import br.com.lucystar.login.entity.roles.RolesEntity;
import br.com.lucystar.login.exceptions.NotFoundException;
import br.com.lucystar.login.repository.roles.GroupRepository;
import br.com.lucystar.login.service.client.ClientService;
import br.com.lucystar.login.utils.MessagesLocal;
import br.com.lucystar.login.utils.Util;
import jakarta.transaction.Transactional;

@Service
public class GroupService {

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private ClientService clientService;

	@Transactional
	public GroupDto addGroup(GroupAddDto group) throws NotFoundException {
		GroupsEntity groupEntity = new GroupsEntity();
		groupEntity.setId(Util.generateId());
		groupEntity.setGroupCode(group.groupCode());
		groupEntity.setGroupName(group.groupName());
		groupEntity.setStatus(group.status());
		groupEntity.setClientEntity(clientService.findByIdEntity(group.clientId()));
		groupRepository.save(groupEntity);
		return new GroupDto(groupEntity.getId(), groupEntity.getGroupCode(), groupEntity.getGroupName(),
				groupEntity.getStatus(), groupEntity.getClientEntity().getId(),
				groupEntity.getClientEntity().getCodeClient(), groupEntity.getClientEntity().getNameClient(),
				listRolesDto(groupEntity.getRoles()));
	}

	@Transactional
	public GroupDto updateGroup(GroupAddDto group, String id) throws NotFoundException {
		GroupsEntity groupEntity = groupRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(MessagesLocal.GROUP_NOT_FOUND));
		groupEntity.setGroupCode(group.groupCode());
		groupEntity.setGroupName(group.groupName());
		groupEntity.setStatus(group.status());
		groupEntity.setClientEntity(clientService.findByIdEntity(group.clientId()));
		groupRepository.save(groupEntity);
		return new GroupDto(groupEntity.getId(), groupEntity.getGroupCode(), groupEntity.getGroupName(),
				groupEntity.getStatus(), groupEntity.getClientEntity().getId(),
				groupEntity.getClientEntity().getCodeClient(), groupEntity.getClientEntity().getNameClient(),
				listRolesDto(groupEntity.getRoles()));
	}

	private List<RolesDto> listRolesDto(Set<RolesEntity> roles) {
		List<RolesDto> list = new ArrayList<>();
		if (roles != null) {
			roles.forEach(lst -> list.add(new RolesDto(lst.getId(), lst.getRoleCode(), lst.getRoleName(),
					lst.getStatus(), lst.getSystemAdmin().getLocalSecretKey()))

			);
		}
		return list;
	}

	public List<GroupDto> findAll(String clientId) {
		List<GroupDto> lst = new ArrayList<>();
		groupRepository.findAllGroup(clientId).forEach(list ->

		lst.add(new GroupDto(list.getId(), list.getGroupCode(), list.getGroupName(), list.getStatus(),
				list.getClientEntity().getId(), list.getClientEntity().getCodeClient(),
				list.getClientEntity().getNameClient(), listRolesDto(list.getRoles()))));

		return lst;
	}

	public GroupDto findByCodeGroup(String groupCode, String clientId) throws NotFoundException {
		GroupsEntity groupEntity = groupRepository.findByCodeGroup(groupCode,clientId)
				.orElseThrow(() -> new NotFoundException(MessagesLocal.GROUP_NOT_FOUND));
		return new GroupDto(groupEntity.getId(), groupEntity.getGroupCode(), groupEntity.getGroupName(), groupEntity.getStatus(),
				groupEntity.getClientEntity().getId(), groupEntity.getClientEntity().getCodeClient(),
				groupEntity.getClientEntity().getNameClient(), listRolesDto(groupEntity.getRoles()));
	}

	public GroupDto findById(String id) throws NotFoundException {
		GroupsEntity groupEntity = groupRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(MessagesLocal.ROLE_NOT_FOUND));
		return new GroupDto(groupEntity.getId(), groupEntity.getGroupCode(), groupEntity.getGroupName(), groupEntity.getStatus(),
				groupEntity.getClientEntity().getId(), groupEntity.getClientEntity().getCodeClient(),
				groupEntity.getClientEntity().getNameClient(), listRolesDto(groupEntity.getRoles()));
	}

	public String deleteGroup(String id) {
		groupRepository.deleteById(id);
		return MessagesLocal.DELETE_SUCCESS;
	}

	
}
