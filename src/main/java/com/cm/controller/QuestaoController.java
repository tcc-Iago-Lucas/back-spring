package com.cm.controller;

import com.cm.dto.QuestaoDTO;
import com.cm.modelo.Questao;
import com.cm.service.QuestaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/questoes")
public class QuestaoController {
    @Autowired private QuestaoService service;

    @PostMapping
    public ResponseEntity<?> createQuestao(@RequestBody QuestaoDTO questaoDTO){
         Questao questao = service.create(questaoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(questao.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Questao> show(@PathVariable Long id){
        return ResponseEntity.ok(service.find(id));
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> uptade(@PathVariable Long id, @RequestBody QuestaoDTO questaoDTO){
        service.update(id,questaoDTO);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return  ResponseEntity.noContent().build();
    }


}
