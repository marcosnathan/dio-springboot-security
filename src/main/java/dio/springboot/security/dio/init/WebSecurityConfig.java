package dio.springboot.security.dio.init;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig {

    /*@Bean
    public UserDetailsService userDetailsService(
            BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(
                User.withUsername("say")
                        .password(bCryptPasswordEncoder.encode("123"))
                        .roles("USERS")
                        .build());

        manager.createUser(
                User.withUsername("adm")
                        .password(bCryptPasswordEncoder.encode("123"))
                        .roles("MANAGERS", "USERS").build());
        return manager;
    }*/

  /*  @Bean
    public AuthenticationManager authenticationManager(
            HttpSecurity httpSecurity,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            UserDetailsService userDetailsService
    ) throws Exception {


        return httpSecurity
                .getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();

    }*/

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorz -> authorz
                        .requestMatchers("/managers/**").hasRole("MANAGERS")
                        .requestMatchers("/users/**").hasAnyRole("USERS", "MANAGERS")
                        .anyRequest().authenticated())
                .formLogin(withDefaults());
        return http.build();
    }

}
