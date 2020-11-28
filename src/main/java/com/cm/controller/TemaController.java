package com.cm.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cm.controller.dto.TemaDTO;
import com.cm.modelo.Tema;
import com.cm.service.TemaService;

@RestController
@RequestMapping("/tema")
public class TemaController {
	
	@Autowired private TemaService service;
	
	@PostMapping
	public ResponseEntity<?> createTema(@RequestBody TemaDTO temaDTO){
		Tema t = service.create(temaDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(t.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping("/{id}")
	public ResponseEntity<Tema> showTema(@PathVariable Long id){
		return ResponseEntity.ok(service.show(id));
	}
	
	
	@RequestMapping(path="/{id}" , method = RequestMethod.PUT)
	public ResponseEntity<?> updateTema(@PathVariable Long id, @RequestBody TemaDTO temaDTO){
		service.update(id, temaDTO);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(path="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTema(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
