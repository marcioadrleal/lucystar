package br.com.lucystar.login.entity;

import java.io.Serializable;
import java.util.Date;

import br.com.lucystar.login.enums.StatuEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity(name = "system_admin")
public class SystemAdminParamEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8837226406006110363L;

	@Id
	@Column(name = "id", length = 40)
	private String id;

	@Column(name = "user_name", length = 20, nullable = false)
	private String userName;

	@Column(name = "secret_key", length = 40, nullable = false)
	private String secretKey;

	@Column(name = "local_secret_key", length = 40, nullable = false)
	private String localSecretKey;

	@Column(name = "created_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "updated_date", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;

	@Column(name = "deleted_date", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date deletedDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = 10, nullable = false)
	private StatuEnum status;

	@Column(name = "email", length = 40, nullable = false)
	private String email;

	public String getLocalSecretKey() {
		return localSecretKey;
	}

	public void setLocalSecretKey(String localSecretKey) {
		this.localSecretKey = localSecretKey;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getDeletedDate() {
		return deletedDate;
	}

	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}

	public StatuEnum getStatus() {
		return status;
	}

	public void setStatus(StatuEnum status) {
		this.status = status;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
