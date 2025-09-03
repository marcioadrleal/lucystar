package br.com.lucystar.login.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.lucystar.login.entity.ClientEntity;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, String> {

	@Query("""
		       SELECT c 
		       FROM br.com.lucystar.login.entity.ClientEntity c 
		       JOIN c.systemAdmin sa 
		       WHERE sa.localSecretKey = :secretKey
		       """)
	public List<ClientEntity> findSecretLocalKey(@Param("secretKey") String secretKey);
	
}
