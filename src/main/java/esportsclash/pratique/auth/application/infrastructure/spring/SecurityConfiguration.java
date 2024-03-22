package esportsclash.pratique.auth.application.infrastructure.spring;

import esportsclash.pratique.auth.application.services.jwtservice.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity, JwtService jwtService) throws Exception {
        httpSecurity
                .addFilterBefore(
                        new JwtAuthenticationFilter(jwtService),
                        UsernamePasswordAuthenticationFilter.class
                )
                .authorizeHttpRequests(authorize -> authorize.requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(authorize -> authorize.disable())
                .httpBasic(authorize -> authorize.disable())
                .csrf(authorize -> authorize.disable())
                .sessionManagement(authorize -> authorize.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return httpSecurity.build();


    }

}
