package com.crud.controller;

import com.crud.dto.UserDTO;
import com.crud.entity.User;
import com.crud.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UpdateUserController {

    private final UserRepository userRepository;

    public UpdateUserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Rota responsável por atualizar um usuário pelo ID
    @PutMapping("/update/{id}")
    private ResponseEntity<User> updateUserById(@PathVariable Integer id, @RequestBody UserDTO userDTO){
        // Verifica se o ID está vinculado a um Usuário
        // Se sim, atualiza as informações conforme o DTO recebido
        if(userRepository.findById(id).isPresent()){
            User user = userRepository.getReferenceById(id);
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());
            user.setEmail(userDTO.getEmail());
            user.setNickname(userDTO.getNickname());

            User userUpdate = userRepository.save(user); // User recebe as informações salvar
            return ResponseEntity.ok(userUpdate); // Retorna código HTTP 200
        }

        // Caso ocorra quaisquer erros, apresenta um código de erro
        return ResponseEntity.notFound().build();
    }
}
