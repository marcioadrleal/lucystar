package br.com.lucystar.login.entity.client;

import java.io.Serializable;

import br.com.lucystar.login.entity.system.SystemAdminParamEntity;
import br.com.lucystar.login.enums.StatuEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "client")
public class ClientEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1624760221921775836L;

	@Id
	@Column(name = "id", length = 40, nullable = false)
	private String id;

	@Column(name = "client_code", length = 20, nullable = false, unique = true)
	private String codeClient;

	@Column(name = "client_name", length = 80, nullable = false)
	private String nameClient;

	@Column(name = "status", length = 10, nullable = false)
	@Enumerated(EnumType.STRING)
	private StatuEnum status;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "system_admin_id", referencedColumnName = "id")
	private SystemAdminParamEntity systemAdmin;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodeClient() {
		return codeClient;
	}

	public void setCodeClient(String codeClient) {
		this.codeClient = codeClient;
	}

	public String getNameClient() {
		return nameClient;
	}

	public void setNameClient(String nameClient) {
		this.nameClient = nameClient;
	}

	public StatuEnum getStatus() {
		return status;
	}

	public void setStatus(StatuEnum status) {
		this.status = status;
	}

	public SystemAdminParamEntity getSystemAdmin() {
		return systemAdmin;
	}

	public void setSystemAdmin(SystemAdminParamEntity systemAdmin) {
		this.systemAdmin = systemAdmin;
	}

}
