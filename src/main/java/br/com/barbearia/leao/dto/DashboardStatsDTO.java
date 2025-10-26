package br.com.barbearia.leao.dto;

import java.math.BigDecimal;

// Esta classe simples serve para transportar os dados do dashboard.
public class DashboardStatsDTO {

    private long agendamentosHoje;
    private BigDecimal ganhosDiarios;
    private BigDecimal ganhosSemanais;
    private BigDecimal ganhosMensais;

    // Getters e Setters
    public long getAgendamentosHoje() { return agendamentosHoje; }
    public void setAgendamentosHoje(long agendamentosHoje) { this.agendamentosHoje = agendamentosHoje; }
    public BigDecimal getGanhosDiarios() { return ganhosDiarios; }
    public void setGanhosDiarios(BigDecimal ganhosDiarios) { this.ganhosDiarios = ganhosDiarios; }
    public BigDecimal getGanhosSemanais() { return ganhosSemanais; }
    public void setGanhosSemanais(BigDecimal ganhosSemanais) { this.ganhosSemanais = ganhosSemanais; }
    public BigDecimal getGanhosMensais() { return ganhosMensais; }
    public void setGanhosMensais(BigDecimal ganhosMensais) { this.ganhosMensais = ganhosMensais; }
}