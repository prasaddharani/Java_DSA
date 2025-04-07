package main.dsa;

import java.util.HashSet;
import java.util.Set;

public class CodeSnippets {

    public static void main(String[] args) {
        setCircularReferenceIssue();
    }

    private static void setCircularReferenceIssue() {
        Set<Object> set1 = new HashSet<>();
        Set<Object> set2 = new HashSet<>();

        set1.add(set2);
        set2.add(set1);

        // It throws StackOverflowError because of circular reference
        System.out.println(set1);
    }
}
