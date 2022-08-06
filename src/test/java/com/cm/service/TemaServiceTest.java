package com.cm.service;

import com.cm.controller.dto.TemaDTO;
import com.cm.modelo.Questao;
import com.cm.modelo.Tema;
import com.cm.repository.TemaRepository;
import com.cm.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TemaServiceTest {

    @InjectMocks
    private TemaService service;

    @Mock
    private TemaRepository repository;

    private Long id = Long.valueOf(1);
    private String DESCRIPTION = "TESTE";


    Tema getTema(){
        Tema tema = new Tema();
        tema.setTema(DESCRIPTION);
        tema.setId(id);
        return  tema;
    }

    private TemaDTO getTemaDTO() {
        TemaDTO temaDTO = new TemaDTO();
        temaDTO.setDescricao(DESCRIPTION);
        return temaDTO;
    }


    @Test
    void showTemaNotFound(){
        when(repository.findById(id)).thenReturn(Optional.empty());
        try {
            service.show(id);
        }catch (Exception e){
            assertEquals("Tema não encontrado com esse id: " + id, e.getMessage());
            assertTrue(e instanceof ObjectNotFoundException);
        }
    }

    @Test
    void showTema(){
        when(repository.findById(id)).thenReturn(Optional.of(getTema()));
        Tema response = service.show(id);
        assertEquals(id, response.getId());
        assertEquals(DESCRIPTION, response.getTema());
    }

    @Test
    void updateTema(){
        when(repository.findById(id)).thenReturn(Optional.of(getTema()));
        service.update(id,getTemaDTO());
        verify(repository, times(1)).save(any(Tema.class));
    }

    @Test
    void deleteTemaComQuestoes(){
        List<Questao> questoes = new ArrayList<>();
        questoes.add(new Questao());
        Tema tema = getTema();
        tema.setQuestaos(questoes);
        when(repository.findById(id)).thenReturn(Optional.of(tema));
        try{
            service.delete(id);
        }catch (Exception e){
            assertEquals(
                    "Não é possivel apagar esse tema, ele tem questões atrelados a ele",
                    e.getMessage()
                    );
        }
    }

    @Test
    void deteleTemaSemQuestoes(){
        when(repository.findById(id)).thenReturn(Optional.of(getTema()));
        service.delete(id);
        verify(repository, times(1)).delete(any(Tema.class));
    }





}