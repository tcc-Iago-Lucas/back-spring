package com.cm.service;

import java.util.Optional;

import com.cm.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cm.dto.CadastrarDTO;
import com.cm.dto.UserDTO;
import com.cm.modelo.User;
import com.cm.repository.UserRepository;
import com.cm.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;

	@Autowired
	private TokenService tokenService;
	
	
	
	public User create(CadastrarDTO cadastro) {
		verificacoes(cadastro);

		User u = new User(cadastro);
		BCryptPasswordEncoder hashPassowrd = new BCryptPasswordEncoder();
		u.setSenha(hashPassowrd.encode(cadastro.getPassword()));
		return repo.save(u);
		
		
	}
	private void verificaoEmaileUserName(String email, String userName) {
		Optional<User> op = repo.findByEmail(email);
		if(userName.isEmpty() ){
			throw  new BadRequestException("UserName vazio");
		}
		if(!userName.isEmpty() && userName.length() > 15){
			throw  new BadRequestException("UserName maior que o permitodo");
		}
		if(op.isPresent()){
			throw  new BadRequestException("Usuario já existe com esse email: " + email);
		}
		op = repo.findByNickname(userName);
		if(op.isPresent()){
			throw  new BadRequestException("Usuario já existe com esse userName: " + userName);
		}
	}


	private void verificacoes(CadastrarDTO cadastrarDTO){

		if(cadastrarDTO.getPassword().isEmpty()){
			throw  new BadRequestException("senha  vazia");
		}
		if(!cadastrarDTO.getPassword().isEmpty() && cadastrarDTO.getPassword().length() > 15){
			throw  new BadRequestException("senha maior que o permitodo");
		}
		verificaoEmaileUserName(cadastrarDTO.getEmail(), cadastrarDTO.getUserName());

	}



	public UserDTO show(Long id) {
		// TODO Auto-generated method stub
		
		
		UserDTO u = new UserDTO(find(id));
		return u;
	}


	public User find(Long id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Usuario não encontrado com esse id: " + id));
	}
	public void update(Long id, UserDTO u) {
		User user = find(id);
		if(u.getEmail() != null && !u.getEmail().isEmpty()) {
			verificaoEmaileUserName(u.getEmail(),u.getUserName());
			user.setEmail(u.getEmail());
		}
		if(u.getName() != null &&  !u.getName().isEmpty()) {
			user.setNome(u.getName());
		}
		
		 repo.save(user);
	}



	public void delete(Long id) {
		User user = find(id);
		if(user.getTurmas().isEmpty()){
			repo.delete(user);
		}else{
			throw  new BadRequestException("Usuario cadastrado em uma turma");
		}

		
	}

    public UserDTO profile(String token) {

		return show(tokenService.getIdUser(token));
    }
}
