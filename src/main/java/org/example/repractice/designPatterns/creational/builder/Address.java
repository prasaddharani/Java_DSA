package org.example.repractice.designPatterns.creational.builder;

public class Address {
    private String city;
    private String line1;
    private String line2;
    private String zipCode;

    private Address(Builder builder) {
        this.city = builder.city;
        this.line1 = builder.line1;
        this.line2 = builder.line2;
        this.zipCode = builder.zipCode;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", line1='" + line1 + '\'' +
                ", line2='" + line2 + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }

    public static class Builder {
        private String city;
        private String line1;
        private String line2;
        private String zipCode;

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder line1(String line1) {
            this.line1 = line1;
            return this;
        }

        public Builder line2(String line2) {
            this.line2 = line2;
            return this;
        }

        public Builder zipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }
}
