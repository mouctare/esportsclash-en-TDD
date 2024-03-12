package esportsclash.pratique.player.infrastructure.spring;

import esportsclash.pratique.player.infrastructure.persistance.jpa.SQLPlayerDataAccessor;
import esportsclash.pratique.player.infrastructure.persistance.jpa.SQLPlayerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlayerConfiguration {
    @Bean
    public SQLPlayerRepository playerRepository(SQLPlayerDataAccessor sqlPlayerDataAccessor){
        return new SQLPlayerRepository(sqlPlayerDataAccessor);
    }
}
