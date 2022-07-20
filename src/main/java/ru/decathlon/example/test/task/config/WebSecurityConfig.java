package ru.decathlon.example.test.task.config;

import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.decathlon.example.test.task.model.Permission;

import java.util.Map;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    private static final String ASSETS = "/assets/**";
    private static final String INDEX_PAGE = "/";
    private static final String AUTH_LOGIN = "/auth/login";
    private static final String AUTH_LOGOUT = "/auth/logout";
    private static final String CREATE_ACCOUNT = "/account/create-account";
    private static final String HOME_PAGE = "/home"; //TODO
    private static final String DATABASE_URL = "/h2";

    @Bean
    @SneakyThrows
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        // @formatter:off
        http
                // It is generally BAD to disable CSRF protection!
                .csrf().disable()
                .authorizeRequests()
                    .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                    .antMatchers(ASSETS, INDEX_PAGE, CREATE_ACCOUNT).permitAll()
                    .antMatchers(DATABASE_URL).hasAuthority(Permission.WRITE.getAction())
                    .anyRequest()
                    .fullyAuthenticated()
                    .and()
                .formLogin()
                    .loginPage(AUTH_LOGIN).permitAll()
                    .defaultSuccessUrl(HOME_PAGE)
                    .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher(AUTH_LOGOUT, "POST"))
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl(INDEX_PAGE)
                    .and()
                .httpBasic()
                    .and()
                // add this line to use H2 web console
                .headers().frameOptions().disable();
        // @formatter:on
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        String defaultEncoder = "bcrypt";

        Map<String, PasswordEncoder> encoders = Map.of(
                "argon2", new Argon2PasswordEncoder(),
                "bcrypt", new BCryptPasswordEncoder(12),
                "pbkdf2", new Pbkdf2PasswordEncoder("decathlon", 32),
                "scrypt", new SCryptPasswordEncoder());

        return new DelegatingPasswordEncoder(defaultEncoder, encoders);
    }
}
