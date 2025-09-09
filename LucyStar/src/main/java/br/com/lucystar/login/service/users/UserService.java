package br.com.lucystar.login.service.users;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lucystar.login.config.SecurityConfiguration;
import br.com.lucystar.login.dto.roles.GroupDto;
import br.com.lucystar.login.dto.user.UserAddDto;
import br.com.lucystar.login.dto.user.UserDto;
import br.com.lucystar.login.entity.roles.GroupsEntity;
import br.com.lucystar.login.entity.users.UserEntity;
import br.com.lucystar.login.entity.users.UserGroupEntity;
import br.com.lucystar.login.enums.StatuEnum;
import br.com.lucystar.login.exceptions.NotFoundException;
import br.com.lucystar.login.repository.roles.GroupRepository;
import br.com.lucystar.login.repository.users.GroupUserRepository;
import br.com.lucystar.login.repository.users.UserRepository;
import br.com.lucystar.login.utils.MessagesLocal;
import br.com.lucystar.login.utils.Util;
import jakarta.transaction.Transactional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private GroupUserRepository groupUserRepository;

	@Autowired
	private SecurityConfiguration securityConfiguration;

	public UserDto findById(String id) throws NotFoundException {
		UserEntity user = userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(MessagesLocal.USER_NOT_FOUND));
		return convertDto(user);
	}

	@Transactional
	public UserDto add(UserAddDto dto) throws NotFoundException {
		if ((dto.group() == null) || (dto.group().size() == 0)) {
			throw new NotFoundException(MessagesLocal.VALID_USER_GROUP);
		}

		UserEntity user = new UserEntity();
		user.setCreatedAt(new Date());
		user.setEmail(dto.email());
		user.setName(dto.name());
		user.setUserName(dto.login());
		user.setStatus(StatuEnum.ACTIVE);
		user.setId(Util.generateId());
		user.setPassword(securityConfiguration.passwordEncoder().encode(dto.password()));
		List<GroupsEntity> lst = groupRepository.findAllById(dto.group());
		if ((lst == null) || (lst.size() == 0) || (lst.size() != dto.group().size())) {
			throw new NotFoundException(MessagesLocal.GROUP_NOT_FOUND);
		}
		userRepository.save(user);
		groupUserRepository.saveAll(configGroupUser(user, lst));

		UserEntity userEntity = userRepository.findById(user.getId())
				.orElseThrow(() -> new NotFoundException(MessagesLocal.FAILURE));

		return convertDto(userEntity);
	}

	private UserDto convertDto(UserEntity user) {
		List<GroupDto> lst = new ArrayList<>();
		if ((user.getGroups() != null) && (user.getGroups().size() > 0)) {
			user.getGroups()
					.forEach(x -> lst.add(new GroupDto(x.getId(), x.getGroupCode(), x.getGroupName(), x.getStatus(),
							x.getClientEntity().getId(), x.getClientEntity().getCodeClient(),
							x.getClientEntity().getNameClient(), null)));
		}
		return new UserDto(user.getId(), user.getName(), user.getUserName(), user.getEmail(), user.getStatus(),
				Util.convertDate(user.getCreatedAt()), Util.convertDate(user.getUpdatedAt()),
				Util.convertDate(user.getDeletedAt()), lst);
	}

	@Transactional
	private List<UserGroupEntity> configGroupUser(UserEntity user, List<GroupsEntity> lst) {
		List<UserGroupEntity> usrGroup = new ArrayList<>();
		lst.forEach(x -> {
			Optional<UserGroupEntity> ugr = groupUserRepository.findByUserGroup(user.getId(), x.getId());
			if (ugr.isPresent()) {
				usrGroup.add(ugr.get());
			} else {
				UserGroupEntity userGroup = new UserGroupEntity();
				userGroup.setGroup(x);
				userGroup.setStatus(StatuEnum.ACTIVE);
				userGroup.setUser(user);
				userGroup.setId(Util.generateId());
				usrGroup.add(userGroup);
			}
		});
		return usrGroup;

	}

	public UserDto update(String id, UserAddDto dto) throws NotFoundException {
		if ((dto.group() == null) || (dto.group().size() == 0)) {
			throw new NotFoundException(MessagesLocal.VALID_USER_GROUP);
		}
		UserEntity user = userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(MessagesLocal.USER_NOT_FOUND));
		user.setUpdatedAt(new Date());
		user.setEmail(dto.email());
		user.setName(dto.name());
		user.setUserName(dto.login());
		user.setStatus(StatuEnum.ACTIVE);
		user.setPassword(securityConfiguration.passwordEncoder().encode(dto.password()));
		List<GroupsEntity> lst = groupRepository.findAllById(dto.group());
		if ((lst == null) || (lst.size() == 0) || (lst.size() != dto.group().size())) {
			throw new NotFoundException(MessagesLocal.GROUP_NOT_FOUND);
		}
		Map<String, UserGroupEntity> mapUserGroup = new HashMap<>();
		user.getGroups().forEach(
				x -> mapUserGroup.put(x.getId(), groupUserRepository.findByUserGroup(x.getId(), user.getId()).get()));
		lst.forEach(x -> {

			if (mapUserGroup.get(x.getId()) != null) {
				Optional<UserGroupEntity> usr = groupUserRepository.findByUserGroup(x.getId(), user.getId());
				if (!usr.isPresent()) {
					UserGroupEntity u = new UserGroupEntity();
					u.setGroup(groupRepository.findById(x.getId()).get());
					u.setUser(user);
					u.setStatus(StatuEnum.ACTIVE);
					u.setId(Util.generateId());
                    mapUserGroup.put(x.getId(), u);
				}
			}

		});
		userRepository.save(user);
		groupUserRepository.saveAll(configGroupUser(user, lst));

		UserEntity userEntity = userRepository.findById(user.getId())
				.orElseThrow(() -> new NotFoundException(MessagesLocal.FAILURE));

		return convertDto(userEntity);
	}

}
