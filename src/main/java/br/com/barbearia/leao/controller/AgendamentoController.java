package br.com.barbearia.leao.controller;

import br.com.barbearia.leao.dto.AgendamentoDTO;
import br.com.barbearia.leao.model.Agendamento;
import br.com.barbearia.leao.model.Usuario;
import br.com.barbearia.leao.repository.AgendamentoRepository; // <-- MUDANÇA AQUI
import br.com.barbearia.leao.service.AgendamentoService;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping; // <-- NOVO IMPORT
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List; // <-- NOVO IMPORT
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/agendamentos")
@CrossOrigin(origins = "*")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired // <-- NOVA INJEÇÃO
    private AgendamentoRepository agendamentoRepository;

    @PostMapping
    public ResponseEntity<Agendamento> agendar(@RequestBody AgendamentoDTO dados, @AuthenticationPrincipal Usuario usuarioLogado) {
        Agendamento novoAgendamento = agendamentoService.agendar(dados, usuarioLogado);
        return ResponseEntity.ok(novoAgendamento);
    }

    // MÉTODO NOVO PARA "MEUS AGENDAMENTOS"
    @GetMapping("/meus")
    public ResponseEntity<List<Agendamento>> meusAgendamentos(@AuthenticationPrincipal Usuario usuarioLogado) {
        // Usa o novo método do repositório para buscar os agendamentos do usuário logado
        var agendamentos = agendamentoRepository.findByUsuarioId(usuarioLogado.getId());
        return ResponseEntity.ok(agendamentos);
    }
    
    @GetMapping("/horarios-ocupados")
    public ResponseEntity<List<String>> horariosOcupados(@RequestParam("data") LocalDate data) {
    // Busca no banco todos os agendamentos para a data fornecida
    List<Agendamento> agendamentosDoDia = agendamentoRepository.findByData(data);

    // Extrai apenas as horas desses agendamentos e coloca numa lista de texto
    List<String> horariosOcupados = agendamentosDoDia.stream()
            .map(agendamento -> agendamento.getHora().toString())
            .toList();
            
    return ResponseEntity.ok(horariosOcupados);
}
}