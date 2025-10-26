package br.com.barbearia.leao.controller;

import br.com.barbearia.leao.dto.DashboardStatsDTO;
import br.com.barbearia.leao.model.Agendamento;
import java.math.BigDecimal; // NOVO IMPORT
import java.time.DayOfWeek; // NOVO IMPORT
import java.time.LocalDate; // NOVO IMPORT
import java.time.temporal.TemporalAdjusters;
import br.com.barbearia.leao.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @GetMapping("/agendamentos")
    public ResponseEntity<List<Agendamento>> listarTodosAgendamentos() {
        List<Agendamento> todosAgendamentos = agendamentoRepository.findAll();
        return ResponseEntity.ok(todosAgendamentos);
    }
    
    @GetMapping("/agendamentos/filtrados")
public ResponseEntity<List<Agendamento>> listarAgendamentosPorMes(
        @RequestParam("ano") int ano,
        @RequestParam("mes") int mes) {
    List<Agendamento> agendamentos = agendamentoRepository.findByYearAndMonth(ano, mes);
    return ResponseEntity.ok(agendamentos);
}
    
    @GetMapping("/estatisticas")
    public ResponseEntity<DashboardStatsDTO> getEstatisticas() {
        List<Agendamento> todosAgendamentos = agendamentoRepository.findAll();
        LocalDate hoje = LocalDate.now();

        // Define os limites da semana (de Domingo a Sábado)
        LocalDate inicioDaSemana = hoje.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        LocalDate fimDaSemana = hoje.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));

        // Calcula os totais
        long agendamentosHoje = todosAgendamentos.stream()
                .filter(ag -> ag.getData().equals(hoje))
                .count();

        BigDecimal ganhosDiarios = todosAgendamentos.stream()
                .filter(ag -> ag.getData().equals(hoje))
                .map(ag -> ag.getServico().getPreco())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal ganhosSemanais = todosAgendamentos.stream()
                .filter(ag -> !ag.getData().isBefore(inicioDaSemana) && !ag.getData().isAfter(fimDaSemana))
                .map(ag -> ag.getServico().getPreco())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        BigDecimal ganhosMensais = todosAgendamentos.stream()
                .filter(ag -> ag.getData().getMonth() == hoje.getMonth() && ag.getData().getYear() == hoje.getYear())
                .map(ag -> ag.getServico().getPreco())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Monta o objeto de resposta
        DashboardStatsDTO stats = new DashboardStatsDTO();
        stats.setAgendamentosHoje(agendamentosHoje);
        stats.setGanhosDiarios(ganhosDiarios);
        stats.setGanhosSemanais(ganhosSemanais);
        stats.setGanhosMensais(ganhosMensais);

        return ResponseEntity.ok(stats);
    }

}