package br.com.lucystar.login.entity.roles;

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
@Table(name = "roles")
public class RolesEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9204663375843428677L;

	@Id
	@Column(name = "id", length = 40, nullable = false)
	private String id;

	@Column(name = "role_code", length = 15, nullable = false)
	private String roleCode;

	@Column(name = "role_name", length = 40, nullable = false)
	private String roleName;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = 10, nullable = false)
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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

}
