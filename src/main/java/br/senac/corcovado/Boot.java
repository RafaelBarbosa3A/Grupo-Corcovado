package br.senac.corcovado;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Boot {

    public static void main(String[] args) {
        SpringApplication.run(Boot.class, args);
    }

    @Bean
    public CommandLineRunner populateDB() {
        return (String[] args) -> {

        };
    }
;
}
