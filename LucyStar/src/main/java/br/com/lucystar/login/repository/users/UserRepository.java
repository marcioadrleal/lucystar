package br.com.lucystar.login.repository.users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lucystar.login.entity.users.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
	
	
	public Optional<UserEntity> findByUserName(String userName);

	public Optional<UserEntity> findByEmail( String email);
	
}
