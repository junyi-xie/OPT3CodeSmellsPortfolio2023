package digimonbattlesimulator.team;

import digimonbattlesimulator.digimon.Digimon;

public interface TeamObserver {
    void update(Digimon digimon, boolean lastMemberAdded);
}