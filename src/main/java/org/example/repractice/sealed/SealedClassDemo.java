package org.example.repractice.sealed;

sealed class Shape permits Circle, Rectangle, Square {
    public void printShape() {
        System.out.println("Shape main class");
    }
}

final class Rectangle extends Shape {

    @Override
    public void printShape() {
        System.out.println("Rectangle shape");
    }
}

final class Circle extends Shape {
    @Override
    public void printShape() {
        System.out.println("Rectangle shape");
    }
}

non-sealed class Square extends Shape {
    @Override
    public void printShape() {
        System.out.println("Square shape");
    }
}

public class SealedClassDemo {

    public static void main(String[] args) {
        Shape rectangle = new Rectangle();
        Shape square = new Square();
        rectangle.printShape();
        square.printShape();
    }
}