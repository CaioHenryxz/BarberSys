package br.com.barbearia.leao.service;

import br.com.barbearia.leao.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    // Pega o valor da nossa chave secreta do application.properties
    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario) {
        try {
            // Define o algoritmo de assinatura usando nossa chave secreta
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            
            String token = JWT.create()
                    .withIssuer("API barbearia.leao") // Quem está gerando o token
                    .withSubject(usuario.getEmail()) // Quem é o "dono" do token (o usuário)
                    .withExpiresAt(dataExpiracao()) // Define a data de validade
                    .sign(algoritmo); // Assina o token
            return token;
            
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
    }
    
    // Método para validar o token e extrair o Subject (email do usuário)
    // Usaremos isso mais tarde!
    public String getSubject(String tokenJWT) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("API barbearia.leao")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (Exception exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }

    private Instant dataExpiracao() {
        // Define que o token vai expirar em 2 horas
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}