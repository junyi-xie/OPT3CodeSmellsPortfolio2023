package digimonbattlesimulator.team;

import digimonbattlesimulator.digimon.Digimon;

import java.util.List;

class TeamBuilder implements TeamObserver {

    @Override
    public void update(List<Digimon> team, boolean isDigimonAdded) {
        System.out.println();
    }
}
