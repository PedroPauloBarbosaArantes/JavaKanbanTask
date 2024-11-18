package com.kanbanapi.security.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Esta sem a lógica para buscar o usuário a partir do banco de dados
        if ("user".equals(username)) {
            return new User("user", "$2a$10$Qf.kW9RLqEJ1i9V6E.wXHuNbxodf2.KC1u1mtN3IlEB24zMq1LfW.", new ArrayList<>()); // Senha codificada usando BCrypt
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado com o nome de usuário: " + username);
        }
    }
}
