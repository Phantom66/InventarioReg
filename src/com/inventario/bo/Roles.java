package com.inventario.bo;

public class Roles {

	// El id de esta clase como en las otras la dejo que la
	// genere Hibernate y no se la paso por constructor
	private int id;
	private String nombre;
	private String nperfil;
	private Perfil perfil;

	public Roles() {
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
		Roles other = (Roles) obj;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNperfil() {
		return nperfil;
	}

	public void setNperfil(String nperfil) {
		this.nperfil = nperfil;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	@Override
	public String toString() {
		return "Roles [id=" + id + ", nombre=" + nombre + ", nperfil=" + nperfil + ", perfil=" + perfil + "]";
	}

}
