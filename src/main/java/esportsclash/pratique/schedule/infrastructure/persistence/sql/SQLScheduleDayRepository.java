package esportsclash.pratique.schedule.infrastructure.persistence.sql;

import esportsclash.pratique.core.infrastructure.persistance.sql.SQLBaseRepository;
import esportsclash.pratique.schedule.application.ports.ScheduleDayRepository;
import esportsclash.pratique.schedule.domain.model.ScheduleDay;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.Optional;

public class SQLScheduleDayRepository extends SQLBaseRepository<ScheduleDay> implements ScheduleDayRepository {
    private final EntityManager entityManager;

    public SQLScheduleDayRepository(EntityManager entityManager, EntityManager entityManager1) {
        super(entityManager);
        this.entityManager = entityManager1;
    }

    @Override
    public Class<ScheduleDay> getEntityClass() {
        return ScheduleDay.class;
    }

    @Override
    public Optional<ScheduleDay> findByMatchId(String matchId) {
        return Optional.empty();
    }

    @Override
    public Optional<ScheduleDay> findByDate(LocalDate date) {
        return Optional.empty();
    }
}
