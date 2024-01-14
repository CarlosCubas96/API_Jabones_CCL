package com.dwes.api.servicios;

import java.util.List;

import com.dwes.api.entidades.Categoria;

public interface CategoriaService {

	List<Categoria> obtenerTodasLasCategorias();

	Categoria obtenerCategoriaPorId(Long id);

	Categoria crearCategoria(Categoria categoria);

	Categoria actualizarCategoria(Long id, Categoria categoria);

	void eliminarCategoria(Long id);

	void agregarProductoACategoria(Long categoriaId, Long productoId);

	void quitarProductoDeCategoria(Long categoriaId, Long productoId);

}
