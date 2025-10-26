package br.com.barbearia.leao.controller;

import br.com.barbearia.leao.dto.UsuarioCadastroDTO;
import br.com.barbearia.leao.model.Usuario;
import br.com.barbearia.leao.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Combinação de @Controller e @ResponseBody, padrão para APIs REST
@RequestMapping("/api/usuarios") // Todas as rotas neste controller começarão com /api/usuarios
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastrar") // Mapeia requisições POST para /api/usuarios/cadastrar
    public ResponseEntity<Usuario> cadastrar(@RequestBody UsuarioCadastroDTO dados) {
        // @RequestBody avisa ao Spring para pegar os dados do corpo da requisição JSON
        Usuario usuarioSalvo = usuarioService.cadastrar(dados);
        // Retorna HTTP 200 OK com os dados do usuário salvo no corpo da resposta
        return ResponseEntity.ok(usuarioSalvo);
    }
}