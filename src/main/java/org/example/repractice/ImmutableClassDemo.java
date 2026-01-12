package org.example.repractice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Student {
    private final int id;
    private final String name;
    private final List<String> skills;

    public Student(int id, String name, List<String> skills) {
        this.id = id;
        this.name = name;
        this.skills = new ArrayList<>(skills);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getSkills() {
        return new ArrayList<>(skills);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", skills=" + skills +
                '}';
    }
}
public class ImmutableClassDemo {

    public static void main(String[] args) {
        List<String> skills = new ArrayList<>(List.of("Java", "Python"));

        Student student = new Student(1, "Dharani", skills);
        skills.add("AWS");
        student.getSkills().add("GenAI");
        System.out.println(student);
    }
}
