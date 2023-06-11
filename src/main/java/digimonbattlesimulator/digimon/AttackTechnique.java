package digimonbattlesimulator.digimon;

public class AttackTechnique {
    private String icon;
    private String name;
    private String description;
    private int power;
    private Type type;

    public AttackTechnique(String icon, String name, String description, int power, Type type) {
        this.icon = icon;
        this.name = name;
        this.description = description;
        this.power = power;
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public String getDescription() { 
        return description; 
    }

    public String getPower() {
        return Integer.toString(power);
    }

    public Type getType() {
        return type;
    }
}
