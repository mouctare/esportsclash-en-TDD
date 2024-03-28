package esportsclash.pratique.team.infrastructure.persistance.spring.config;

import esportsclash.pratique.team.application.ports.TeamRepository;
import esportsclash.pratique.team.infrastructure.persistance.jpa.SQLTeamRepository;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TeamAdaptersConfiguration {
    @Bean
    public TeamRepository teamRepository(EntityManager entityManager) {
        return new SQLTeamRepository(entityManager);
    }
}
