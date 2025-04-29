package com.crud.controller;

import com.crud.dto.UserDTO;
import com.crud.entity.User;
import com.crud.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class RegisterUserController {

    private final UserRepository userRepository;

    public RegisterUserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Rota para realizar um novo registro de usuário
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO, BindingResult result) {
        // Esse laço irá verificar erros de inserção conforme os dados preenchidos
        // E caso 'result' contenha um erro, o sistema retorna um erro compativel
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        // Caso não tenha nenhum erro, realiza a adição de um usuário DTO ao User
        // O DTO foi importante para que não precisasse informar o ID no registro
        // e também para verificações como do @Email, verificando a veracidade das informações
        User user = userDTO.toUserDTO();
        userRepository.save(user); // Metodo responsável por salvar

        // Retorna, assim, uma mensagem de sucesso com código (200)
        return ResponseEntity.ok().body(Map.of("message", "User registered successfully!"));
    }
}
