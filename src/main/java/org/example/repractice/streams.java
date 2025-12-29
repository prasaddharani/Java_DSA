package org.example.repractice;

import lombok.extern.slf4j.Slf4j;
import org.example.java8.streams.Employee;
import org.example.java8.streams.Person;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class streams {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee(1, "Dharani", "IT", 50000, List.of("Java", "Python")),
                new Employee(3, "Prasad", "IT", 20000, List.of("Java", "Python")),
                new Employee(4, "John", "BPO", 30000, List.of("Communication")),
                new Employee(2, "Edge", "CEO", 100000, List.of("Business")),
                new Employee(2, "Prasanth", "CEO", 200000, List.of("Business")));


        // sort list of employees by salary
        List<Employee> empBySal = employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed()).toList();
        System.out.println("sort list of employees by salary"+ empBySal);

        // log.info("Person average age: {}", averageAge);
        List<Person> personList = List.of(
                new Person(1, "Dharani", 26),
                new Person(2, "Prasad", 25));
        double averageAge = personList.stream()
                .mapToInt(Person::getAge)
                .average().orElse(0);
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
        Map<String, Long> wordCount= words.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        log.info("count the occurrence of each element: {}", wordCount);

        // group employee by each dept and calculate avg salary
        Map<String, Double> averageSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)));
        log.info("Average Salary by department: {}", averageSalaryByDept);

        // Find highest paid employee by each department
        Map<String, Optional<Employee>> highestPaidEmpByDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.maxBy(Comparator.comparing(Employee::getSalary))));
        log.info("highest paid employee by each department {}", highestPaidEmpByDept);

        // Find all departments with more than 2 employees
        List<String> moreThan2EmployeesDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().size() >= 2)
                .map(Map.Entry::getKey)
                .toList();
        log.info("More than 2 Employess Dept {}", moreThan2EmployeesDept);

        List<Employee> moreThan2Employees = employees.stream()
                        .filter(employee -> moreThan2EmployeesDept.contains(employee.getDepartment()))
                        .toList();
        log.info("all departments with more than 2 employees: {}", moreThan2Employees);

        // find department with the highest average salary
        Optional<String> highestAverageSalDepartment = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
        log.info("department with the highest average salary: {}", highestAverageSalDepartment);

        // find more frequent character in a string
        Map<Character, Long> mostFrequentCharacter= "Dharani".chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        log.info("more frequent character in a string: {}", mostFrequentCharacter);

        // Find first non-repeating character
        Optional<Character> nonRepeatingChar = "Dharani".chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst();
        log.info("first non-repeating character: {}", nonRepeatingChar);



    }
}
