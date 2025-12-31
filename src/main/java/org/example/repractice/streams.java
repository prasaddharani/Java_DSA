package org.example.repractice;

import lombok.extern.slf4j.Slf4j;
import org.example.java8.streams.*;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

        // Find most common first letter among all employees
        List<Employee> employeeNameWithMostCommonChar = employees.stream()
                .collect(Collectors.groupingBy(employee -> employee.getName().charAt(0)))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue).get();
        log.info("most common first letter among all employees: {}", employeeNameWithMostCommonChar);

        // Find average 3 consecutive elements sliding window
        List<Integer> input = Arrays.asList(4, 8, 15, 16, 23, 42);
        int window = 3;
        List<Double> averageOfThreeConsecutive = IntStream.range(0, input.size() - (window - 1))
                        .mapToObj(i -> input.subList(i, i + window))
                        .toList()
                        .stream()
                        .map(subList ->
                                subList.stream().mapToInt(i -> i).average().orElse(0))
                        .toList();
        log.info("average 3 consecutive elements sliding window: {}", averageOfThreeConsecutive);


        // Finding the longest word in a sentence
        String sentence = "average , 3 consecutive ' elements sliding window";
        String longestWordFromSentence = Arrays.stream(
                sentence.replaceAll("[^a-z\\s]", "")
                        .split(" "))
                .max(Comparator.comparing(String::length)).orElse("");
        log.info("longest word in a sentence: {}", longestWordFromSentence);

        String paragraph = "Java is object oriented programming language. Java is platform independent";

        // Find top 3 most words in paragraph
        List<String> maxWords = Arrays.stream(paragraph.split(" "))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .toList();
        log.info("top 3 most words in paragraph: {}", maxWords);

        // Reverse words in string
        String reversedString = Arrays.stream(paragraph.split(" "))
                .map(word -> Arrays.stream(word.split(""))
                        .reduce("", (ch, rev) -> rev + ch)).collect(Collectors.joining(" "));
        log.info("Reversed String: {}", reversedString);

        // Find the day with highest spend from the list of transactions
        List<Transaction> transactions = List.of(
                new Transaction("T1", LocalDate.of(2025, 11, 1), 1000),
                new Transaction("T2", LocalDate.of(2025, 11, 2), 3000),
                new Transaction("T3", LocalDate.of(2025, 11, 1), 1000),
                new Transaction("T4", LocalDate.of(2025, 11, 3), 1000),
                new Transaction("T5", LocalDate.of(2025, 11, 2), 2000)
        );

        LocalDate maxSpent = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getDate, Collectors.summingDouble(Transaction::getAmount)))
                .entrySet()
                .stream()
                .max(Comparator.comparingDouble(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(null);
        log.info("Max spent on: {}", maxSpent);

        List<Employee> employeesBySalary = List.of(
                new Employee(1, "Dharani", "IT", 50000, List.of("Java", "Python")),
                new Employee(3, "Prasad", "IT", 20000, List.of("Java", "Python")),
                new Employee(4, "John", "BPO", 30000, List.of("Communication")),
                new Employee(2, "Edge", "CEO", 100000, List.of("Business")),
                new Employee(2, "Prasanth", "CEO", 200000, List.of("Business")));

        Map<SalaryRangeEnum, List<Employee>> employeesBySalRange = employeesBySalary.stream()
                .collect(Collectors.groupingBy(employee -> groupBySalaryRange(employee.getSalary())));
        log.info("Employees by salary range: {}", employeesBySalRange);

        // Group character based on upper case, lower case, special, digit
        List<Character> characters =
                List.of('A', 'b', '3', 'Z', 'x', '#', '7', 'm', '@');

        Map<CharacterTypeEnum, List<Character>> characterMap = characters.stream()
                .collect(Collectors.groupingBy(streams::groupByCharacterType));
        log.info("Group by character type: {}", characterMap);

        // Find All Employees who worked in 3+ departments
        Map<String, List<Employee>> talentedEmployee = employees.stream()
                .collect(Collectors.groupingBy(Employee::getName))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().size() >= 3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        log.info("Employees who works in more than 3 departments: {}", talentedEmployee);


    }



    private static CharacterTypeEnum groupByCharacterType(Character c) {
        if (Character.isDigit(c)) {
            return CharacterTypeEnum.DIGIT;
        } else if (Character.isLowerCase(c)) {
            return CharacterTypeEnum.LOWER_CASE;
        } else if (Character.isUpperCase(c)) {
            return CharacterTypeEnum.UPPER_CASE;
        } else {
            return CharacterTypeEnum.OTHER;
        }
    }

    private static SalaryRangeEnum groupBySalaryRange(Double salary) {
        if (salary < 10000) {
            return SalaryRangeEnum.LOW;
        } else if (salary <= 50000) {
            return SalaryRangeEnum.MEDIUM;
        } else {
            return SalaryRangeEnum.HIGH;
        }
    }
}
