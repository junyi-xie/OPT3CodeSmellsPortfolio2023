package digimonbattlesimulator.digimon;

import java.util.List;

public class Agumon extends Digimon {
    public Agumon(int hitpoints, int attack, int defense, int agility) {
        super(hitpoints, attack, defense, agility);
    }

    @Override
    public String getName() {
        return "Agumon";
    }

    @Override
    public String getSpritePath() {
        // return "/digimonbattlesimulator/sprites/agumon_sprite.gif";
        return "/digimonbattlesimulator/sprites/agumon_sprite.png";
    }

    @Override
    public Ability getAbility() {
        return Ability.BLAZE;
    }

    @Override
    public Type getType() {
        return Type.ICE;
    }

    @Override
    protected List<AttackTechnique> createAttackTechniques() {
        return List.of(
            new AttackTechnique("1_heat_breath_icon.png", "Heat Breath", "Spews a very hot breath of flames at enemies in front.", 70, Type.FIRE),
            new AttackTechnique("2_fire_tower_icon.png", "Fire Tower", "Attack that results in a pillar of flame erupting at the target's feet.", 70, Type.FIRE),
            new AttackTechnique("3_firewall_icon.png", "Firewall", "Attack that surrounds the attacker with a wall of fire.", 80, Type.FIRE),
            new AttackTechnique("4_burning_heart_icon.png", "Burning Heart", "Enflames the hearts of allies, boosting their strength.", 100, Type.FIRE),
            new AttackTechnique("5_inferno_icon.png", "Inferno", "Attack that surrounds the attacker with the flames of hell.",130, Type.FIRE),
            new AttackTechnique("6_magma_bomb_icon.png", "Magma Bomb", "Rains bolts of lava from the sky, dealing enemies much damage.",150, Type.FIRE),
            new AttackTechnique("7_flame_storm_icon.png", "Flame Storm", "Raises a storm of flames, dealing much damage to a wide range of enemies.",150, Type.FIRE)
        );
    }
}