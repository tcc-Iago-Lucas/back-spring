package com.cm.service;

import com.cm.controller.dto.AlternativaDTO;
import com.cm.modelo.Alternativa;
import com.cm.modelo.Questao;
import com.cm.repository.AlternativaRepository;
import com.cm.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlternativaService {
    @Autowired private AlternativaRepository repo;
    public void criadoJuntoQuestao(Questao questao, List<AlternativaDTO> alternativaDTOS) {

        alternativaDTOS.forEach(a -> {
            Alternativa alternativa = new Alternativa(a);
            alternativa.setQuestao(questao);
            repo.save(alternativa);
        });

    }

    public Alternativa find(Long id) {

        Optional<Alternativa> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Questao não encontrada com esse id: " + id));
    }
   public void delete(Long id){
        Alternativa alternativa = find(id);
        repo.delete(alternativa);
   }
}
