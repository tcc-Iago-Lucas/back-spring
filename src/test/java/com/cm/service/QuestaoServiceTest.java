package com.cm.service;

import com.cm.controller.dto.AlternativaDTO;
import com.cm.controller.dto.QuestaoDTO;
import com.cm.modelo.Alternativa;
import com.cm.modelo.Questao;
import com.cm.modelo.Tema;
import com.cm.repository.QuestaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestaoServiceTest {

    @InjectMocks
    private QuestaoService service;

    @Mock
    private QuestaoRepository repository;
    @Mock
    private AlternativaService alternativaService;
    @Mock
    private TemaService temaService;

    private Long id = Long.valueOf(1);
    private String TESTE = "teste";

    List<AlternativaDTO> getAlternativaDTOS() {
        List<AlternativaDTO> alternativaDTOS = new ArrayList<>();
        alternativaDTOS.add(new AlternativaDTO());
        alternativaDTOS.add(new AlternativaDTO());
        return alternativaDTOS;
    }

    QuestaoDTO getQuestaoDTO(Boolean temId, Boolean temAlternativas) {
        QuestaoDTO questaoDTO = new QuestaoDTO();
        if (temId) questaoDTO.setTemaId(id);
        questaoDTO.setEnuciado(TESTE);
        if (temAlternativas) questaoDTO.setAlternativaDTOS(getAlternativaDTOS());
        return questaoDTO;
    }

    Tema getTema() {
        Tema tema = new Tema();
        tema.setTema(TESTE);
        tema.setId(id);
        return tema;
    }
    List<Alternativa> getAlternativas(){
        List<Alternativa> alternativas = new ArrayList<>();
        Alternativa alternativa = new Alternativa();
        alternativa.setId(id);
        alternativas.add(alternativa);
        alternativas.add(alternativa);
        return  alternativas;
    }
    Questao getQuestao() {
        Questao questao = new Questao();
        questao.setEnuciado(TESTE);
        questao.setId(id);
        questao.setTema(getTema());
        questao.setAlternativas(getAlternativas());
        return questao;
    }
    @Test
    void criandoQuestaoSemAlternativas() {
        try {
            service.create(getQuestaoDTO(false, false));
        } catch (Exception e) {
            assertEquals(
                    "A questão deve ter pelo menos duas alternativa",
                    e.getMessage());
        }
    }

    @Test
    void criandoQuestaoSemTemaId() {
        try {
            service.create(getQuestaoDTO(false, true));
        } catch (Exception e) {
            assertEquals(
                    "A questao precisa de um tema, por favor informe o id do tema",
                    e.getMessage());
        }
    }

    @Test
    void criandoQuestao() {
        when(temaService.show(id)).thenReturn(getTema());
        when(repository.save(any(Questao.class))).thenReturn(getQuestao());
        Questao response = service.create(getQuestaoDTO(true, true));

        assertEquals(id, response.getId());
        assertEquals(TESTE, response.getEnuciado());
        assertEquals(TESTE, response.getTema().getTema());
        verify(repository, times(1)).save(any(Questao.class));
        verify(alternativaService,
                times(1)
        ).criandoAlternativaJuntoComQuestao(any(Questao.class), any(List.class));

    }

    @Test
    void findNotFound(){
        when(repository.findById(id)).thenReturn(Optional.empty());
        try {
            service.find(id);
        }catch (Exception e){
            assertEquals("Questao não encontrada com esse id: " + id , e.getMessage());
        }
    }

    @Test
    void find(){
        when(repository.findById(id)).thenReturn(Optional.of(getQuestao()));
        Questao response = service.find(id);

        assertEquals(TESTE, response.getEnuciado());
        assertEquals(id , response.getId() );

    }

    @Test
    void update(){
        when(repository.findById(id)).thenReturn(Optional.of(getQuestao()));
        service.update(id, getQuestaoDTO(true, true));
        verify(repository,times(1)).save(any(Questao.class));
    }

    @Test
    void delete() {
        when(repository.findById(id)).thenReturn(Optional.of(getQuestao()));
        service.delete(id);
        verify(repository, times(1)).delete(any(Questao.class));
        verify(alternativaService, times(2)).deleteJuntoComQuestao(any(Long.class));

    }


}