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

    public String getHitpoints() {
        return Integer.toString(hitpoints);
    }

    public String getAttack() {
        return Integer.toString(attack);
    }

    public String getDefense() {
        return Integer.toString(defense);
    }

    public String getAgility() {
        return Integer.toString(agility);
    }

    public List<AttackTechnique> getAttackTechniques() {
        return attackTechniques;
    }

    public abstract String getName();

    public abstract String getCharacteristic();

    public abstract String getSpritePath();

    public abstract Ability getAbility();

    public abstract Type getType();

    protected abstract List<AttackTechnique> createAttackTechniques();
}