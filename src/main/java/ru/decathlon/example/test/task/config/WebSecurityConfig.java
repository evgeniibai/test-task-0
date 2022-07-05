package ru.decathlon.example.test.task.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.Map;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        String defaultEncoder = "noop";
        /*
            NoOpPasswordEncoder: for educational purposes.
         */
        Map<String, PasswordEncoder> encoders = Map.of(
                "noop", org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance(),
                "argon2", new Argon2PasswordEncoder(),
                "bcrypt", new BCryptPasswordEncoder(12),
                "pbkdf2", new Pbkdf2PasswordEncoder("BAY", 32),
                "scrypt", new SCryptPasswordEncoder()
        );

        return new DelegatingPasswordEncoder(defaultEncoder, encoders);
    }
}
