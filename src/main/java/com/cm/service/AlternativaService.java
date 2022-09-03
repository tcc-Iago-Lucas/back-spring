package com.cm.service;

import com.cm.dto.AlternativaDTO;
import com.cm.exception.BadRequestException;
import com.cm.modelo.Alternativa;
import com.cm.modelo.Questao;
import com.cm.repository.AlternativaRepository;
import com.cm.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AlternativaService {
    @Autowired
    private AlternativaRepository repo;

    public void criandoAlternativaJuntoComQuestao(Questao questao, List<AlternativaDTO> alternativaDTOS) {

        alternativaDTOS.forEach(a -> {
            Alternativa alternativa = new Alternativa(a);
            alternativa.setQuestao(questao);
            repo.save(alternativa);
        });

    }

    public Alternativa find(Long id) {

        Optional<Alternativa> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Alternativa não encontrada com esse id: " + id));
    }

    public void delete(Long id) {
        Alternativa alternativa = find(id);
        if (alternativa.getCerto()) {
            throw new BadRequestException("A questão não pode ficar sem a alternativa correta");
        }
        if (alternativa.getQuestao().getAlternativas().size() <= 2) {
            throw new BadRequestException("A questão não pode ficar com menos de duas alternativas");
        }
        repo.delete(alternativa);

    }

    @Transactional
    public void deleteJuntoComQuestao(Long id) {
        Alternativa alternativa = find(id);
        repo.delete(alternativa);
    }
}
