package hodlene.k8s.todo_h2_web_application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("{noop}dummy").roles("USER", "ADMIN");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login", "/h2-console/**").permitAll()
                        .requestMatchers("/", "/todo/**").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .formLogin(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headersConfigurer ->
                        headersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
                );

        return http.build();
    }
}
