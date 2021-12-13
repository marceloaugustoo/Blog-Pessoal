package com.generation.BlogPessoal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.generation.BlogPessoal.model.postagem;

import com.generation.BlogPessoal.repository.PostagemRepository;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*",allowedHeaders = "*")

public class PostagemController {
	
	@Autowired
	private PostagemRepository repository;
	
	//metodo utilizado para mostrar os posts
	@GetMapping
	public ResponseEntity<List<postagem>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	//metodo utilizado para mostrar os posts pelo id
	@GetMapping("/{id}")
	public ResponseEntity<postagem> getById(@PathVariable long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	//metodo utilizado para nos mostrar o titulo dos posts
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<postagem>> getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	//metodo utilizado para enviar posts
	@PostMapping
	public ResponseEntity<postagem> postpostagem (@Valid @RequestBody postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}
	
	//metodo utilizado para atualizar posts
	@PutMapping
	public ResponseEntity<postagem> putpostagem (@Valid @RequestBody postagem postagem){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
	}
	
	//metodo utilizado para deletar posts
	@DeleteMapping("/{id}")
	public void delete( @PathVariable long id) {
		repository.deleteById(id);
	}
	
}
