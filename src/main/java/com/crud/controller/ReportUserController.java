package com.crud.controller;

import com.crud.entity.User;
import com.crud.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class ReportUserController {

    private final UserRepository userRepository;

    public ReportUserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Rota responsável por gerar um relatório de um único usuário, pelo ID
    @GetMapping("/report/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        // Busca um usuário por ID
        User user = userRepository.findById(id).orElse(null);

        if (user != null) {
            return ResponseEntity.ok(user);  // Retorna o usuário com status 200
        } else {
            return ResponseEntity.notFound().build();  // Retorna 404 se não existir
        }
    }
}
