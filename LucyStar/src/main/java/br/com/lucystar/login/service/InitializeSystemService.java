package br.com.lucystar.login.service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.lucystar.login.dto.InitializeSystemDTO;
import br.com.lucystar.login.entity.SystemAdminParamEntity;
import br.com.lucystar.login.enums.StatuEnum;
import br.com.lucystar.login.exceptions.ResetSystemException;
import br.com.lucystar.login.repository.SystemAdminParamRepository;
import br.com.lucystar.login.utils.Util;
import jakarta.transaction.Transactional;

@Service
public class InitializeSystemService {

	
	@Autowired
	private SystemAdminParamRepository systemAdminRepository;
	
	@Transactional
	public ResponseEntity<InitializeSystemDTO> resetSystem() throws ResetSystemException{
		 UUID ui = UUID.randomUUID();
		 Optional<SystemAdminParamEntity> entity = systemAdminRepository.findByUserName(Util.SYSTEM_ADMIN);
		 if ( !entity.isEmpty() ) {
		   throw new ResetSystemException("The system has already been initialized");	 
		 }
		 SystemAdminParamEntity system = new SystemAdminParamEntity();
		 system.setId(ui.toString());
		 system.setUserName(Util.SYSTEM_ADMIN);
		 system.setCreatedDate(new Date());
		 system.setStatus(StatuEnum.ACTIVE);
		 system.setSecretKey(UUID.randomUUID().toString());
		 systemAdminRepository.save(system);
		 InitializeSystemDTO initial = new InitializeSystemDTO(system.getUserName(), system.getSecretKey(), Util.convertDate(system.getCreatedDate()));
		 return new ResponseEntity<InitializeSystemDTO>(initial,HttpStatus.CREATED);		
	}
	
	
	
	
	
	
}
