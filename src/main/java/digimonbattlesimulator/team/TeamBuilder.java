package digimonbattlesimulator.team;

import digimonbattlesimulator.digimon.Digimon;

public abstract class TeamBuilder implements TeamObserver {
    protected String teamName;
    protected int maxTeamSize;

    public TeamBuilder(String teamName, int maxTeamSize) {
        this.teamName = teamName;
        this.maxTeamSize = maxTeamSize;
    }

    @Override
    public void update(Digimon digimon, boolean isDigimonAdded) {
        if (isDigimonAdded) {
            System.out.println(digimon.getName() + " has been added to the team");
        } else {
            System.out.println(digimon.getName() + " has been removed from the team");
        }
    }

    public String getTeamName() {
        return teamName;
    }

    public int getMaxTeamSize() {
        return maxTeamSize;
    }
}