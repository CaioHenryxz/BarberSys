package br.com.barbearia.leao.repository;

import br.com.barbearia.leao.model.Agendamento;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List; // <-- NOVO IMPORT
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    boolean existsByDataAndHora(LocalDate data, LocalTime hora);

    // MÉTODO NOVO: O Spring Data JPA cria a busca automaticamente
    // Ele entende que deve procurar agendamentos onde o campo "usuario.id"
    // seja igual ao id que passamos como parâmetro.
    List<Agendamento> findByUsuarioId(Long usuarioId);
    List<Agendamento> findByData(LocalDate data);
    
    @Query("SELECT a FROM Agendamento a WHERE YEAR(a.data) = :ano AND MONTH(a.data) = :mes ORDER BY a.data, a.hora")
    List<Agendamento> findByYearAndMonth(@Param("ano") int ano, @Param("mes") int mes);
}