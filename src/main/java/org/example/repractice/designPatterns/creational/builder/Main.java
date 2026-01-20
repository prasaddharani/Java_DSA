package org.example.repractice.designPatterns.creational.builder;

public class Main {
    public static void main(String[] args) {
        Address address = Address.builder().city("TPT").line1("line1").line2("line2").zipCode("111111").build();
        Account account = Account.builder().id("1").name("Dharani").balance(10L).address(address).build();

        System.out.println(account);
    }
}
