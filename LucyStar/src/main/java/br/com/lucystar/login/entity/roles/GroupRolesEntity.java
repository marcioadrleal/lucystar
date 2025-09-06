package br.com.lucystar.login.entity.roles;

import java.io.Serializable;

import br.com.lucystar.login.enums.StatuEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "groups_roles")
public class GroupRolesEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1632375524564374881L;

	@Id
	@Column(name = "id", length = 40, nullable = false)
	private String id;

	@ManyToOne
	@JoinColumn(name = "group_id", nullable = false)
	private GroupsEntity group;

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	private RolesEntity role;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = 10, nullable = false)
	private StatuEnum status;

	public GroupRolesEntity(String id, GroupsEntity group, RolesEntity role, StatuEnum status) {
		super();
		this.id = id;
		this.group = group;
		this.role = role;
		this.status = status;
	}

	public GroupRolesEntity() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public GroupsEntity getGroup() {
		return group;
	}

	public void setGroup(GroupsEntity group) {
		this.group = group;
	}

	public RolesEntity getRole() {
		return role;
	}

	public void setRole(RolesEntity role) {
		this.role = role;
	}

	public StatuEnum getStatus() {
		return status;
	}

	public void setStatus(StatuEnum status) {
		this.status = status;
	}

}