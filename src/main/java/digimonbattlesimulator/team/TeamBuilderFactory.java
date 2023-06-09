package digimonbattlesimulator.team;

public class TeamBuilderFactory {
    public static Team createRegularTeam(String name, int maxTeamSize) {
        TeamBuilder teamBuilder = new RegularTeamBuilder(name, maxTeamSize);
        return new Team(teamBuilder);
    }

    public static Team createCustomTeam(String name, int maxTeamSize) {
        TeamBuilder teamBuilder = new CustomTeamBuilder(name, maxTeamSize);
        return new Team(teamBuilder);
    }
}