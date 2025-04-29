package com.crud.controller;

import com.crud.entity.User;
import com.crud.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class ReportAllUserController {

    private final UserRepository userRepository;

    public ReportAllUserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Rota responsável por gerar um relatório de todos os usuários
    @GetMapping("/report/all")
    public ResponseEntity<List<User>> getAllUser(){
        // Busca por todos os Usuários
        List<User> existingUsers = this.userRepository.findAll();
        // Verifica se há usuários na lista
        // Caso contrário, retorna uma mensagem de erro com código (404)
        if(existingUsers.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        // Caso haja usuários na lista, é retornando um código 200 e a lista dos mesmos
        return ResponseEntity.ok(existingUsers);
    }
}
