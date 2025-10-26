package br.com.barbearia.leao.service;

import br.com.barbearia.leao.dto.UsuarioCadastroDTO;
import br.com.barbearia.leao.model.Usuario;
import br.com.barbearia.leao.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.barbearia.leao.model.UsuarioRole;

@Service // Anotação que marca esta classe como um componente de serviço (lógica de negócio)
public class UsuarioService {

    @Autowired // Injeção de dependência: O Spring vai nos dar uma instância do Repository
    private UsuarioRepository usuarioRepository;

    @Autowired // O Spring também nos dará o PasswordEncoder que configuramos
    private PasswordEncoder passwordEncoder;

    public Usuario cadastrar(UsuarioCadastroDTO dadosCadastro) {
        // 1. Cria uma nova instância da nossa Entidade Usuario
        Usuario novoUsuario = new Usuario();

        // 2. Copia os dados do DTO para a Entidade
        novoUsuario.setNome(dadosCadastro.getNome());
        novoUsuario.setEmail(dadosCadastro.getEmail());
        novoUsuario.setTelefone(dadosCadastro.getTelefone());

        // 3. CRIPTOGRAFA a senha antes de salvar!
        novoUsuario.setSenha(passwordEncoder.encode(dadosCadastro.getSenha()));
        novoUsuario.setRole(UsuarioRole.CLIENTE);

        // 4. Salva o novo usuário no banco de dados usando o repository
        return usuarioRepository.save(novoUsuario);
    }
}