package br.com.lucystar.login.repository.roles;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.lucystar.login.entity.roles.GroupRolesEntity;

@Repository
public interface GroupRolesRepository extends JpaRepository<GroupRolesEntity, String>{

	@Query("""
		       SELECT c 
		       FROM br.com.lucystar.login.entity.roles.GroupRolesEntity c 
		       JOIN c.group gr
		       JOIN c.role  rl 
		       WHERE gr.id = :groupId
		         AND rl.id IN :rolesId
		       """)
	public List<GroupRolesEntity> findGroupRoles(@Param("groupId") String groupId ,@Param("rolesId") List<String> rolesId);
	
	@Query("""
		       SELECT c 
		       FROM br.com.lucystar.login.entity.roles.GroupRolesEntity c 
		       JOIN c.group gr
		       JOIN c.role  rl 
		       WHERE gr.id = :groupId
		         AND rl.id = :rolesId
		       """)
	public Optional<GroupRolesEntity> findGroupRolesUnique(@Param("groupId") String groupId ,@Param("rolesId") String rolesId);
	
}
