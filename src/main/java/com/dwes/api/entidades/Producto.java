package com.dwes.api.entidades;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "productos") // Esta es la clase raíz
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_producto", discriminatorType = DiscriminatorType.STRING)
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "El nombre no puede ser nulo")
	@Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres")
	private String nombre;

	@NotNull(message = "El precio no puede ser nulo")
	@DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que 0")
	private Double precio;

	@Size(max = 500, message = "La descripción no puede tener más de 500 caracteres")
	private String descripcion;

	@NotNull(message = "El stock no puede ser nulo")
	@Min(value = 0, message = "El stock no puede ser negativo")
	private Integer stock;

	private String imagenUrl;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "producto_categoria", joinColumns = @JoinColumn(name = "producto_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	@Fetch(FetchMode.JOIN) // Este FetchMode asegura que la carga perezosa funcione correctamente
	@JsonIgnoreProperties("productos") // Evita problemas de referencia circular al serializar a JSON
	private List<Categoria> categorias = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
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

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getImagenUrl() {
		return imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}

}