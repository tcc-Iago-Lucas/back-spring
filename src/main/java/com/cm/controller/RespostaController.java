package com.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cm.dto.RespostaDTO;
import com.cm.modelo.Resposta;
import com.cm.service.RespostaService;

@RestController
@RequestMapping("/respostas")
public class RespostaController {
	@Autowired RespostaService service;
	
	@PostMapping
	ResponseEntity<?> responder(@RequestHeader("Authorization") String token, @RequestBody RespostaDTO respostaDTO){
		Resposta resposta =  service.responder(token,respostaDTO);
		return ResponseEntity.ok(resposta);
	}

}
