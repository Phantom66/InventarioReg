package com.inventario.bo;

import java.util.List;

public class Perfil {

	// El id de esta clase como en las otras la dejo que la
	// genere Hibernate y no se la paso por constructor
	private int id;
	private String name;
	private String email;
	private String password;

	private Persona persona;

	private List<Roles> roles;

	public Perfil() {
	}

	public Perfil(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public Perfil(String name, String email, String password, Persona persona) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.persona = persona;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Perfil other = (Perfil) obj;
		if (id != other.id)
			return false;
		return true;
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
