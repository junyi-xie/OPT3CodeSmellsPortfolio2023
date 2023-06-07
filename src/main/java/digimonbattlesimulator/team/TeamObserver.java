package digimonbattlesimulator.team;

import digimonbattlesimulator.digimon.Digimon;

import java.util.List;

public interface TeamObserver {
    void update(List<Digimon> digimon, boolean lastMemberAdded);
}