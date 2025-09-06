package br.com.lucystar.login.entity.roles;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import br.com.lucystar.login.entity.client.ClientEntity;
import br.com.lucystar.login.enums.StatuEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "groups")
public class GroupsEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 894836397391918803L;

	@Id
	@Column(name = "id", length = 40, nullable = false)
	private String id;

	@Column(name = "group_code", length = 20, nullable = false)
	private String groupCode;

	@Column(name = "group_name", length = 80, nullable = false)
	private String groupName;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = 10, nullable = false)
	private StatuEnum status;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "client_id", referencedColumnName = "id")
	private ClientEntity clientEntity;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "groups_roles", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RolesEntity> roles = new HashSet<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public StatuEnum getStatus() {
		return status;
	}

	public void setStatus(StatuEnum status) {
		this.status = status;
	}

	public ClientEntity getClientEntity() {
		return clientEntity;
	}

	public void setClientEntity(ClientEntity clientEntity) {
		this.clientEntity = clientEntity;
	}

	public Set<RolesEntity> getRoles() {
		return roles;
	}

	public void setRoles(Set<RolesEntity> roles) {
		this.roles = roles;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	
	

}
