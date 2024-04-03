package esportsclash.pratique.schedule.application.usecases;

import an.awesome.pipelinr.Command;
import esportsclash.pratique.auth.domain.model.viewModel.IdResponse;
import esportsclash.pratique.schedule.domain.model.Moment;

import java.time.LocalDate;

public class OrganizeMatchCommand implements Command<IdResponse> {
    private LocalDate date;

    private Moment moment;

    private String firstTeamId;

    private String secondTeamId;

    public OrganizeMatchCommand(LocalDate date, Moment moment, String firstTeam, String secondTeamId) {
        this.date = date;
        this.moment = moment;
        this.firstTeamId = firstTeam;
        this.secondTeamId = secondTeamId;
    }

    public LocalDate getDate() {
        return date;
    }

    public Moment getMoment() {
        return moment;
    }

    public String getFirstTeamId() {
        return firstTeamId;
    }

    public String getSecondTeamId() {
        return secondTeamId;
    }
}
