package org.example.collectionFramework;

import jdk.jfr.Event;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class CollectionDemo {
    public static void main(String[] args) {
        // List to maintain items added to cart
        List<String> cartItems = new ArrayList<>();
        cartItems.add("IPhone");
        cartItems.add("Charger");
        cartItems.add("HeadPhones");
        log.info("CartItems in list {}", cartItems);

        // Set to avoid duplicate coupon codes
        Set<String> coupons = new HashSet<>();
        coupons.add("SUMMER10");
        coupons.add("SUPERHIT");
        coupons.add("CASH300");
        coupons.add("CASH300");
        log.info("Coupons in Set {}", coupons);

        // Queue for Order Processing (FIFO)
        Queue<String> orderQueue = new LinkedList<>();
        orderQueue.add("Order1");
        orderQueue.add("Order2");
        log.info("Orders in Queue {}", orderQueue);

        // Map to store product id -> name
        Map<Integer, String> productCatalog = new HashMap<>();
        productCatalog.put(1001, "IPhone");
        productCatalog.put(1002, "Charger");
        productCatalog.put(1003, "Headphones");
        log.info("Product Catalog in Map {}", productCatalog);

        String paragraph = "Hi Hello How are you? Hi I am Good";
        Map<String, Long> countingWords = Arrays.stream(paragraph.split(" "))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        log.info("Counting words {}", countingWords);

        NavigableMap<LocalDateTime, Event> events = new TreeMap<>();
        // Query between two times
        events.subMap(LocalDateTime.now(), true, LocalDateTime.now(), true);
        log.info("NavigableMap {}", events);

        enum Status { NEW, IN_PROGRESS, DONE }

        Map<Status, String> map = new EnumMap<>(Status.class);
        log.info(map.get(Status.NEW));

    }

    static class CollectionRemove {
        static class Employee {
            int id;
            String name;
            int salary;
            Employee(int id, String name, int salary) {
                this.id = id;
                this.name = name;
                this.salary = salary;
            }

            @Override
            public String toString() {
                return "Employee{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", salary=" + salary +
                        '}';
            }
        }
        public static void main(String[] args) {
            Employee employee1 = new Employee(1, "dharani", 10000);
            Employee employee2 = new Employee(2, "Prasad", 20000);
            List<Employee> employees = new ArrayList<>(List.of(employee1, employee2));
            //            employees.forEach(employee -> {
//                if (employee.salary <= 10000) {
//                    employees.remove(employee);
//                }
//            });
            employees.removeIf(employee -> employee.salary <= 10000);
            employees.forEach(System.out::println);
        }
    }
}
