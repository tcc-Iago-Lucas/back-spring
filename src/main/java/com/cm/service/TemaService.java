package com.cm.service;


import java.util.Optional;

import com.cm.controller.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cm.controller.dto.TemaDTO;
import com.cm.modelo.Tema;
import com.cm.repository.TemaRepository;
import com.cm.service.exceptions.ObjectNotFoundException;

@Service
public class TemaService {
	
	@Autowired private TemaRepository repo;

	public Tema create(TemaDTO temaDTO) {
		Tema t = new Tema(temaDTO.getDescricao());
		return repo.save(t);
	}

	public Tema show(Long id) {
		  Optional<Tema> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Tema não encontrado com esse id: " + id));
	}

	public void update(Long id, TemaDTO temaDTO) {
		Tema t = show(id);
		t.setTema(temaDTO.getDescricao());
		repo.save(t);
		
	}

	public void delete(Long id) {
		Tema t = show(id);
		if(t.getDesempenhos().isEmpty()){
			repo.delete(t);
		}else{
			throw new BadRequestException("Não é possivel apagar esse tema, ele tem questões atrelados a ele");
		}

		
	}


    public Page<Tema> temas(Pageable paginacao) {
		return repo.findAll(paginacao);
    }
}
