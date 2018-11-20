package com.inventario.bo;

public class Roles {

	//El id de esta clase como en las otras la dejo que la 
	//genere Hibernate y no se la paso por constructor
	private int id;
	private String nombre;
	private String nperfil;
	private Perfil perfil;
	
	public Roles() {}

	
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
