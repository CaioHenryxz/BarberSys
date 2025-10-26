package br.com.barbearia.leao.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record AgendamentoDTO(
    Long servicoId,
    LocalDate data,
    LocalTime hora,
    String observacoes
) {}