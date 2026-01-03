package org.example.repractice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class EmployeeComparable implements Comparable<EmployeeComparable> {
    private String name;
    private int age;

    EmployeeComparable(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int compareTo(EmployeeComparable employeeComparable) {
        //return Integer.compare(this.age, employeeComparable.age);
        return employeeComparable.age - this.age;
    }

    @Override
    public String toString() {
        return "ComparableDemo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class ComparableDemo {
    public static void main(String[] args) {
        List<EmployeeComparable> employees = new ArrayList<>(List.of(
                new EmployeeComparable("Dharani", 27),
                new EmployeeComparable("Prasad", 26)

        ));
        Collections.sort(employees);
        System.out.println(employees);
    }
}
