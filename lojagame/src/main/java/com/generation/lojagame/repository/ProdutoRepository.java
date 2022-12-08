package com.generation.lojagame.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.lojagame.entity.Produto;
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	@Query("SELECT p FROM Produto p WHERE p.id = ?1")
	Produto getById(Long id);
	
	
	@Query("SELECT p FROM Produto p WHERE p.tipo LIKE %:titulo%")
	List<Produto> getByTitulo(@Param("titulo") String titulo);
	
	public List<Produto> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);
	
	

}
