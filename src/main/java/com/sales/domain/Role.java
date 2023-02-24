package com.sales.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionType;
import org.springframework.security.core.GrantedAuthority;

import com.sales.domain.enums.RoleName;

@Entity
@Table(name = "tb_role")
public class Role  implements Serializable, GrantedAuthority{

	private static final long serialVersionUID = 1;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, unique = true)
	public RoleName roleName;

	@Override
	public String getAuthority() {
		return this.roleName.toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleName getRoleName() {
		return roleName;
	}

	public void setRoleName(RoleName roleName) {
		this.roleName = roleName;
	}
	

}
