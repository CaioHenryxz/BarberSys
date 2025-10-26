package br.com.barbearia.leao.service;

import br.com.barbearia.leao.dto.AgendamentoDTO;
import br.com.barbearia.leao.model.Agendamento;
import br.com.barbearia.leao.model.AgendamentoStatus;
import br.com.barbearia.leao.model.Usuario;
import br.com.barbearia.leao.repository.AgendamentoRepository;
import br.com.barbearia.leao.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;
    @Autowired
    private ServicoRepository servicoRepository;

    public Agendamento agendar(AgendamentoDTO dados, Usuario cliente) {
        // SUA REGRA DE NEGÓCIO: Checar se o horário está disponível
        if (agendamentoRepository.existsByDataAndHora(dados.data(), dados.hora())) {
            throw new IllegalStateException("Horário indisponível. Por favor, escolha outro.");
        }

        var servico = servicoRepository.findById(dados.servicoId())
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado!"));

        var agendamento = new Agendamento();
        agendamento.setUsuario(cliente);
        agendamento.setServico(servico);
        agendamento.setData(dados.data());
        agendamento.setHora(dados.hora());
        agendamento.setObservacoes(dados.observacoes());
        agendamento.setStatus(AgendamentoStatus.CONFIRMADO); // Ou PENDENTE, se precisar de confirmação do admin

        return agendamentoRepository.save(agendamento);
    }
}