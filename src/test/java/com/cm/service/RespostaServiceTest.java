package com.cm.service;

import com.cm.dto.RespostaDTO;
import com.cm.modelo.*;
import com.cm.repository.RespostaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class RespostaServiceTest {
    @InjectMocks
    private RespostaService service;


    @Mock
    private TokenService tokenService;
    @Mock
    private UserService userService;
    @Mock
    private AlternativaService alternativaService;
    @Mock
    private RespostaRepository repo;
    @Mock
    private DesempenhoService desempenhoService;

    private String TESTE ="teste_para_esseservico";
    private Long id = Long.valueOf(1);


    @Test
    void responderUsuarioNaoTemTurmaAtiva () {
        when(tokenService.getIdUser(TESTE.substring(7, TESTE.length()))).thenReturn(id);
        when(userService.find(id)).thenReturn(new User());
            try{
                service.responder(TESTE,getRespostaDTO());
            }catch (Exception e){
                assertEquals("O usuario não está em nenhuma turma ativa", e.getMessage());
            }
    }

    @Test
    void responderJaRespondeu () {
        when(tokenService.getIdUser(TESTE.substring(7, TESTE.length()))).thenReturn(id);
        when(userService.find(id)).thenReturn(getUser());
        when( alternativaService.findByCodigo(TESTE)).thenReturn(getAlternativa());
        when(repo.jaRespondeu(any(Questao.class), any(UserTurma.class))).thenReturn(new Resposta());
        try{
            service.responder(TESTE,getRespostaDTO());
        }catch (Exception e){
            assertEquals("Você já respondeu esse questão nessa turma", e.getMessage());
        }
    }

    @Test
    void responder () {
        List<Resposta> respostas = new ArrayList<>();
        respostas.add(new Resposta(getAlternativa(), new UserTurma()));
        when(tokenService.getIdUser(TESTE.substring(7, TESTE.length()))).thenReturn(id);
        when(userService.find(id)).thenReturn(getUser());
        when( alternativaService.findByCodigo(TESTE)).thenReturn(getAlternativa());
        when(repo.jaRespondeu(any(Questao.class), any(UserTurma.class))).thenReturn(null);
       when(repo.TemaRespondido(any(Tema.class), any(UserTurma.class))).thenReturn(respostas);
        service.responder(TESTE,getRespostaDTO());
        verify(repo, times(1)).save(any(Resposta.class));
        verify(desempenhoService, times(1)).calcularPorTema(any(List.class), any(UserTurma.class));

    }

    private Alternativa getAlternativa() {
        Alternativa alternativa = new Alternativa();
        Questao questao = new Questao();
        Tema tema = new Tema();
        List<Questao> questaoList = new ArrayList<>();
        List<Alternativa> alternativas = new ArrayList<>();
        questaoList.add(questao);
        tema.setQuestaos(questaoList);
        questao.setTema(tema);

        alternativa.setQuestao(questao);
        alternativas.add(alternativa);
        questao.setAlternativas(alternativas);

        return  alternativa;
    }

    private User getUser() {
        User user = new User();
        Turma turma = new Turma();
        UserTurma userTurma = new UserTurma();
        turma.setId(id);
        user.setId(id);
        turma.setAtivo(true);

        userTurma.setTurma(turma);
        userTurma.setUser(user);
        Set<UserTurma> userTurmas = new HashSet<>();
        userTurmas.add(userTurma);
        List<Turma> turmas = new ArrayList<>();
        turma.setUsuariosTurma(userTurmas);
        turmas.add(turma);
        user.setTurmas(turmas);
        return  user;
    }

    private RespostaDTO getRespostaDTO() {
      return new RespostaDTO(TESTE,TESTE);
    }


}