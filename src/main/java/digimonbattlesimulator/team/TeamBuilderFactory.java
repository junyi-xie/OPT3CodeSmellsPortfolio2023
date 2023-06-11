package digimonbattlesimulator.team;

public class TeamBuilderFactory {
    public static Team createRegularTeam(String teamName, int maxTeamSize) {
        TeamBuilder teamBuilder = new RegularTeamBuilder(teamName, maxTeamSize);
        return new Team(teamBuilder);
    }

    public static Team createCustomTeam(String teamName, int maxTeamSize) {
        TeamBuilder teamBuilder = new CustomTeamBuilder(teamName, maxTeamSize);
        return new Team(teamBuilder);
    }
}