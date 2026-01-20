package org.example.repractice.designPatterns.creational.prototype;

public class CharacterFactory {

    private Character protoType;

    public CharacterFactory(String name, String health, String attackPower, String level) {
        this.protoType = new Character(name, health, attackPower, level);
    }

    public Character createCharacterWithNewName(String name) throws CloneNotSupportedException {
        Character clonedCharacter = protoType.clone();
        clonedCharacter = new Character(name, clonedCharacter.getHealth(),
                clonedCharacter.getAttackPower(), clonedCharacter.getLevel());
        return clonedCharacter;
    }

    @Override
    public String toString() {
        return "CharacterFactory{" +
                "protoType=" + protoType +
                '}';
    }
}
