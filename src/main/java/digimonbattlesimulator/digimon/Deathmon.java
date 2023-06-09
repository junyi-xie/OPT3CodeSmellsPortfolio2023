package digimonbattlesimulator.digimon;

import java.util.List;

public class Deathmon extends Digimon {
    public Deathmon(int hitpoints, int attack, int defense, int agility) {
        super(hitpoints, attack, defense, agility);
    }

    @Override
    public String getName() {
        return "Deathmon";
    }

    @Override
    public String getSpritePath() {
        return "/digimonbattlesimulator/sprites/deathmon_sprite.gif";
    }

    @Override
    public Ability getAbility() {
        return Ability.DARK_AURA;
    }

    @Override
    public Type getType() {
        return Type.DARK;
    }

    @Override
    protected List<AttackTechnique> createAttackTechniques() {
        return List.of(
            new AttackTechnique("29_dark_spirit_icon.png", "Dark Spirit", "Shoots a ball of darkness at enemies.", 70, Type.DARK),
            new AttackTechnique("30_blackout_icon.png", "Blackout", "Brings forth a dark mist to the surroundings, dealing damage to enemies.", 80, Type.DARK),
            new AttackTechnique("31_nightmare_icon.png", "Nightmare", "Brings forth an eye of darkness, whose stare deals damage, from above the victim's head.", 80, Type.DARK),
            new AttackTechnique("32_chaos_cloud_icon.png", "Chaos Cloud", "Attacks enemies in front with dark energy.", 90, Type.DARK),
            new AttackTechnique("33_shadow_fall_icon.png", "Shadow Fall", "Drops bolts of dark energy down on the target's head.", 110, Type.DARK),
            new AttackTechnique("34_hide_and_seek_icon.png", "Hide and Seek", "Attacks a wide range of enemies with a dark fear that turns enemy anger to allied Digimon.", 150, Type.DARK),
            new AttackTechnique("35_evil_squall_icon.png", "Evil Squall", "Brings forth powerful dark energy, dealing much damage to a wide range of enemies.", 150, Type.DARK)
        );
    }
}