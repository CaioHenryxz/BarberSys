package br.com.barbearia.leao.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "agendamentos")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;
    private LocalTime hora;
    
    @Enumerated(EnumType.STRING) // Diz ao JPA para salvar o nome do Enum (ex: "CONFIRMADO") como texto
    private AgendamentoStatus status;
    
    private String observacoes;

    @ManyToOne // Relação: Muitos agendamentos podem pertencer a UM usuário.
    @JoinColumn(name = "usuario_id") // Cria a coluna de chave estrangeira no banco
    private Usuario usuario;

    @ManyToOne // Relação: Muitos agendamentos podem ser de UM serviço.
    @JoinColumn(name = "servico_id")
    private Servico servico;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }
    public AgendamentoStatus getStatus() { return status; }
    public void setStatus(AgendamentoStatus status) { this.status = status; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Servico getServico() { return servico; }
    public void setServico(Servico servico) { this.servico = servico; }
}