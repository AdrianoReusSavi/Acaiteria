package org.example.seeder;

import org.example.model.UnidadeMedida;
import org.example.model.QUnidadeMedida;
import org.example.repository.UnidadeMedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UnidadeMedidaSeeder implements CommandLineRunner {
    private final UnidadeMedidaRepository repository;

    @Autowired
    public UnidadeMedidaSeeder(UnidadeMedidaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        //adicionarNovaUnidadeMedida("500 ML", "Pote de 500 Mililitros");
    }

    private void adicionarNovaUnidadeMedida(String sigla, String descricao) {
        QUnidadeMedida qUnidadeMedida = QUnidadeMedida.unidadeMedida;
        boolean existe = repository.exists(
                qUnidadeMedida.sigla.eq(sigla)
                        .and(qUnidadeMedida.descricao.eq(descricao))
        );

        if (!existe) {
            UnidadeMedida unidadeMedida = new UnidadeMedida();
            unidadeMedida.setSigla(sigla);
            unidadeMedida.setDescricao(descricao);
            repository.save(unidadeMedida);
        }
    }
}