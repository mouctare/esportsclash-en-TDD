package esportsclash.pratique.schedule.application.usecases;

import an.awesome.pipelinr.Command;
import esportsclash.pratique.auth.domain.model.viewModel.IdResponse;
import esportsclash.pratique.core.domain.exception.NotFoundException;
import esportsclash.pratique.schedule.application.ports.ScheduleDayRepository;
import esportsclash.pratique.schedule.domain.model.ScheduleDay;
import esportsclash.pratique.team.application.ports.TeamRepository;

import java.util.UUID;

public class OrganizeMatchCommandHandler implements Command.Handler<OrganizeMatchCommand, IdResponse> {
    private final TeamRepository teamRepository;

    private final ScheduleDayRepository scheduleDayRepository;

    public OrganizeMatchCommandHandler(TeamRepository teamRepository, ScheduleDayRepository scheduleDayRepository) {
        this.teamRepository = teamRepository;
        this.scheduleDayRepository = scheduleDayRepository;
    }

    @Override
    public IdResponse handle(OrganizeMatchCommand command) {
        var team1 = teamRepository.findById(command.getFirstTeamId()).orElseThrow(
                () -> new NotFoundException("Team", command.getFirstTeamId())
        );
        var team2 = teamRepository.findById(command.getSecondTeamId()).orElseThrow(
                ()  -> new NotFoundException("Team", command.getSecondTeamId())
        );

        // Récupération du scheduleDay par la date s'il n'éxiste pas je crée un
        var scheduleDay = scheduleDayRepository.findByDate(command.getDate()).orElse(
                new ScheduleDay(UUID.randomUUID().toString(), command.getDate())
        );

       var organizeMatch = scheduleDay.organizeAmeeting(team1, team2, command.getMoment());
        scheduleDayRepository.save(scheduleDay);
        return new IdResponse(organizeMatch.getId());
    }
}
