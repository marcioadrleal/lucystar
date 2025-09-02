package br.com.lucystar.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lucystar.login.entity.SystemAdminParamEntity;

@Repository
public interface SystemAdminParamRepository extends JpaRepository<SystemAdminParamEntity, String> {

	public Optional<SystemAdminParamEntity> findByUserName(String userName);

}
