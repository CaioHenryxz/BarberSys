package br.com.barbearia.leao.dto;

// Esta classe serve apenas para receber os dados do formulário de cadastro.
public class UsuarioCadastroDTO {
    private String nome;
    private String email;
    private String senha;
    private String telefone;

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}