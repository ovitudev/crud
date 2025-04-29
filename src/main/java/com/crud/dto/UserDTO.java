package com.crud.dto;


import com.crud.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

// Substitui Getters e Setters padrões
@Getter
@Setter
// Classe responsável por receber informações do Front
// Garantindo segurança e integrabilidade, além de transferir apenas necessários para
// a classe responsável por sincronizar com o banco (User)
public class UserDTO {

    @NotBlank(message = "Nome não pode estar em branco!")
    private String username;

    @NotBlank(message = "Senha não pode estar vazia!")
    private String password;

    @NotBlank(message = "E-mail não pode estar vazio!")
    @Email(message = "E-mail inválido!")
    private String email;

    @NotBlank
    private String nickname;

    public User toUserDTO() {
        User user = new User();

        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setNickname(nickname);

        return user;
    }
}
