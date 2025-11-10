package org.example.interview.singleton;

import lombok.Getter;

public class EagerSingleton {
    @Getter
    private static EagerSingleton instance = new EagerSingleton();

    EagerSingleton() {}

}
