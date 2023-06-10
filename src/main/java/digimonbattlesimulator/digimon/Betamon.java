package digimonbattlesimulator.digimon;

import java.util.List;

public class Betamon extends Digimon {
    public Betamon(int hitpoints, int attack, int defense, int agility) {
        super(hitpoints, attack, defense, agility);
    }

    @Override
    public String getName() {
        return "Betamon";
    }

    @Override
    public String getSpritePath() {
        return "/digimonbattlesimulator/sprites/betamon_sprite.gif";
        // return "/digimonbattlesimulator/sprites/betamon_sprite.png";
    }

    @Override
    public Ability getAbility() {
        return Ability.OVERGROW;
    }

    @Override
    public Type getType() {
        return Type.NATURE;
    }

    @Override
    protected List<AttackTechnique> createAttackTechniques() {
        return List.of(
            new AttackTechnique("22_poison_powder_icon.png", "Poison Powder", "Attacks enemies in front with a poisonous mist.", 60, Type.NATURE),
            new AttackTechnique("23_earth_coat_icon.png", "Earth Coat", "Wraps you and your allies in stone, increasing stamina.", 80, Type.NATURE),
            new AttackTechnique("24_bio_field_icon.png", "Bio Field", "Attack that surrounds the attacker with a poisonous mist.", 90, Type.NATURE),
            new AttackTechnique("25_root_bind_icon.png", "Root Bind", "Attack that brings forth a clinging ivy to cover the victim.", 110, Type.NATURE),
            new AttackTechnique("26_rock_fall_icon.png", "Rock Fall", "Attack that drops giant boulders on the target's head.",130, Type.NATURE),
            new AttackTechnique("27_earth_power_icon.png", "Eath Power", "Wraps attacker and allies in stone, increasing stamina.",130, Type.NATURE),
            new AttackTechnique("28_venom_disaster_icon.png", "Venom Disaster", "Brings forth a tornado of poisonous mist, dealing much damage to a wide range of enemies.",150, Type.NATURE)
        );
    }
}