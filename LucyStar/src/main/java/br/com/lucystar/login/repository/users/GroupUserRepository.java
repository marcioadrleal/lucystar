package br.com.lucystar.login.repository.users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.lucystar.login.entity.users.UserGroupEntity;

@Repository
public interface GroupUserRepository extends JpaRepository<UserGroupEntity, String> {
	
	@Query("""
		       SELECT c 
		       FROM br.com.lucystar.login.entity.users.UserGroupEntity c 
		       JOIN c.group gr
		       JOIN c.user  rl 
		       WHERE gr.id = :groupId
		         AND rl.id = :userId
		       """)
	public Optional<UserGroupEntity> findByUserGroup(@Param("groupId") String groupId , @Param("userId") String userId );

}
