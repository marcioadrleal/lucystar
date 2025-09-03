package br.com.lucystar.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.lucystar.login.entity.SystemAdminParamEntity;

@Repository
public interface SystemAdminParamRepository extends JpaRepository<SystemAdminParamEntity, String> {

	public Optional<SystemAdminParamEntity> findByUserName(String userName);

	@Query("SELECT m FROM br.com.lucystar.login.entity.SystemAdminParamEntity m WHERE m.userName = :userName AND m.secretKey = :secretKey")
	public Optional<SystemAdminParamEntity> findSystemAdmin(String userName, String secretKey);

	public Optional<SystemAdminParamEntity> findByLocalSecretKey(String localSecretKey);
	
	public Optional<SystemAdminParamEntity> findByEmail( String email );

}
