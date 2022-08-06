package com.cm.service;

import com.cm.modelo.User;
import com.cm.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AutenticacaoServiceTest {

    @InjectMocks
    private AutenticacaoService service;

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
    @Test
    void  loadUserByUsernameUserNotFound(){
        when(repository.findByEmail(email)).thenReturn(Optional.empty());
        try {
            service.loadUserByUsername(email);
        }catch (Exception e) {
            assertEquals("Dados inválidos!", e.getMessage());
        }
    }

    @Test
    void loadUserByUsernameUser(){
        when(repository.findByEmail(email)).thenReturn(Optional.of(getUser()));
        UserDetails response = service.loadUserByUsername(email);
        assertTrue(response.isAccountNonExpired());
        assertTrue(response.isAccountNonLocked());
        assertTrue( response instanceof UserDetails);
    }
}