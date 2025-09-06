package br.com.lucystar.login.repository.client;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.lucystar.login.entity.client.ClientEntity;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, String> {

	@Query("""
		       SELECT c 
		       FROM br.com.lucystar.login.entity.client.ClientEntity c 
		       JOIN c.systemAdmin sa 
		       WHERE sa.localSecretKey = :secretKey
		       """)
	public List<ClientEntity> findSecretLocalKey(@Param("secretKey") String secretKey);
	
	@Query("""
		       SELECT c 
		       FROM br.com.lucystar.login.entity.client.ClientEntity c 
		       JOIN c.systemAdmin sa 
		       WHERE c.codeClient = :code
		         and sa.localSecretKey = :secretKey
		       """)
	public Optional<ClientEntity> findByCodeClient( @Param("code") String code ,  @Param("secretKey") String secretKey  );
	
}
