package com.cm.controller;

import com.cm.dto.out.RespostaUsuarioDTOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cm.dto.RespostaDTO;
import com.cm.modelo.Resposta;
import com.cm.service.RespostaService;

import java.util.List;

@RestController
@RequestMapping("/respostas")
public class RespostaController {
	@Autowired RespostaService service;
	
	@PostMapping
	ResponseEntity<?> responder(@RequestHeader("Authorization") String token, @RequestBody RespostaDTO respostaDTO){
		Resposta resposta =  service.responder(token,respostaDTO);
		return ResponseEntity.ok(resposta);
	}

	@GetMapping("/{userTurmaId}/{temaId}")
	public  ResponseEntity<List<RespostaUsuarioDTOut>> respostaPorUsuarioTurma(@PathVariable Long userTurmaId,
																			   @PathVariable Long temaId){
		return  ResponseEntity.ok(service.respostaPorUsuarioTurma(userTurmaId, temaId));
	}

}
