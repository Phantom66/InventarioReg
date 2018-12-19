package com.inventario.bo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="producto")
public class Producto {

	// El id de esta clase como en las otras la dejo que la
	// genere Hibernate y no se la paso por constructor
	@Id
	private int id;
	
	private String nombre;
	private String estatus;
	private String descripcion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_persona")
	private Persona persona;

	public Producto() {	};

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
		Producto other = (Producto) obj;
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

	// No lo voy a colocar en el constructor. Mejorar.
//	public void setIdPersona(String id) {
//
//		this.idPersona = id;
//
//	}
//
//	public String getIdPersona() {
//
//		return idPersona;
//	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", estatus=" + estatus + ", descripcion=" + descripcion
				+  "]";
	}

	

}
