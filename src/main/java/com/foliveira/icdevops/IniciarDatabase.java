package com.foliveira.icdevops;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.foliveira.icdevops.model.Item;
import com.foliveira.icdevops.repository.ItemRepository;

@Configuration
public class IniciarDatabase {
    private static final Logger log = LoggerFactory.getLogger(IniciarDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ItemRepository repository) {

        return args -> {
            log.info("\nCarregando " + repository.save(
                new Item(
                    "Biscoito",
                    1.99,
                    2
                )
            ));
            log.info("\nCarregando " + repository.save(
                new Item(
                    "leite"
                )
            ));
        };
    }
}
