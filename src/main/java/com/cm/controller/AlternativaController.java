package com.cm.controller;

import com.cm.dto.AlternativaDTO;
import com.cm.dto.QuestaoDTO;
import com.cm.modelo.Alternativa;
import com.cm.modelo.Questao;
import com.cm.service.AlternativaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/alternativas")
public class AlternativaController {

    @Autowired
    private AlternativaService service;

    @PostMapping
    public ResponseEntity<?> createAlternativa(@RequestBody AlternativaDTO alternativaDTO){
        Alternativa alternativa = service.create(alternativaDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(alternativa.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
