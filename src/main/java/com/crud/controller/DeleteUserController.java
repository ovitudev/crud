package com.crud.controller;

import com.crud.entity.User;
import com.crud.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class DeleteUserController {

    private final UserRepository userRepository;

    public DeleteUserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Rota para delete um usuário pelo ID informado
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Integer id){
        // Tenta selecionar um usuário conforme o ID inserido
        Optional<User> existingUser = this.userRepository.findById(id);
        // Verifica se há um usuário com esse ID
        // e caso encontre realiza a exclusão apenas dele retornando um valor de sucesso (200)
        if(existingUser.isPresent()){
            this.userRepository.delete(existingUser.get());
            return ResponseEntity.ok().build();
        }

        // Caso não encontre é apresentado uma mensagem de erro e um valor de que não foi encontrado (404)
        return ResponseEntity.notFound().build();
    }
}
