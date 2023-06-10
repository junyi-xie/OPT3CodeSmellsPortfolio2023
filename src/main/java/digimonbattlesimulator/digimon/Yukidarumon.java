package digimonbattlesimulator.digimon;

import java.util.List;

public class Yukidarumon extends Digimon {
    public Yukidarumon(int hitpoints, int attack, int defense, int agility) {
        super(hitpoints, attack, defense, agility);
    }

    @Override
    public String getName() {
        return "Yukidarumon";
    }

    @Override
    public String getSpritePath() {
        return "/digimonbattlesimulator/sprites/yukidarumon_sprite.gif";
        // return "/digimonbattlesimulator/sprites/yukidarumon_sprite.png";
    }

    @Override
    public Ability getAbility() {
        return Ability.TORRENT;
    }

    @Override
    public Type getType() {
        return Type.ICE;
    }

    @Override
    protected List<AttackTechnique> createAttackTechniques() {
        return List.of(
            new AttackTechnique("8_bubble_breath_icon.png", "Bubble Breath", "Bathes enemies in front in a powerful water breath attack.", 80, Type.ICE),
            new AttackTechnique("9_hail_spear_icon.png", "Hail Spear", "Attack that raises painfully sharp icicles from under the target's feet.", 70, Type.ICE),
            new AttackTechnique("10_splash_icon.png", "Splash", "Attack that blasts enemies in front with water at high pressure.", 50, Type.ICE),
            new AttackTechnique("11_waterfall_icon.png", "Waterfall", "Attack that drops in a flow of high-pressure water on the target's head.", 90, Type.ICE),
            new AttackTechnique("12_heavy_rain_icon.png", "Heavy Rain", "Attack that brings powerful torrential rains to a wide area.", 130, Type.ICE),
            new AttackTechnique("13_ice_statue_icon.png", "Ice Statue", "Attack that results in giant icicles erupting at the target's feet.", 110, Type.ICE),
            new AttackTechnique("13_aurora_freeze_icon.png", "Aurora Freeze", "Raises a powerful blizzard of water and ice, dealing much damage to a wide range of enemies.", 150, Type.ICE)
        );
    }
}