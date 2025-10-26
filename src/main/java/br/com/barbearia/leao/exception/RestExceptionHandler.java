package br.com.barbearia.leao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice // Anotação que torna esta classe um "conselheiro" global para todos os controllers
public class RestExceptionHandler {

    // Este método será chamado especificamente quando uma IllegalStateException for lançada
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Object> handleIllegalStateException(IllegalStateException ex) {
        // Cria uma resposta JSON simples com a mensagem da exceção
        Map<String, String> body = Map.of("erro", ex.getMessage());
        
        // Retorna a resposta com o status HTTP 400 (Bad Request)
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}


// @RestControllerAdvice: Diz ao Spring: "Fique de olho em todas as exceções que acontecerem nos meus Controllers".

//@ExceptionHandler(IllegalStateException.class): Diz: "Se a exceção que você pegar for do tipo IllegalStateException, execute este método aqui".

//O método simplesmente pega a mensagem da exceção ("Horário indisponível...") e a coloca em um corpo de resposta JSON, retornando o status 400 Bad Request, que é o correto para esta situação.