package com.cm.controller;

import com.cm.dto.DesempenhoDTO;
import com.cm.modelo.Desempenho;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cm.service.DesempenhoService;

import java.util.List;

@RestController
@RequestMapping("/desempenhos")
public class DesempenhoController {
	
	@Autowired private DesempenhoService service;
	
	@GetMapping("/{userTurmaId}")
	public ResponseEntity<?> findByUserTurma(@PathVariable Long userTurmaId){
		return ResponseEntity.ok(service.findByUserTurma(userTurmaId));
	}


	@PostMapping("/calcular")
	public ResponseEntity<Void> cacular(@RequestHeader("Authorization") String token,@RequestBody DesempenhoDTO desempenhoDTO){
		service.calcular(token,desempenhoDTO);
		return ResponseEntity.ok().build();
	}


}
