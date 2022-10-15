package com.cm.service;

import com.cm.modelo.*;
import com.cm.repository.DesempenhoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class DesempenhoServiceTest {

    public static final String TESTE = "teste";
    @InjectMocks
    private DesempenhoService service;

    @Mock
    private DesempenhoRepository repo;
    @Mock
    private UserTurmaService userTurmaService;

    private Long id = Long.valueOf(1);

    List<Resposta> getRespostas(Boolean zero){
        List<Resposta> respostas = new ArrayList<>();
        Resposta resposta = new Resposta();
        Tema tema = new Tema();
        tema.setId(id);
        tema.setTema(TESTE);
        tema.setCodigo(TESTE);
        resposta.setTema(tema);
        resposta.setAcertou(false);
        if(zero){
            resposta.setAcertou(false);
            respostas.add(resposta);
            return respostas;
        }else{
            resposta.setAcertou(true);
            respostas.add(resposta);
            return respostas;
        }

    }

    @Test
    void calcularPorTemaRespostaVazia(){
        List<Resposta> respostas = new ArrayList<>();
        try{
            service.calcularPorTema(respostas, new UserTurma());
        }catch (Exception e){
            assertEquals("respostas vazio", e.getMessage());
        }

    }

    @Test
    void calcularPorTemaDesempenhoZero(){
        List<Resposta> respostas = getRespostas(true);
        Desempenho desempenho = service.calculaDesempenho(respostas, getUserTurma());
       assertEquals(0, desempenho.getAproveitamento());
    }

    @Test
    void CalcularPorTemaDesempenho(){
        Desempenho desempenho = service.calculaDesempenho(getRespostas(false),getUserTurma());
        assertEquals(1, desempenho.getAproveitamento());
    }

    private UserTurma getUserTurma() {
        UserTurma userTurma = new UserTurma();
        Turma turma = new Turma();
        turma.setCodigo(TESTE);
        User user = new User();
        user.setId(Long.valueOf(1));
        userTurma.setTurma(turma);
        userTurma.setUser(user);
        return userTurma;
    }


}