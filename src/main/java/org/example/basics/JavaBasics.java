package org.example.basics;

public class JavaBasics {
    public static void main(String[] args) {
        //conditionalStatements();
        primitiveDataTypes();
    }

    private static void primitiveDataTypes() {
        // Primitive data types
        byte b = 127;
        short s = 32767;
        int i = 2147483647;
        long l = 9223372036854775807L;
        float f = 3.4f;
        double d = 3.4;
        char c = 'a';
        boolean bool = true;

        System.out.println("byte: " + b);
        System.out.println("short: " + s);
        System.out.println("int: " + i);
        System.out.println("long: " + l);
        System.out.println("float: " + f);
        System.out.println("double: " + d);
        System.out.println("char: " + c);
        System.out.println("boolean: " + bool);
    }

    private static void conditionalStatements() {
        int num = 1;
        switch(num) {
            case 3:
                System.out.println("3rd case");
                break;
            case 2:
                System.out.println("2nd case");
                break;
            case 1:
                System.out.println("1st case");
                break;
            case 4:
                System.out.println("4th case");
                break;
        }
    }
}
