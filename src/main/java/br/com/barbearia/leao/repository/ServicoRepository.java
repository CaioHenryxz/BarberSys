package br.com.barbearia.leao.repository;

import br.com.barbearia.leao.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
}