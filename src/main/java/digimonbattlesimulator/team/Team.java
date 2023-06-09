package digimonbattlesimulator.team;

import digimonbattlesimulator.digimon.Digimon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private final ObservableList<Digimon> digimonTeam;
    private final List<TeamObserver> observers;
    private final TeamBuilder teamBuilder;
    private boolean isDigimonAdded;

    public Team(TeamBuilder teamBuilder) {
        digimonTeam = FXCollections.observableArrayList();
        observers = new ArrayList<>();
        this.teamBuilder = teamBuilder;
    }

    public void addDigimon(Digimon digimon) {
        digimonTeam.add(digimon);
        isDigimonAdded = true;
        notifyObservers(digimon);
    }

    public void removeDigimon(Digimon digimon) {
        digimonTeam.remove(digimon);
        isDigimonAdded = false;
        notifyObservers(digimon);
    }

    public ObservableList<Digimon> getDigimonTeam() {
        return digimonTeam;
    }

    public TeamBuilder getTeamBuilder() {
        return teamBuilder;
    }

    public void addObserver(TeamObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(TeamObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(Digimon digimon) {
        for (TeamObserver observer : observers) {
            observer.update(digimon, isDigimonAdded);
        }
    }
}