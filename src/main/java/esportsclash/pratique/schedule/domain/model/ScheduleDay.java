package esportsclash.pratique.schedule.domain.model;

import esportsclash.pratique.player.domain.model.BaseEntity;
import esportsclash.pratique.team.domain.Team;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class ScheduleDay extends BaseEntity<ScheduleDay> {
    private LocalDate day;
    private Map<Moment, Match> matches;

    public ScheduleDay(String id, LocalDate day){
        super(id);
        this.day = day;
        this.matches = new EnumMap<>(Moment.class);
    }
    public ScheduleDay(String id, LocalDate day, Map<Moment, Match> matches){
        super(id);
        this.day = day;
        this.matches = matches;
    }
    public Match organizeAmeeting(Team team1, Team team2, Moment moment){
      // Vérifier si le moment est déjà pris
      if (matches.containsKey(moment)){
          throw new IllegalStateException("Moment " + moment + " is already taken");
      }

      // Vérifier si l'une des équipes joue déjà
      Optional<Match> anyTeamAlreadyPlays = matches.values().stream()
              .filter(match -> match.featuresTeam(team1.getId()) || match.featuresTeam(team2.getId()))
              .findFirst();

      if (anyTeamAlreadyPlays.isPresent()) {
          var match = anyTeamAlreadyPlays.get();
          Team teamPlaying = null;
          if (match.featuresTeam(team1.getId())) {
              teamPlaying = team1;
          } else if (match.featuresTeam(team2.getId())) {
              teamPlaying = team2;
          }
          throw new IllegalStateException("Team " +  teamPlaying.getName() + " is already playing");
      }

      // Vérifier si les équipes sont complètes
      if (!team1.isComplete() || !team2.isComplete()) {
          throw new IllegalStateException("One of the teams is not complete");
      }
      // Créer un nouveau match et l'ajouter au calendrier
      var newMatch = new Match(UUID.randomUUID().toString(), team1.getId(), team2.getId());
      matches.put(moment, newMatch);

      return newMatch;
  }
  public void cancelAmeeting(String matchId){
     var moment = matches.keySet().stream()
             .filter(m -> matches.get(m).getId().equals(matchId))
             .findFirst();
     moment.ifPresent(matches::remove);

    }

    @Override
    public ScheduleDay deepClone() {
        return new ScheduleDay(
                getId(),
                day,
                matches.keySet().stream()
                        .collect(
                                () -> new EnumMap<>(Moment.class),
                                (map, moment) -> map.put(moment, matches.get(moment).deepClone()),
                                Map::putAll
                        )
        );
    }

    public Optional<Match> getAt(Moment moment) {
        return matches.containsKey(moment) ?
                Optional.of(matches.get(moment)) :
                Optional.empty();
    }
    public LocalDate getDay(){
        return day;
    }
}
