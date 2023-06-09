package digimonbattlesimulator.team;

import digimonbattlesimulator.digimon.Digimon;

import java.util.List;

public abstract class TeamBuilder implements TeamObserver {
    protected String name;
    protected int maxTeamSize;

    public TeamBuilder(String name, int maxTeamSize) {
        this.name = name;
        this.maxTeamSize = maxTeamSize;
    }

    @Override
    public void update(Digimon digimon, boolean isDigimonAdded) {
        if (isDigimonAdded) {
            System.out.println(digimon.getName() + " has been added to the team");
        } else {
            System.out.println(digimon.getName() + " has been removed from the team");
        }
        // TODO something like alert that digimon has been added or removed
    }

    public String getName() {
        return name;
    }

    public int getMaxTeamSize() {
        return maxTeamSize;
    }
}