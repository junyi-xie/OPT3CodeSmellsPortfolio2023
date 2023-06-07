package digimonbattlesimulator.team;

import digimonbattlesimulator.digimon.Digimon;

import java.util.ArrayList;
import java.util.List;

class Team {
    private final List<Digimon> digimonTeam;

    private final List<TeamObserver> observers;

    private boolean isDigimonAdded;

    public Team() {
        digimonTeam = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public void addDigimon(Digimon digimon) {
        digimonTeam.add(digimon);
        isDigimonAdded = true;
        notifyObservers();
    }

    public void removeDigimon(Digimon digimon) {
        digimonTeam.remove(digimon);
        isDigimonAdded = false;
        notifyObservers();
    }

    public int getTeamSize() {
        return digimonTeam.size();
    }

    public void printTeam() {
        System.out.println("Teamopstelling:");
    }

    public void addObserver(TeamObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(TeamObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (TeamObserver observer : observers) {
            observer.update(digimonTeam, isDigimonAdded);
        }
    }
}