package com.cm.service;

import com.cm.dto.CadastrarDTO;
import com.cm.dto.UserDTO;
import com.cm.modelo.Turma;
import com.cm.modelo.User;
import com.cm.repository.UserRepository;
import com.cm.exception.ObjectNotFoundException;
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
class UserServiceTest {


    @InjectMocks
    private  UserService service;

    @Mock
    private UserRepository repository;

    private Long id = Long.valueOf(1);
    private  String email = "teste@ts.com";
    private  String name = "name";



    private User getUser() {

        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setNome(name);

        return user;
    }
    private CadastrarDTO getCadastrarDTO(){
        CadastrarDTO cadastrarDTO = new CadastrarDTO();
        cadastrarDTO.setEmail(email);
        cadastrarDTO.setName(name);
        cadastrarDTO.setPassword("senha");
        return  cadastrarDTO;
    }

    private UserDTO getUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(email);
        userDTO.setName(name);
        userDTO.setId(id);
        return  userDTO;
    }

    @Test
    void createEmailJaCadastrado(){
        when(repository.findByEmail(email)).thenReturn(Optional.of(getUser()));
        try {
            service.create(getCadastrarDTO());
        }catch (Exception e ){
            assertEquals("Usuario já existe com esse email: " + email, e.getMessage());
        }
    }
    @Test
    void create(){
        when(repository.findByEmail(email)).thenReturn(Optional.empty());
        when(repository.save(any(User.class))).thenReturn(getUser());
        User response = service.create(getCadastrarDTO());
        assertEquals(email,response.getUsername());
        assertEquals(name,response.getNome());
    }

    @Test
    void findNotFound(){
        when(repository.findById(id)).thenReturn(Optional.empty());

        try{
            service.find(id);
        }catch (Exception e){
            assertEquals("Usuario não encontrado com esse id: " + id, e.getMessage());
            assertTrue(e instanceof ObjectNotFoundException);
        }
    }
    @Test
    void Show(){
        when(repository.findById(id)).thenReturn(Optional.of(getUser()));
        UserDTO response =  service.show(id);

        assertEquals(id, response.getId());
        assertEquals(name, response.getName());
        assertEquals(email, response.getEmail());
    }

    @Test
    void update(){
        when(repository.findByEmail(email)).thenReturn(Optional.empty());
        when(repository.findById(id)).thenReturn(Optional.of(getUser()));
        service.update(id,getUserDTO());
        verify(repository, times(1)).save(any(User.class));
    }

    @Test
    void deleteUsuarioCadastroEmUmaTurma(){
        List<Turma> turmas = new ArrayList<>();
        turmas.add(new Turma());
        User user = getUser();
        user.setTurmas(turmas);

        when(repository.findById(id)).thenReturn(Optional.of(user));
        try {
            service.delete(id);
        }catch (Exception e ){
            assertEquals("Usuario cadastrado em uma turma", e.getMessage());
        }
    }

    @Test
    void delete(){
        when(repository.findById(id)).thenReturn(Optional.of(getUser()));
        service.delete(id);
        verify(repository, times(1)).delete(any(User.class));
    }


}