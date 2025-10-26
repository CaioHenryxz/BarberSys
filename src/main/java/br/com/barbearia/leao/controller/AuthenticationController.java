package br.com.barbearia.leao.controller;

import br.com.barbearia.leao.dto.LoginDTO;
import br.com.barbearia.leao.dto.LoginResponseDTO; // <-- NOVO IMPORT
import br.com.barbearia.leao.model.Usuario;
import br.com.barbearia.leao.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    // ALTERAÇÃO: O método agora retorna o nosso novo DTO
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);
        
        // Pega o objeto completo do utilizador que foi autenticado
        var usuario = (Usuario) authentication.getPrincipal();
        
        // Gera o token
        var tokenJWT = tokenService.gerarToken(usuario);

        // Cria o objeto de resposta com o token E o perfil do utilizador
        var response = new LoginResponseDTO(tokenJWT, usuario.getRole());

        // Retorna a resposta completa
        return ResponseEntity.ok(response);
    }
}