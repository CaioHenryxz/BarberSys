package br.com.barbearia.leao.dto;

import br.com.barbearia.leao.model.UsuarioRole;

// Esta classe representa a resposta que enviaremos após um login bem-sucedido.
public class LoginResponseDTO {
    private final String token;
    private final UsuarioRole role;

    public LoginResponseDTO(String token, UsuarioRole role) {
        this.token = token;
        this.role = role;
    }

    // Getters
    public String getToken() {
        return token;
    }

    public UsuarioRole getRole() {
        return role;
    }
}