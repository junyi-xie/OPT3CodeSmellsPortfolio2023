package digimonbattlesimulator.digimon;

import java.util.List;

public abstract class Digimon {
    protected int hitpoints;

    protected int attack;

    protected int defense;

    protected int agility;

    protected List<AttackTechnique> attackTechniques;

    public Digimon(int hitpoints, int attack, int defense, int agility) {
        this.hitpoints = hitpoints;
        this.attack = attack;
        this.defense = defense;
        this.agility = agility;
        this.attackTechniques = createAttackTechniques();
    }

    protected void showStats() {
        System.out.println("Stats:");
        System.out.println("----------");
        System.out.printf("Hitpoints: %-5d%n", hitpoints);
        System.out.printf("Attack: %-5d%n", attack);
        System.out.printf("Defense: %-5d%n", defense);
        System.out.printf("Agility: %-5d%n", agility);
    }

    protected void showAttackTechniques() {
        System.out.println("Attack Techniques:");
        System.out.println("---------------");
        System.out.printf("%-15s %-10s %-10s%n", "Name", "Power", "Type");
        for (AttackTechnique attackTechnique : attackTechniques) {
            System.out.printf("%-15s %-10d %-10s%n", attackTechnique.getName(), attackTechnique.getPower(), attackTechnique.getType());
        }
        System.out.println();
    }

    protected abstract String getName();

    protected abstract Ability getAbility();

    protected abstract Type getType();

    protected abstract List<AttackTechnique> createAttackTechniques();
}