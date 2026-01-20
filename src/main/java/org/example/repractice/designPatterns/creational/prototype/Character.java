package org.example.repractice.designPatterns.creational.prototype;

public class Character implements Cloneable{
    private String name;
    private String health;
    private String attackPower;
    private String level;

    public Character(String name, String health, String attackPower, String level) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public String getHealth() {
        return health;
    }

    public String getAttackPower() {
        return attackPower;
    }

    public String getLevel() {
        return level;
    }

    @Override
    public Character clone() throws CloneNotSupportedException {
        return (Character) super.clone();
    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", health='" + health + '\'' +
                ", attackPower='" + attackPower + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
