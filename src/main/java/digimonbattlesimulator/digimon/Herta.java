package digimonbattlesimulator.digimon;

import java.util.List;

public class Herta extends Digimon {
    public Herta(int hitpoints, int attack, int defense, int agility) {
        super(hitpoints, attack, defense, agility);
    }

    @Override
    public String getName() {
        return "Herta";
    }

    @Override
    public String getCharacteristic() { return "Kuru Kuru ~"; }

    @Override
    public String getSpritePath() {
        return "/digimonbattlesimulator/sprites/herta.gif";
    }

    @Override
    public Ability getAbility() {
        return Ability.HOLY_AURA;
    }

    @Override
    public Type getType() {
        return Type.HOLY;
    }

    @Override
    protected List<AttackTechnique> createAttackTechniques() {
        return List.of(
            new AttackTechnique("/digimonbattlesimulator/images/36_light_soul_icon.png", "Light Soul", "Shoots a ball of light energy at enemies.", 70, Type.HOLY),
            new AttackTechnique("/digimonbattlesimulator/images/37_saint_ray_icon.png", "Saint Ray", "Attack that drops a column of light on the target's head.", 80, Type.HOLY),
            new AttackTechnique("/digimonbattlesimulator/images/38_holy_breath_icon.png", "Holy Breath", "Increases the stamina and speed of allies.", 80, Type.HOLY),
            new AttackTechnique("/digimonbattlesimulator/images/39_flash_icon.png", "Flash", "Deals damage to enemies by exploding them from within with light.", 90, Type.HOLY),
            new AttackTechnique("/digimonbattlesimulator/images/40_saint_shield_icon.png", "Saint Shield", "Deals damage to area around target with a dome of light.",110, Type.HOLY),
            new AttackTechnique("/digimonbattlesimulator/images/41_judgement_icon.png", "Judgement", "Attacks enemies with an extra powerful laser of judgement.",150, Type.HOLY),
            new AttackTechnique("/digimonbattlesimulator/images/42_shining_nova_icon.png", "Shining Nova", "Fills the surrounding area with light energy, dealing much damage to enemies.",150, Type.HOLY)
        );
    }
}