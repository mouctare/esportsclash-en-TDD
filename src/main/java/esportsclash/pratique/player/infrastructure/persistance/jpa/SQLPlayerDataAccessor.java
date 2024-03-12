package esportsclash.pratique.player.infrastructure.persistance.jpa;

import esportsclash.pratique.player.domain.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SQLPlayerDataAccessor extends JpaRepository<Player, String> {
}
