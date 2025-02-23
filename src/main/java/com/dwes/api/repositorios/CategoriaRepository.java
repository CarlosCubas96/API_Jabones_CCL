package com.dwes.api.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dwes.api.entidades.Categoria;
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
