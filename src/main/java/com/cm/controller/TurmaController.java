package com.cm.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cm.dto.TurmaDTO;
import com.cm.modelo.Turma;
import com.cm.service.TurmaService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/turmas")
public class TurmaController {
	
	@Autowired private TurmaService service;

	@PostMapping
	public ResponseEntity<Object> create(@RequestHeader("Authorization") String token,@RequestBody TurmaDTO turmaDTO) {
		Turma t = service.create(token,turmaDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(t.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> show(@PathVariable Long id) {
		return ResponseEntity.ok(service.show(id));
	}
	
	@PostMapping("/{turmaID}/user/{userID}")
	public ResponseEntity<?> IncluirAlunoNaTurma(@PathVariable Long turmaID, @PathVariable Long userID){
		service.incluirAlunoNaTurma(turmaID, userID);
		return ResponseEntity.ok().build();
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTurma(@RequestHeader("Authorization") String token, @PathVariable Long id){
		service.deleteTurma(token,id);
		return ResponseEntity.ok().build();
		
	}

	@PostMapping("/{id}/gerar-codigo")
	public ResponseEntity<?> gerarCodigo(@PathVariable Long id){
		return  ResponseEntity.ok( service.gerarCodigo(id));
	}

	@PostMapping("/matricular/{codigoTurma}")
	public ResponseEntity<Void> matricular(@RequestHeader("Authorization") String token,  @PathVariable String codigoTurma){
		service.matricular(token,codigoTurma);

		return ResponseEntity.ok().build();
	}

	@GetMapping
	public ResponseEntity<Page<Turma>> pageTurma(
			@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
			@ApiIgnore Pageable paginacao,
			@RequestParam(required = false) Integer page ,
			@RequestParam(required = false) String sort ,
			@RequestParam(required = false) Integer size ,
			@RequestParam(required = false) Sort.Direction direction
	){
		return ResponseEntity.ok(service.turmas(paginacao));
	}

	
}
