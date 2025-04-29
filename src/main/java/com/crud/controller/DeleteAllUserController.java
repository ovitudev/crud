package com.crud.controller;

import com.crud.entity.User;
import com.crud.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class DeleteAllUserController {

    private final UserRepository userRepository;

    public DeleteAllUserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Rota para excluir TODOS os usuários
    @DeleteMapping("/delete/all")
    public ResponseEntity<?> deleteAllUsers(){
        List<User> existingUsers = this.userRepository.findAll(); // Busco por todos os usuários
        // Verifico se a Lista está vazia, e caso esteja retorna uma mensagem de erro (404)
        if(existingUsers.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        // Caso contrário, executo o metodo de exclusão de todos os usuários encontrados na lista
        // E retorno um valor de sucesso (200)
        userRepository.deleteAll();
        return ResponseEntity.ok().build();
    }
}
