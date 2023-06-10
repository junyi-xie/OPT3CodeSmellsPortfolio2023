package digimonbattlesimulator.digimon;

import java.util.List;

public class Birdramon extends Digimon {
    public Birdramon(int hitpoints, int attack, int defense, int agility) {
        super(hitpoints, attack, defense, agility);
    }

    @Override
    public String getName() {
        return "Birdramon";
    }

    @Override
    public String getCharacteristic() { return "Graceful Giant Bird"; }

    @Override
    public String getSpritePath() {
        // return "/digimonbattlesimulator/sprites/birdramon_sprite.gif";
        return "/digimonbattlesimulator/sprites/birdramon_sprite.png";
    }

    @Override
    public Ability getAbility() {
        return Ability.DELTA;
    }

    @Override
    public Type getType() {
        return Type.AIR;
    }

    @Override
    protected List<AttackTechnique> createAttackTechniques() {
        return List.of(
            new AttackTechnique("/digimonbattlesimulator/images/15_electric_chute_icon.png", "Electric Chute", "Shoots a ball of energy at enemies.", 70, Type.AIR),
            new AttackTechnique("/digimonbattlesimulator/images/16_wing_shoes_icon.png", "Wing Shoes", "Sends a pleasing wind to the feet of you and your allies, increasing speed.", 60, Type.AIR),
            new AttackTechnique("/digimonbattlesimulator/images/17_wind_cutter_icon.png", "Wind Cutter", "Attack that looses a cutting whirlwind at the target.", 80, Type.AIR),
            new AttackTechnique("/digimonbattlesimulator/images/18_electric_cloud_icon.png", "Electric Cloud", "Attacks with strikes of lightning from thunderclouds gathered over the target's head.", 100, Type.AIR),
            new AttackTechnique("/digimonbattlesimulator/images/19_tailwind_icon.png", "Tailwind", "Sends a powerful wind to the feet of you and your allies, increasing speed.", 90, Type.AIR),
            new AttackTechnique("/digimonbattlesimulator/images/20_thunderstorm_icon.png", "Thunderstorm", "Shoots lightning from all directions, dealing much damage to a wide range of enemies.", 150, Type.AIR),
            new AttackTechnique("/digimonbattlesimulator/images/21_hurricane_icon.png", "Hurricane", "Raises a powerful hurricane, dealing much damage to a wide range of enemies.", 150, Type.AIR)
        );
    }
}