package br.com.martins.criptografia.config;

import br.com.martins.criptografia.entity.UserEntity;
import br.com.martins.criptografia.repository.UserRepository;
import br.com.martins.criptografia.util.GenerateCpfUtil;
import com.github.javafaker.Faker;
import com.github.javafaker.Finance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Configuration
public class UserPreLoadConfig {
    private static final Logger log = LoggerFactory.getLogger(UserPreLoadConfig.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {
        Finance finance = new Faker().finance();

        return args -> {

            log.info("Preloading " + repository.save(new UserEntity(GenerateCpfUtil.generateCpf(),finance.creditCard().replace("-",""), 3000D)));
        };
    }
}
