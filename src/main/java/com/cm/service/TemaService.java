package com.cm.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cm.dto.SelectTemasDTO;
import com.cm.dto.out.TemaDTOut;
import com.cm.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cm.dto.TemaDTOIn;
import com.cm.modelo.Tema;
import com.cm.repository.TemaRepository;
import com.cm.exception.ObjectNotFoundException;

@Service
public class TemaService {
	
	@Autowired private TemaRepository repo;

	public Tema create(TemaDTOIn temaDTO) {
		Tema t = new Tema(temaDTO);
		return repo.save(t);
	}

	public Tema find(Long id){
		Optional<Tema> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Tema não encontrado com esse id: " + id));

	}
	public TemaDTOut show(Long id) {
		 Tema t = find(id);
		 return new TemaDTOut(t);
	}

	public void update(Long id, TemaDTOIn temaDTO) {
		Tema t = find(id);
		t.setTema(temaDTO.getDescricao());
		repo.save(t);
		
	}

	public void delete(Long id) {
		Tema t = find(id);
		if(t.getDesempenhos().isEmpty()){
			repo.delete(t);
		}else{
			throw new BadRequestException("Não é possivel apagar esse tema, ele tem questões atrelados a ele");
		}

		
	}


    public Page<Tema> temas(Pageable paginacao) {
		return repo.findAll(paginacao);
    }

    public List<SelectTemasDTO> selectTemas() {
		List<SelectTemasDTO> selectTemasDTOS = new ArrayList<>();
		List<Tema> temas = repo.findAll();
		temas.forEach(t -> {
			if(!t.getTemaJogo()) {
				SelectTemasDTO selectTemasDTO = new SelectTemasDTO(t);
				selectTemasDTOS.add(selectTemasDTO);
			}
		});
		return  selectTemasDTOS;
    }

	public Integer totalTemas(){
		return repo.total();
	}
}
