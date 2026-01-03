package org.example.repractice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class EmployeeComparator {
    private String name;
    private int age;

    EmployeeComparator(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "ComparableDemo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class ComparatorDemo {
    public static void main(String[] args) {
        List<EmployeeComparator> employees = new ArrayList<>(List.of(
                new EmployeeComparator("Dharani", 27),
                new EmployeeComparator("Prasanth", 26),
                new EmployeeComparator("Prasad", 26)

        ));
        employees.sort(Comparator.comparingInt(EmployeeComparator::getAge).reversed()
                .thenComparing(EmployeeComparator::getName));
        System.out.println(employees);
    }
}
