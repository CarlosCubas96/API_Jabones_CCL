package com.dwes.api.servicios.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dwes.api.entidades.Categoria;
import com.dwes.api.entidades.Producto;
import com.dwes.api.repositorios.CategoriaRepository;
import com.dwes.api.repositorios.ProductoRepository;
import com.dwes.api.servicios.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	private final CategoriaRepository categoriaRepository;
	private final ProductoRepository productoRepository;

	public CategoriaServiceImpl(CategoriaRepository categoriaRepository, ProductoRepository productoRepository) {
		this.categoriaRepository = categoriaRepository;
		this.productoRepository = productoRepository;
	}

	@Override
	public List<Categoria> obtenerTodasLasCategorias() {
		return categoriaRepository.findAll();
	}

	@Override
	public Categoria obtenerCategoriaPorId(Long id) {
		return categoriaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Categoría no encontrada: " + id));
	}

	@Override
	public Categoria crearCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	@Override
	@Transactional
	public Categoria actualizarCategoria(Long id, Categoria categoria) {
		Categoria categoriaExistente = obtenerCategoriaPorId(id);

		// Actualizar las propiedades de la categoría existente con las del objeto
		// proporcionado
		categoriaExistente.setNombre(categoria.getNombre());
		categoriaExistente.setDescripcion(categoria.getDescripcion());

		return categoriaRepository.save(categoriaExistente);
	}

	@Override
	public void eliminarCategoria(Long id) {
		categoriaRepository.deleteById(id);
	}

	@Override
	@Transactional
	public void agregarProductoACategoria(Long categoriaId, Long productoId) {
		Categoria categoria = categoriaRepository.findById(categoriaId)
				.orElseThrow(() -> new RuntimeException("Categoría no encontrada: " + categoriaId));

		Producto producto = productoRepository.findById(productoId)
				.orElseThrow(() -> new RuntimeException("Producto no encontrado: " + productoId));

		categoria.getProductos().add(producto);
		producto.getCategorias().add(categoria);
	}

	@Override
	@Transactional
	public void quitarProductoDeCategoria(Long categoriaId, Long productoId) {
		Categoria categoria = categoriaRepository.findById(categoriaId)
				.orElseThrow(() -> new RuntimeException("Categoría no encontrada: " + categoriaId));

		Producto producto = productoRepository.findById(productoId)
				.orElseThrow(() -> new RuntimeException("Producto no encontrado: " + productoId));

		categoria.getProductos().remove(producto);
		producto.getCategorias().remove(categoria);
	}
}
