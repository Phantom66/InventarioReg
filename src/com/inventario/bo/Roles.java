package com.inventario.bo;

public class Roles {

	
	private int id;
	private String nombre;
	private String nPerfil;
	private Perfil perfil;
	
	public Roles() {}
	
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return nombre;
	}



	public void setName(String nombre) {
		this.nombre = nombre;
	}



	public String getnPerfil() {
		return nPerfil;
	}



	public void setnPerfil(String nPerfil) {
		this.nPerfil = nPerfil;
	}



	public Perfil getPerfil() {
		return perfil;
	}



	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}



	@Override
	public String toString() {
		return "Roles [id=" + id + ", nomvre=" + nombre + ", nPerfil=" + nPerfil + ", perfil=" + perfil + "]";
	}
	
	
	
	
}
