package com.cm.service;

import com.cm.controller.dto.AlternativaDTO;
import com.cm.modelo.Alternativa;
import com.cm.modelo.Questao;
import com.cm.repository.AlternativaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlternativaServiceTest {
    @InjectMocks
    private AlternativaService service;

    @Mock
    private AlternativaRepository repository;

    private Long id = Long.valueOf(1);

    Questao getQuestao() {
        return new Questao();
    }

    AlternativaDTO getAlternativaDTO(Boolean certo) {
        AlternativaDTO alternativaDTO = new AlternativaDTO();
        alternativaDTO.setAlternativa("teste");
        alternativaDTO.setCerto(certo);
        return alternativaDTO;
    }

    private Alternativa getAlternativa(boolean certo) {
        Alternativa alternativa = new Alternativa();
        alternativa.setAlternativa("teste");
        alternativa.setCerto(certo);
        alternativa.setId(id);
        return alternativa;
    }

    @Test
    void criandoAlternativaJuntoComQuestao() {
        List<AlternativaDTO> alternativaDTOS = new ArrayList<>();

        alternativaDTOS.add(getAlternativaDTO(false));
        alternativaDTOS.add(getAlternativaDTO(true));
        service.criandoAlternativaJuntoComQuestao(getQuestao(), alternativaDTOS);

        verify(repository, times(2)).save(any(Alternativa.class));
    }

    @Test
    void AlternativaNotfound() {
        when(repository.findById(id)).thenReturn(Optional.empty());
        try {
            service.find(id);
        } catch (Exception e) {
            assertEquals("Alternativa não encontrada com esse id: " + id, e.getMessage());
        }
    }

    @Test
    void AlternativaFind() {
        when(repository.findById(id)).thenReturn(Optional.of(getAlternativa(true)));
        Alternativa response = service.find(id);
        assertEquals(id, response.getId());
        assertTrue(response.getCerto());
        assertEquals("teste", response.getAlternativa());
    }

    @Test
    void deleteAlternativaCorreta() {
        when(repository.findById(id)).thenReturn(Optional.of(getAlternativa(true)));
        try {
            service.delete(id);
        } catch (Exception e) {
            assertEquals("A questão não pode ficar sem a alternativa correta", e.getMessage());
        }
    }

    @Test
    void deleteAlternativaQuestaoFicandoComMenosDeDuasAlternativas() {
        Questao questao = new Questao();
        Alternativa alternativafalse = getAlternativa(false);
        questao.getAlternativas().add(alternativafalse);
        questao.getAlternativas().add(getAlternativa(true));
        alternativafalse.setQuestao(questao);

        when(repository.findById(id)).thenReturn(Optional.of(alternativafalse));
        try {
            service.delete(id);
        } catch (Exception e) {
            assertEquals("A questão não pode ficar com menos de duas alternativas", e.getMessage());
        }

    }

    @Test
    void deleteAlternativa() {
        Questao questao = new Questao();
        Alternativa alternativafalse = getAlternativa(false);
        questao.getAlternativas().add(alternativafalse);
        questao.getAlternativas().add(getAlternativa(true));

        questao.getAlternativas().add(getAlternativa(false));
        alternativafalse.setQuestao(questao);

        when(repository.findById(id)).thenReturn(Optional.of(alternativafalse));

        service.delete(id);
        verify(repository, times(1)).delete(any(Alternativa.class));
    }

}