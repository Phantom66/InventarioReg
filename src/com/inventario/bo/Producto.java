package com.inventario.bo;

public class Producto {
	
	//El id de esta clase como en las otras la dejo que la 
	//genere Hibernate y no se la paso por constructor
	private int id;
	private String nombre;
	private String estatus;
	private String descripcion;
	private String idPersona;
	private Persona persona;
	
	
	public Producto() {};
	
	public Producto(String nombre, String estatus, String descripcion, Persona persona) {
		
		this.nombre = nombre;
		this.estatus = estatus;
		this.descripcion = descripcion;
		this.persona = persona;
	}
		


	public Producto(String nombre, String estatus, String descripcion) {
		
		this.nombre = nombre;
		this.estatus = estatus;
		this.descripcion = descripcion;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((estatus == null) ? 0 : estatus.hashCode());
		result = prime * result + id;
		result = prime * result + ((idPersona == null) ? 0 : idPersona.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((persona == null) ? 0 : persona.hashCode());
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
		Producto other = (Producto) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (estatus == null) {
			if (other.estatus != null)
				return false;
		} else if (!estatus.equals(other.estatus))
			return false;
		if (id != other.id)
			return false;
		if (idPersona == null) {
			if (other.idPersona != null)
				return false;
		} else if (!idPersona.equals(other.idPersona))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (persona == null) {
			if (other.persona != null)
				return false;
		} else if (!persona.equals(other.persona))
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
