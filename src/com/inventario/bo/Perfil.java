package com.inventario.bo;

import java.util.List;

public class Perfil {
	
	private int id;
	private String email;
	private String name;
	private String password;
	private Persona persona;
	private List<Roles>roles;
	

	public Perfil () {	}
	
	public Perfil(int id, String name, String email, String password) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.password = password;
	}

	public Perfil(int id, String name, String email, String password, Persona persona) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.password = password;
		this.persona = persona;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Perfil [id=" + id + ", email=" + email + ", name=" + name + ", password=" + password + ", persona="
				+ persona + ", roles=" + roles + "]";
	}
	
	

}
