package br.com.lucystar.login.repository.roles;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.lucystar.login.entity.roles.GroupsEntity;

@Repository
public interface GroupRepository extends JpaRepository<GroupsEntity,String> {

	
	@Query("""
		       SELECT c 
		       FROM br.com.lucystar.login.entity.roles.GroupsEntity c 
		       JOIN c.clientEntity sa 
		       WHERE sa.id = :secretKey
		       """)
	public List<GroupsEntity> findAllGroup(@Param("secretKey") String secretKey);
	
	
	@Query("""
		       SELECT c 
		       FROM br.com.lucystar.login.entity.roles.GroupsEntity c 
		       JOIN c.clientEntity sa 
		       WHERE c.groupCode = :code
		             AND sa.id = :secretKey
		       """)
	public Optional<GroupsEntity> findByCodeGroup(@Param("code") String code , @Param("secretKey") String secretKey);
}
