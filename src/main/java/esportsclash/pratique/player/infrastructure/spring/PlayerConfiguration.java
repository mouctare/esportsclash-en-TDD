package esportsclash.pratique.player.infrastructure.spring;

import esportsclash.pratique.player.infrastructure.persistance.ram.InMemoryPlayerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlayerConfiguration {
    @Bean
    public InMemoryPlayerRepository playerRepository(){
        return new InMemoryPlayerRepository();
    }
}
