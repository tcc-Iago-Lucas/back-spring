package com.cm.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cm.controller.dto.TurmaDTO;
import com.cm.modelo.Turma;
import com.cm.service.TurmaService;

@RestController
@RequestMapping("/turma")
public class TurmaController {
	
	@Autowired private TurmaService service;

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody TurmaDTO turmaDTO) {
		Turma t = service.create(turmaDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(t.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping("/{id}")
	public ResponseEntity<Object> show(@PathVariable Long id) {
		return ResponseEntity.ok(service.show(id));
	}
	
	@PostMapping
	@RequestMapping("/{turmaID}/user/{userID}")
	public ResponseEntity<?> IncluirAlunoNaTurma(@PathVariable Long turmaID, @PathVariable Long userID){
		service.incluirAlunoNaTurma(turmaID, userID);
		return ResponseEntity.ok().build();
	}
	
	
	@RequestMapping(path= "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTurma(@PathVariable Long id){
		service.deleteTurma(id);
		return ResponseEntity.ok().build();
		
	}

	@RequestMapping("/{id}/gerar-codigo")
	public ResponseEntity<?> gerarCodigo(@PathVariable Long id){
		return  ResponseEntity.ok( service.gerarCodigo(id));
	}
	
}
