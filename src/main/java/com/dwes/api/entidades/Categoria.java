package com.dwes.api.entidades;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "categorias")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "El nombre no puede ser nulo")
	@Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres")
	private String nombre;

	@Size(max = 500, message = "La descripción no puede tener más de 500 caracteres")
	private String descripcion;

	@ManyToMany(mappedBy = "categorias")
	@Fetch(FetchMode.JOIN) // Este FetchMode asegura que la carga perezosa funcione correctamente
	@JsonIgnoreProperties("categorias") // Evita problemas de referencia circular al serializar a JSON
	private List<Producto> productos = new ArrayList<>();

	public Categoria() {
	}

	public Categoria(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
