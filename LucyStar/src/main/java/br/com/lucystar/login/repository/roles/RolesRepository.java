package br.com.lucystar.login.repository.roles;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.lucystar.login.entity.roles.RolesEntity;

@Repository
public interface RolesRepository extends JpaRepository<RolesEntity, String> {


	@Query("""
		       SELECT c 
		       FROM br.com.lucystar.login.entity.roles.RolesEntity c 
		       JOIN c.systemAdmin sa 
		       WHERE sa.localSecretKey = :secretKey
		       """)
	public List<RolesEntity> findAllRoles(@Param("secretKey") String secretKey);
	
	
	@Query("""
		       SELECT c 
		       FROM br.com.lucystar.login.entity.roles.RolesEntity c 
		       JOIN c.systemAdmin sa 
		       WHERE c.roleCode = :code
		             and sa.localSecretKey = :secretKey
		       """)
	public Optional<RolesEntity> findByRoleCode(@Param("code") String code , @Param("secretKey") String secretKey);
	
	
	
	
}
