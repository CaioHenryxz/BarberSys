package br.com.barbearia.leao.repository;

import br.com.barbearia.leao.model.Usuario;
import java.util.Optional; // Importe esta classe
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // O Spring Data JPA cria a implementação deste método automaticamente
    // apenas por causa do nome "findByEmail".
    Optional<Usuario> findByEmail(String email);

}