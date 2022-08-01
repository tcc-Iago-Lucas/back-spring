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

import com.cm.controller.dto.TemaDTO;
import com.cm.modelo.Tema;
import com.cm.service.TemaService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/tema")
public class TemaController {
	
	@Autowired private TemaService service;

	@GetMapping
	public Page<Tema> temas(
			@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
					@ApiIgnore  Pageable paginacao,
			@RequestParam(required = false) Integer page ,
			@RequestParam(required = false) String sort ,
			@RequestParam(required = false) Integer size ,
			@RequestParam(required = false) Sort.Direction direction
			){
		return  service.temas(paginacao);
	}

	@PostMapping
	public ResponseEntity<?> createTema(@RequestBody TemaDTO temaDTO){
		Tema t = service.create(temaDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(t.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tema> showTema(@RequestHeader("Authorization") String token,  @PathVariable Long id){
		System.out.println(token);
		return ResponseEntity.ok(service.show(id));
	}
	
	
	@PutMapping(path="/{id}" )
	public ResponseEntity<Void> updateTema(@PathVariable Long id, @RequestBody TemaDTO temaDTO){
		service.update(id, temaDTO);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<?> deleteTema(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
