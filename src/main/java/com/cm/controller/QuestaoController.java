package com.cm.controller;

import com.cm.dto.QuestaoDTO;
import com.cm.dto.out.QuestaoDTOut;
import com.cm.modelo.Questao;
import com.cm.modelo.Tema;
import com.cm.service.QuestaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.annotations.ApiIgnore;

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


    @GetMapping
    public Page<Questao> questoes(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
            @ApiIgnore Pageable paginacao,
            @RequestParam(required = false) Integer page ,
            @RequestParam(required = false) String sort ,
            @RequestParam(required = false) Integer size ,
            @RequestParam(required = false) Sort.Direction direction
    ){
        return  service.questoes(paginacao);
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
