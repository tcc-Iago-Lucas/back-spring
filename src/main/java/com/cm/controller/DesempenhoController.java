package com.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cm.service.DesempenhoService;

@RestController
@RequestMapping("/desempenhos")
public class DesempenhoController {
	
	@Autowired private DesempenhoService service;
	
	@GetMapping()
	public ResponseEntity<?> findByUserTurma(@RequestParam Long userTurmaId){
		return ResponseEntity.ok(service.findByUserTurma(userTurmaId));
	}

}
