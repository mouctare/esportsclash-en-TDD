package esportsclash.pratique.player.infrastructure.persistance.ram;

import esportsclash.pratique.core.infrastructure.persistance.ram.InMemoryBaseRepository;
import esportsclash.pratique.player.application.ports.PlayerRepository;
import esportsclash.pratique.player.domain.model.Player;



public class InMemoryPlayerRepository extends InMemoryBaseRepository<Player> implements PlayerRepository {



}