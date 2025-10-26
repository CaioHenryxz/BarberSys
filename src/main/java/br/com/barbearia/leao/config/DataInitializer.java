package br.com.barbearia.leao.config;

import br.com.barbearia.leao.model.Servico;
import br.com.barbearia.leao.model.Usuario;
import br.com.barbearia.leao.model.UsuarioRole;
import br.com.barbearia.leao.repository.ServicoRepository;
import br.com.barbearia.leao.repository.UsuarioRepository;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Bloco 1: Cria os serviços se o banco estiver vazio
        if (servicoRepository.count() == 0) {
            System.out.println(">>> Populando o banco de dados com TODOS os serviços...");

            Servico corte = new Servico();
            corte.setNome("Corte de Cabelo");
            corte.setDescricao("Corte moderno ou clássico (40min)");
            corte.setPreco(new BigDecimal("35.00"));
            corte.setDuracaoEmMinutos(40);

            Servico barba = new Servico();
            barba.setNome("Barba");
            barba.setDescricao("Design de sua preferência (30min)");
            barba.setPreco(new BigDecimal("35.00"));
            barba.setDuracaoEmMinutos(30);

            Servico sobrancelha = new Servico();
            sobrancelha.setNome("Sobrancelha");
            sobrancelha.setDescricao("Complemente seu visual! (10min)");
            sobrancelha.setPreco(new BigDecimal("25.00"));
            sobrancelha.setDuracaoEmMinutos(10);

            Servico nevou = new Servico();
            nevou.setNome("Nevou");
            nevou.setDescricao("Atualize seu estilo com o nevou! (60min)");
            nevou.setPreco(new BigDecimal("130.00"));
            nevou.setDuracaoEmMinutos(60);

            Servico pigmentacao = new Servico();
            pigmentacao.setNome("Pigmentação");
            pigmentacao.setDescricao("Intensifique a coloração do seu cabelo! (20min)");
            pigmentacao.setPreco(new BigDecimal("15.00"));
            pigmentacao.setDuracaoEmMinutos(20);

            Servico corteInfantil = new Servico();
            corteInfantil.setNome("Corte Infantil");
            corteInfantil.setDescricao("Deixe seu pequeno no estilo! (60min)");
            corteInfantil.setPreco(new BigDecimal("45.00"));
            corteInfantil.setDuracaoEmMinutos(60);
            
            Servico luzes = new Servico();
            luzes.setNome("Luzes");
            luzes.setDescricao("Diferencie seu visual! (60min)");
            luzes.setPreco(new BigDecimal("85.00"));
            luzes.setDuracaoEmMinutos(60);

            Servico bigode = new Servico();
            bigode.setNome("Bigode");
            bigode.setDescricao("Deixe seu bigode ajustado! (10min)");
            bigode.setPreco(new BigDecimal("10.00"));
            bigode.setDuracaoEmMinutos(10);

            Servico combo1 = new Servico();
            combo1.setNome("Combo 1");
            combo1.setDescricao("Corte de cabelo + Barba + Sobrancelha (60min)");
            combo1.setPreco(new BigDecimal("70.00"));
            combo1.setDuracaoEmMinutos(60);

            Servico combo2 = new Servico();
            combo2.setNome("Combo 2");
            combo2.setDescricao("Corte de cabelo + Nevou + Sobrancelha (2hrs)");
            combo2.setPreco(new BigDecimal("55.00"));
            combo2.setDuracaoEmMinutos(120);

            Servico combo3 = new Servico();
            combo3.setNome("Combo 3");
            combo3.setDescricao("Corte + Pigmentação + sobrancelha (60min)");
            combo3.setPreco(new BigDecimal("60.00"));
            combo3.setDuracaoEmMinutos(60);

            Servico combo4 = new Servico();
            combo4.setNome("Combo 4");
            combo4.setDescricao("Corte de cabelo + Bigode + Sobrancelha (40min)");
            combo4.setPreco(new BigDecimal("45.00"));
            combo4.setDuracaoEmMinutos(40);
            
            List<Servico> todosOsServicos = Arrays.asList(
                corte, barba, sobrancelha, nevou, pigmentacao, corteInfantil, luzes, bigode,
                combo1, combo2, combo3, combo4
            );
            
            servicoRepository.saveAll(todosOsServicos);
            
            System.out.println(">>> Banco de dados populado com " + todosOsServicos.size() + " serviços!");
        }

        // Bloco 2: Cria o usuário administrador se ele não existir
        if (usuarioRepository.findByEmail("admin@barbearia.com").isEmpty()) {
            System.out.println(">>> Criando usuário ADMIN padrão...");
            Usuario admin = new Usuario();
            admin.setNome("Barbeiro Admin");
            admin.setEmail("admin@barbearia.com");
            admin.setSenha(passwordEncoder.encode("admin123"));
            admin.setTelefone("00000000000");
            admin.setRole(UsuarioRole.ADMIN);
            usuarioRepository.save(admin);
            System.out.println(">>> Usuário ADMIN criado com sucesso!");
        }
    }
}