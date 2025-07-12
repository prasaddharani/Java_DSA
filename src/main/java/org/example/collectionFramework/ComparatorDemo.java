package org.example.collectionFramework;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
class Employee {
    private Integer id;
    private String name;
    private Integer age;
}

@Slf4j
public class ComparatorDemo {
    public static void main(String[] args) {
        List<Employee> employeeList = new java.util.ArrayList<>(List.of(
                new Employee(1, "Dharani", 25),
                new Employee(2, "D.Prasad", 24)
        ));
        employeeList.sort(Comparator.comparing(Employee::getName).thenComparing(Employee::getAge));
        log.info("Sorted Employees {}", employeeList);

    }
}
