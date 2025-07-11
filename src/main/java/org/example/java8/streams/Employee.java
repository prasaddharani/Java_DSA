package org.example.java8.streams;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;
    private List<String> skills;
}
