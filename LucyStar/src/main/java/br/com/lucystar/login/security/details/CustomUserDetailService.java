package br.com.lucystar.login.security.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.lucystar.login.entity.users.UserEntity;
import br.com.lucystar.login.exceptions.NotFoundException;
import br.com.lucystar.login.repository.users.UserRepository;
import br.com.lucystar.login.utils.MessagesLocal;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userLogin) throws UsernameNotFoundException {
		UserEntity user = null;
		try {
			user = userRepository.findByUserName(userLogin).orElse(userRepository.findByEmail(userLogin)
					.orElseThrow(() -> new NotFoundException(MessagesLocal.USER_NOT_FOUND)));

		} catch (NotFoundException e) {
			throw new UsernameNotFoundException(e.getMessage());
		}
		
		// Pegando todos os roleCode em um array de String
		/*String[] rolesCodes = user.getGroups().stream()
		        .flatMap(group -> group.getRoles().stream()) // pega todos os roles dos grupos
		        .map(RolesEntity::getRoleCode)               // pega apenas o código do role
		        .distinct()                                  // opcional: remove duplicados
		        .toArray(String[]::new);                     // converte para array de String
		*/
		
		
		return new UserDetail(user);
		/*return User.builder()
				 .password(user.getPassword())
				 .username(user.getUserName())
				 .roles(user.getGroups().stream()
						   .flatMap( group -> group.getRoles().stream() )
						   .map(RolesEntity::getRoleCode )
						   .distinct()
						   .toArray(String[]::new)).build(); */
	}

}
