package org.example.repractice.designPatterns.creational.builder;

public class Account {
    private String id;
    private String name;
    private double balance;
    private Address address;

    private Account(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.balance = builder.balance;
        this.address = builder.address;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance + '\'' +
                ", address=" + address +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String name;
        private double balance;
        private Address address;
        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder balance(double balance) {
            this.balance = balance;
            return this;
        }

        public Builder address(Address address) {
            this.address = address;
            return this;
        }

        public Account build() {
            return new Account(this);
        }
    }
}
