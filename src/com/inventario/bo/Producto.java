package com.inventario.bo;

public class Producto {
	
	private int id;
	private String nombre;
	private String estatus;
	private String descripcion;
	private String idPersona;
	private Persona persona;

	
	
	public Producto(int id, String nombre, String estatus, String descripcion, Persona persona) {
		this.id = id;
		this.nombre = nombre;
		this.estatus = estatus;
		this.descripcion = descripcion;
		this.persona = persona;
	}
	
	


	public Producto(int id, String nombre, String estatus, String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.estatus = estatus;
		this.descripcion = descripcion;
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


	public String getEstatus() {
		return estatus;
	}


	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Persona getPersona() {
		return persona;
	}


	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	//No lo voy a colocar en el constructor. Mejorar.
	public void setIdPersona(String id) {
		
		this.idPersona = id;
		
	}
	
	public String getIdPersona() {
		
		return idPersona;
	}


	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", estatus=" + estatus + ", descripcion=" + descripcion
				+ ", persona=" + persona + ", id_persona=" + idPersona + "]";
	}
	
	
	
	
}
