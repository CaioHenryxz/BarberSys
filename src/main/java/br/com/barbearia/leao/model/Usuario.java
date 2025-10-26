package br.com.barbearia.leao.model;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    private String senha;
    private String telefone;

    @Enumerated(EnumType.STRING)
    private UsuarioRole role;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public UsuarioRole getRole() { return role; }
    public void setRole(UsuarioRole role) { this.role = role; }

    // ==== MÉTODOS UserDetails CORRIGIDOS ====
    @Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    // LÓGICA CORRIGIDA: Agora os perfis são exclusivos.
    if (this.role == UsuarioRole.ADMIN) {
        // Admin tem APENAS a permissão de Admin.
        return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
    } else {
        // Cliente tem APENAS a permissão de Cliente.
        return List.of(new SimpleGrantedAuthority("ROLE_CLIENTE"));
    }
}

    @Override
    public String getPassword() { return this.senha; }
    @Override
    public String getUsername() { return this.email; }
    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }
}