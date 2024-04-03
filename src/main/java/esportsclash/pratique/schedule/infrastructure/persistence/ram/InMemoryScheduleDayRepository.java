package esportsclash.pratique.schedule.infrastructure.persistence.ram;

import esportsclash.pratique.core.infrastructure.persistance.ram.InMemoryBaseRepository;
import esportsclash.pratique.schedule.application.ports.ScheduleDayRepository;
import esportsclash.pratique.schedule.domain.model.ScheduleDay;

import java.time.LocalDate;
import java.util.Optional;

public class InMemoryScheduleDayRepository extends InMemoryBaseRepository<ScheduleDay> implements ScheduleDayRepository {
    @Override
    public Optional<ScheduleDay> findByMatchId(String matchId) {
        return Optional.empty();
    }

    @Override
    public Optional<ScheduleDay> findByDate(LocalDate date) {
        return entities.values().stream()
                .filter(schedule -> schedule.getDay().equals(date))
                .findFirst();
    }
}
