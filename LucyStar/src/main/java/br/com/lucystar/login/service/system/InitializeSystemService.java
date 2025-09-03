package br.com.lucystar.login.service.system;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lucystar.login.dto.system.InitializeSystemDTO;
import br.com.lucystar.login.entity.system.SystemAdminParamEntity;
import br.com.lucystar.login.enums.StatuEnum;
import br.com.lucystar.login.exceptions.NotFoundException;
import br.com.lucystar.login.exceptions.ResetSystemException;
import br.com.lucystar.login.exceptions.SystemAdminException;
import br.com.lucystar.login.repository.system.SystemAdminParamRepository;
import br.com.lucystar.login.utils.MessagesException;
import br.com.lucystar.login.utils.Util;
import jakarta.transaction.Transactional;

@Service
public class InitializeSystemService {

	
	@Autowired
	private SystemAdminParamRepository systemAdminRepository;
	
	@Transactional
	public InitializeSystemDTO resetSystem(String email) throws ResetSystemException{
		 UUID ui = UUID.randomUUID();
		 Optional<SystemAdminParamEntity> entity = systemAdminRepository.findByEmail(email);
		 if ( !entity.isEmpty() ) {
		   throw new ResetSystemException("The system has already been initialized");	 
		 }
		 SystemAdminParamEntity system = new SystemAdminParamEntity();
		 system.setId(ui.toString());
		 system.setUserName(Util.SYSTEM_ADMIN);
		 system.setCreatedDate(new Date());
		 system.setStatus(StatuEnum.ACTIVE);
		 system.setSecretKey(UUID.randomUUID().toString());
		 system.setLocalSecretKey(UUID.randomUUID().toString());
		 system.setEmail(email);
		 systemAdminRepository.save(system);
		 InitializeSystemDTO initial = new InitializeSystemDTO(system.getUserName(), system.getSecretKey(), Util.convertDate(system.getCreatedDate()),system.getLocalSecretKey());
		 return initial;		
	}
	
    public SystemAdminParamEntity findByLocalKey( String localKey ) throws NotFoundException {
      return systemAdminRepository.findByLocalSecretKey( localKey ).orElseThrow(() -> new NotFoundException(MessagesException.localKeyNotFoundException) );	
    }
	
	public InitializeSystemDTO findSystemAdmin( String userName , String secretKey ) throws SystemAdminException {
	  	Optional<SystemAdminParamEntity> system = systemAdminRepository.findSystemAdmin(userName, secretKey);
	  	SystemAdminParamEntity valor = system.orElseThrow(() -> new SystemAdminException(""));
	  	InitializeSystemDTO initial = new InitializeSystemDTO(valor.getUserName(), valor.getSecretKey() , Util.convertDate(new Date()) , valor.getLocalSecretKey() );
	  	return initial;
	}
	
	
	
	
	
	
	
}
