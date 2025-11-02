package org.example.java8.streams;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class StreamsPractice {

    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee(1, "Dharani", "IT", 50000, List.of("Java", "Python")),
                new Employee(3, "Prasad", "IT", 20000, List.of("Java", "Python")),
                new Employee(4, "John", "BPO", 30000, List.of("Communication")),
                new Employee(2, "Edge", "CEO", 100000, List.of("Business")));


        // sort list of employees by salary
        List<Employee> sortBySalaryAsc =
                employees.stream().sorted(Comparator.comparing(Employee::getSalary)).toList();

        List<Employee> sortBySalaryDesc =
                employees.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).toList();

        log.info("sort list of employees by salary asc: {}", sortBySalaryAsc);

        log.info("sort list of employees by salary desc: {}", sortBySalaryDesc);

        List<Person> personList = List.of(
                new Person(1, "Dharani", 26),
                new Person(2, "Prasad", 25));

        double averageAge = personList.stream()
                .mapToInt(Person::getAge)
                .average().orElse(0.0);
        log.info("Person average age: {}", averageAge);

        Map<Boolean, List<Person>> partitionByAge = personList.stream()
                .collect(Collectors.partitioningBy(person -> person.getAge() % 2 == 0));
        log.info("Person list partition by age: {}", partitionByAge);

        // group list of words by their length
        List<String> words = List.of("Dharani", "Prasad", "Cat", "Dog", "random", "Dharani");

        Map<Integer, List<String>> wordsByLength = words.stream()
                .collect(Collectors.groupingBy(String::length));
        log.info("group list of words by their length: {}", wordsByLength);

        // count the occurrence of each element

        Map<String, Long> wordCount = words.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        log.info("count the occurrence of each element: {}", wordCount);

        // group employee by each dept and calculate avg salary

        Map<String, Double> averageSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)));
        log.info("Average Salary by department: {}", averageSalaryByDept);

    }
}
