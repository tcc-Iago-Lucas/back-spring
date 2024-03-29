package com.cm.service;

import com.cm.dto.QuestaoDTO;
import com.cm.dto.out.QuestaoDTOut;
import com.cm.exception.BadRequestException;
import com.cm.modelo.Questao;
import com.cm.modelo.Tema;
import com.cm.repository.QuestaoRepository;
import com.cm.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class QuestaoService {
    @Autowired private QuestaoRepository repo;
    @Autowired private AlternativaService alternativaService;
    @Autowired private  TemaService temaService;
    public Questao create(QuestaoDTO questaoDTO) {

        if( Objects.isNull(questaoDTO.getAlternativas()) || questaoDTO.getAlternativas().size() < 2 ){
            throw  new BadRequestException("A questão deve ter pelo menos duas alternativa");
        }
        if(Objects.isNull(questaoDTO.getTemaId()) )
            throw  new BadRequestException("A questao precisa de um tema, por favor informe o id do tema");

        Tema tema = temaService.find(questaoDTO.getTemaId());
        if(tema.getTemaJogo()){
            throw  new BadRequestException("Não é possivel criar uma questão para esse tema , pois é tema do jogo");
        }
        Questao questao = new Questao();
        questao.setEnuciado(questaoDTO.getEnuciado());
        questao.setTema(tema);
        questao.setCodigo(questaoDTO.getCodigo());
        questao = repo.save(questao);
        alternativaService.criandoAlternativaJuntoComQuestao(questao, questaoDTO.getAlternativas());

        return questao;
    }

    public Questao find(Long id) {

        Optional<Questao> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Questao não encontrada com esse id: " + id));
    }

    public void update(Long id, QuestaoDTO questaoDTO) {
        Questao questao = find(id);
        questao.setEnuciado(questaoDTO.getEnuciado());
        repo.save(questao);
    }

    @Transactional
    public void delete(Long id) {
        Questao questao = find(id);
        if(!questao.getAlternativas().isEmpty()){
            throw new BadRequestException("essa questão tem alternativas, nao pode ser apagada");
        }
        repo.delete(questao);
    }

    public Page<Questao> questoes(Pageable paginacao) {
        return repo.findAll(paginacao);


    }
}
