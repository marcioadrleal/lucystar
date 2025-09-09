package br.com.lucystar.login.entity.users;

import java.io.Serializable;

import br.com.lucystar.login.entity.roles.GroupsEntity;
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
@Table(name = "user_group")
public class UserGroupEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2766783464381346932L;

	@Id
	@Column(name = "id", length = 40, nullable = false)
	private String id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private UserEntity user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "group_id")
	private GroupsEntity group;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = 10, nullable = false)
	private StatuEnum status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public GroupsEntity getGroup() {
		return group;
	}

	public void setGroup(GroupsEntity group) {
		this.group = group;
	}

	public StatuEnum getStatus() {
		return status;
	}

	public void setStatus(StatuEnum status) {
		this.status = status;
	}

}
