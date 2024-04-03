package esportsclash.pratique.schedule.application.ports;

import esportsclash.pratique.core.infrastructure.persistance.BaseRepository;
import esportsclash.pratique.schedule.domain.model.ScheduleDay;

import java.time.LocalDate;
import java.util.Optional;

public interface ScheduleDayRepository extends BaseRepository<ScheduleDay> {
    Optional<ScheduleDay> findByMatchId(String matchId);

    Optional<ScheduleDay> findByDate(LocalDate date);
}
