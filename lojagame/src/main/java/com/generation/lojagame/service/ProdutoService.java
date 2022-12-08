package com.generation.lojagame.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import com.generation.lojagame.entity.Produto;
import com.generation.lojagame.repository.ProdutoRepository;

import jakarta.validation.Valid;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository repository;

	public ResponseEntity<List<Produto>> getAll() {
		return ResponseEntity.ok(repository.findAll());

	}

	public ResponseEntity<Produto> getById(Long id) {
		Produto produto = repository.getById(id);

		if (produto == null) {
			return ResponseEntity.notFound().build();

		} else {
			return ResponseEntity.ok(produto);
		}

	}

	public ResponseEntity<List<Produto>> getByTipo(String tipo) {
		List<Produto> produto = repository.getByTipo(tipo);
		if (produto.isEmpty()) {
			return ResponseEntity.notFound().build();

		} else {
			return ResponseEntity.ok(produto);

		}
	}
	
	public ResponseEntity<List<Produto>> getByTitulo(String titulo) {
		List<Produto> produto = repository.findAllByTituloContainingIgnoreCase(titulo);
		if (produto.isEmpty()) {
			return ResponseEntity.notFound().build();

		} else {
			return ResponseEntity.ok(produto);

		}
	}

	public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));

	}

	public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto) {
		return repository.findById(produto.getId())
				.map(response -> ResponseEntity.status(HttpStatus.OK).body(repository.save(produto)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

	}

	public void delete(@PathVariable Long id) {
		Optional<Produto> postagem = repository.findById(id);
		if (postagem.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		repository.deleteById(id);

	}
}