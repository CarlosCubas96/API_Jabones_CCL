package com.dwes.api.controladores;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dwes.api.entidades.Categoria;
import com.dwes.api.servicios.CategoriaService;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

	private final CategoriaService categoriaService;

	public CategoriaController(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	@Operation(summary = "Obtener todas las categorías", description = "Devuelve una lista de todas las categorías.")
	@GetMapping
	public ResponseEntity<List<Categoria>> obtenerTodasLasCategorias() {
		List<Categoria> categorias = categoriaService.obtenerTodasLasCategorias();
		return new ResponseEntity<>(categorias, HttpStatus.OK);
	}

	@Operation(summary = "Obtener una categoría por su ID", description = "Devuelve una categoría según su ID.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Categoría obtenida con éxito", content = @Content(schema = @Schema(implementation = Categoria.class))),
			@ApiResponse(responseCode = "404", description = "Categoría no encontrada") })
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> obtenerCategoriaPorId(
			@Parameter(description = "ID de la categoría", required = true) @PathVariable Long id) {
		Categoria categoria = categoriaService.obtenerCategoriaPorId(id);
		return categoria != null ? new ResponseEntity<>(categoria, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Operation(summary = "Crear una nueva categoría", description = "Crea una nueva categoría.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Categoría creada con éxito", content = @Content(schema = @Schema(implementation = Categoria.class))),
			@ApiResponse(responseCode = "400", description = "Solicitud incorrecta") })
	@PostMapping
	public ResponseEntity<Categoria> crearCategoria(
			@Parameter(description = "Datos de la nueva categoría", required = true) @RequestBody Categoria categoria) {
		Categoria nuevaCategoria = categoriaService.crearCategoria(categoria);
		return new ResponseEntity<>(nuevaCategoria, HttpStatus.CREATED);
	}

	@Operation(summary = "Actualizar una categoría existente", description = "Actualiza una categoría existente.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Categoría actualizada con éxito", content = @Content(schema = @Schema(implementation = Categoria.class))),
			@ApiResponse(responseCode = "404", description = "Categoría no encontrada"),
			@ApiResponse(responseCode = "400", description = "Solicitud incorrecta") })
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> actualizarCategoria(
			@Parameter(description = "ID de la categoría a actualizar", required = true) @PathVariable Long id,
			@Parameter(description = "Datos actualizados de la categoría", required = true) @RequestBody Categoria categoria) {
		Categoria categoriaActualizada = categoriaService.actualizarCategoria(id, categoria);
		return categoriaActualizada != null ? new ResponseEntity<>(categoriaActualizada, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Operation(summary = "Eliminar una categoría por su ID", description = "Elimina una categoría según su ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Categoría eliminada con éxito"),
			@ApiResponse(responseCode = "404", description = "Categoría no encontrada") })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarCategoria(
			@Parameter(description = "ID de la categoría a eliminar", required = true) @PathVariable Long id) {

		categoriaService.eliminarCategoria(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
}
