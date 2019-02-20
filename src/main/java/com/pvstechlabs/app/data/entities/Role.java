package com.pvstechlabs.app.data.entities;

public class Role {
	
	private Long id;

	private RoleType name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleType getName() {
		return name;
	}

	public void setName(RoleType name) {
		this.name = name;
	}
}
