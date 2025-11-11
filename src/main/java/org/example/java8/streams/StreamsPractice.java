package org.example.java8.streams;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
public class StreamsPractice {

    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee(1, "Dharani", "IT", 50000, List.of("Java", "Python")),
                new Employee(3, "Prasad", "IT", 20000, List.of("Java", "Python")),
                new Employee(4, "John", "BPO", 30000, List.of("Communication")),
                new Employee(2, "Edge", "CEO", 100000, List.of("Business")),
                new Employee(2, "Prasanth", "CEO", 200000, List.of("Business")));


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

        // Find highest paid employee by each department
        Map<String, Optional<Employee>> highestPaidEmpByDept= employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.maxBy(Comparator.comparing(Employee::getSalary))));
        log.info("highest paid employee by each department {}", highestPaidEmpByDept);

        // Find all departments with more than 2 employees
        Map<String, Long> employeesWithDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.counting()));
        log.info("employee count with department wise {}", employeesWithDept);
        List<String> moreThan2EmployeesDept = employeesWithDept.entrySet().stream()
                                .filter(department -> department.getValue() >= 2)
                                .map(Map.Entry::getKey)
                                .toList();
        log.info("More than 2 Employess Dept {}", moreThan2EmployeesDept);
        List<Employee> moreThan2Employees = employees.stream()
                        .filter(employee -> moreThan2EmployeesDept.contains(employee.getDepartment()))
                        .toList();
        log.info("all departments with more than 2 employees: {}", moreThan2Employees);

        // find department with the highest average salary
        Optional<String> highestAverageSalDepartment = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)))
                .entrySet()
                .stream().max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
        log.info("department with the highest average salary: {}", highestAverageSalDepartment);

        // find more frequent character in a string
        Optional<Character> mostFrequentCharacter = "Dharani".chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream().max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
        log.info("more frequent character in a string: {}", mostFrequentCharacter);

        // Find first non-repeating character
        Optional<Character> nonRepeatingChar = "Dharani".chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream().filter(characterLongEntry -> characterLongEntry.getValue() == 1)
                .map(Map.Entry::getKey).findFirst();
        log.info("first non-repeating character: {}", nonRepeatingChar);

        // Find most common first letter among all employees
        Optional<List<Employee>> employeesWithCommonFirstChar = employees.stream()
                .collect(Collectors.groupingBy(employee -> employee.getName().charAt(0)))
                .entrySet()
                .stream()
                .max(Comparator.comparing(entry -> entry.getValue().size()))
                .map(Map.Entry::getValue);
        Optional<Map.Entry<Character, Long>> employeeNameWithMostCommonChar = employees.stream()
                        .map(employee -> employee.getName().charAt(0))
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue());
        log.info("most common first letter among all employees: {}", employeesWithCommonFirstChar);
        log.info("most common first letter among all employees: {}", employeeNameWithMostCommonChar);

        // Find average 3 consecutive elements sliding window
        List<Integer> input = Arrays.asList(4, 8, 15, 16, 23, 42);

        int window = 3;
        List<Double> averageOfThreeConsecutive = IntStream.range(0, input.size() - (window - 1))
                .mapToObj(i -> input.subList(i, i + window))
                .toList()
                .stream()
                .map( subList ->
                        subList.stream()
                                .mapToInt(i -> i)
                                .average()
                                .orElse(0.0))
                .toList();
        log.info("average 3 consecutive elements sliding window: {}", averageOfThreeConsecutive);

        // Finding the longest word in a sentence
        String sentence = "average , 3 consecutive ' elements sliding window";
        Optional<String> longestWordFromSentence = Arrays.stream(sentence.toLowerCase()
                        .replaceAll("[^a-z\\s]", "")
                        .split(" "))
                        .max(Comparator.comparing(String::length));

        log.info("longest word in a sentence: {}", longestWordFromSentence);

        // Find top 3 most words in paragraph
        String paragraph = "Java is object oriented programming language. Java is platform independent";
        List<String> maxWords = Arrays.stream(paragraph.split(" "))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .toList();
        log.info("top 3 most words in paragraph: {}", maxWords);

        // Reverse words in string
        String reversedString = Arrays.stream(paragraph.split(" "))
                .map(word -> Arrays.stream(word.split(""))
                        .reduce("", (ch, rev) -> rev + ch))
                .collect(Collectors.joining(" "));
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
                .collect(Collectors.groupingBy(Transaction::getDate,
                        Collectors.summingDouble(Transaction::getAmount)))
                .entrySet()
                .stream()
                .max(Comparator.comparingDouble(Map.Entry::getValue))
                .map(Map.Entry::getKey).orElse(null);
        log.info("Max spent on: {}", maxSpent);


        List<Employee> employeesBySalary = List.of(
                new Employee(1, "Dharani", "IT", 50000, List.of("Java", "Python")),
                new Employee(3, "Prasad", "IT", 20000, List.of("Java", "Python")),
                new Employee(4, "John", "BPO", 30000, List.of("Communication")),
                new Employee(2, "Edge", "CEO", 100000, List.of("Business")),
                new Employee(2, "Prasanth", "CEO", 200000, List.of("Business")));

        Map<SalaryRangeEnum, List<Employee>> employeesBySalRange = employeesBySalary.stream()
                .collect(Collectors.groupingBy(employee -> getSalaryBand(employee.getSalary())));
        log.info("Employees by salary range: {}", employeesBySalRange);

        // Group character based on upper case, lower case, special, digit
        List<Character> characters =
                List.of('A', 'b', '3', 'Z', 'x', '#', '7', 'm', '@');

        Map<CharacterTypeEnum, List<Character>> characterMap = characters.stream()
                .collect(Collectors.groupingBy(StreamsPractice::getCharacterType));
        log.info("Group by character type: {}", characterMap);

        // Find All Employees who worked in 3+ departments
        Map<String, List<Employee>> talentedEmployee = employees.stream()
                .collect(Collectors.groupingBy(Employee::getName))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().size() >= 3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        log.info("Employees who works in more than 3 departments: {}", talentedEmployee);

        // Find Bi-gram frequency
        String paragraph2 = "Java is great and Java is fun. Java is powerful";
        String[] wordsSplit = paragraph2.split(" ");
        List<String> bigram = new ArrayList<>();
        Map<String, Long> bigramFrequency = IntStream.range(0, wordsSplit.length - 1)
                .mapToObj(i -> wordsSplit[i] + " " + wordsSplit[i + 1])
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        log.info("Bigram frequency: {}", bigramFrequency);

        // can update main object
        //employees.forEach(employee -> employee.setSalary(10000));
        log.info("Updated Employees: {}", employees);

        Integer reducedValue = IntStream.range(0, 10)
                .reduce(0, Integer::sum);
        log.info("Reduced Sum value: {}", reducedValue);

        List<String> allSkills = employees.stream()
                .flatMap(employee -> employee.getSkills().stream())
                .distinct()
                .toList();
        log.info("Employees all skills: {}", allSkills);
    }

    private static CharacterTypeEnum getCharacterType(Character c) {
        if (Character.isDigit(c)) {
            return CharacterTypeEnum.DIGIT;
        } else if (Character.isUpperCase(c)) {
            return CharacterTypeEnum.UPPER_CASE;
        } else if (Character.isLowerCase(c)) {
            return CharacterTypeEnum.LOWER_CASE;
        } else {
            return CharacterTypeEnum.OTHER;
        }
    }

    private static SalaryRangeEnum getSalaryBand(Double salary) {
        if (salary <= 10000) {
            return SalaryRangeEnum.LOW;
        } else if (salary > 10000 && salary < 50000 ) {
            return SalaryRangeEnum.MEDIUM;
        } else {
            return SalaryRangeEnum.HIGH;
        }
    }
}
