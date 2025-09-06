package br.com.lucystar.login.service.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lucystar.login.dto.client.ClientAddDTO;
import br.com.lucystar.login.dto.client.ClientDto;
import br.com.lucystar.login.entity.client.ClientEntity;
import br.com.lucystar.login.entity.system.SystemAdminParamEntity;
import br.com.lucystar.login.exceptions.DeleteException;
import br.com.lucystar.login.exceptions.NotFoundException;
import br.com.lucystar.login.repository.client.ClientRepository;
import br.com.lucystar.login.service.system.InitializeSystemService;
import br.com.lucystar.login.utils.MessagesException;
import br.com.lucystar.login.utils.MessagesLocal;
import br.com.lucystar.login.utils.Util;
import jakarta.transaction.Transactional;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InitializeSystemService systemAdminService;

	private ClientEntity findClientLocal(String id) throws NotFoundException {
		Optional<ClientEntity> client = clientRepository.findById(id);
		return client.orElseThrow(() -> new NotFoundException(MessagesException.clientNotFoundException));
	}

	@Transactional
	public ClientDto addClient(ClientAddDTO client) throws NotFoundException {
		ClientEntity entity = new ClientEntity();
		entity.setId(Util.generateId());
		SystemAdminParamEntity systemAdminParamEntity = systemAdminService.findByLocalKey(client.localKey());
		entity.setCodeClient(client.codeClient());
		entity.setSystemAdmin(systemAdminParamEntity);
		entity.setNameClient(client.clientName());
		entity.setStatus(client.status());
		clientRepository.save(entity);
		return new ClientDto(entity.getId(), entity.getCodeClient(), entity.getNameClient(), entity.getStatus(),
				entity.getSystemAdmin().getLocalSecretKey());
	}

	public ClientDto updateClient(ClientAddDTO client, String id) throws NotFoundException {
		ClientEntity entity = findClientLocal(id);
		entity.setCodeClient(client.codeClient());
		entity.setNameClient(client.clientName());
		entity.setStatus(client.status());
		entity.setSystemAdmin(systemAdminService.findByLocalKey(client.localKey()));
		clientRepository.save(entity);
		return new ClientDto(entity.getId(), entity.getCodeClient(), entity.getNameClient(), entity.getStatus(),
				entity.getSystemAdmin().getLocalSecretKey());
	}

	public List<ClientDto> findAll(String localSecretKey) {
		List<ClientDto> list = new ArrayList<>();
		List<ClientEntity> lstClient = clientRepository.findSecretLocalKey(localSecretKey);
		if ((lstClient != null) && (lstClient.size() > 0)) {
			lstClient.forEach(x -> list.add(new ClientDto(x.getId(), x.getCodeClient(), x.getNameClient(),
					x.getStatus(), x.getSystemAdmin().getLocalSecretKey())));
		}
		return list;
	}

	public ClientDto findById(String id) throws NotFoundException {
		ClientEntity entity = findClientLocal(id);
		return new ClientDto(entity.getId(), entity.getCodeClient(), entity.getNameClient(), entity.getStatus(),
				entity.getSystemAdmin().getLocalSecretKey());
	}

	public void deleteClient(String id) throws DeleteException {
		try {
			clientRepository.deleteById(id);
		} catch (Exception e) {
			throw new DeleteException(MessagesException.deleteException);
		}
	}

	public ClientDto findByCodeClient(String code, String SecretlocalKey) throws NotFoundException {
		ClientEntity entity = clientRepository.findByCodeClient(code, SecretlocalKey)
				.orElseThrow(() -> new NotFoundException(MessagesLocal.CLIENT_NOT_FOUND));
		return new ClientDto(entity.getId(), entity.getCodeClient(), entity.getNameClient(), entity.getStatus(),
				entity.getSystemAdmin().getLocalSecretKey());
	}
	
	
	public ClientEntity findByIdEntity(String id) throws NotFoundException {
	  return clientRepository.findById(id).orElseThrow(() -> new NotFoundException(MessagesLocal.CLIENT_NOT_FOUND));	
	}

}
