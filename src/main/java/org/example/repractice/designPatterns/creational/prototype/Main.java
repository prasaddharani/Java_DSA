package org.example.repractice.designPatterns.creational.prototype;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        CharacterFactory characterFactory = new CharacterFactory("Dharani", "80", "90", "2");
        System.out.println(characterFactory);
        Character clonedCharacter = characterFactory.createCharacterWithNewName("Prasad");
        System.out.println(clonedCharacter);
        System.out.println(characterFactory);

    }
}
