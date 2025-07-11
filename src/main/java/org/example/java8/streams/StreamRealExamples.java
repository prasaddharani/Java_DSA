package org.example.java8.streams;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class StreamRealExamples {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee(1, "dharani", "Dev", 1000, List.of("Python", "Java")),
                new Employee(2, "prasad", "Dev", 2000, List.of("Python", "Java")),
                new Employee(3, "Deva", "QA", 2000, List.of("Manual"))
                );

        Map<String, List<Employee>> employeesByDept =
                employees.stream()
                        .collect(Collectors.groupingBy(Employee::getDepartment));
        log.info("Group Employees By Department {}", employeesByDept);

        Map<String, Optional<Employee>> employeesByDeptWithHighPaid = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.maxBy(Comparator.comparing(Employee::getSalary))));
        log.info("Highest Paid Employee in Each Department {}", employeesByDeptWithHighPaid);

        List<String> uniqueSkills =
                employees.stream().flatMap(employee -> employee.getSkills().stream())
                        .distinct()
                        .toList();
        log.info("List of all unique skills {}", uniqueSkills);
        Map<String, Long> departmentCount = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        log.info("Count Employees by Department {}", departmentCount);

        List<Employee> employeesBySalaray = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .toList();
        log.info("Sort employees by Salary {}", employeesBySalaray);

        Optional<Employee> secondHighest = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .skip(1)
                .findFirst();
        log.info("Employee with Second Highest Salary {}", secondHighest);

        Map<Integer, String> employeeByID = employees.stream()
                .collect(Collectors.toMap(Employee::getId, Employee::getName));
        log.info("Employees By ID {}", employeeByID);

        List<String> javaDevelopers = employees.stream()
                .filter(employee -> employee.getSkills().contains("Java"))
                .map(Employee::getName)
                .toList();
        log.info("Java Dev {}", javaDevelopers);

        Map<Boolean, List<Employee>> employeeMap = employees.stream()
                .collect(Collectors.partitioningBy(employee -> employee.getSalary() > 50000));
        log.info("Partition Employee by salary {}", employeeMap);

        Map<String, Double> avgSalaryByDept =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.averagingDouble(Employee::getSalary)
                        ));
        log.info("Avg Salary per Dept {}", avgSalaryByDept);

        Stream<Employee> employeeStream = Stream.generate(() ->
                new Employee(new Random().nextInt(1000), "Emp", "IT", 50000, List.of("Java"))
        );

        employeeStream.limit(5).forEach(System.out::println);


    }
}
