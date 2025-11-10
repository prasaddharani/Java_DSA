package org.example.interview.singleton;

public class BillPughSingleton {

    BillPughSingleton() {}

    private static final class InstanceHolder {
        private static BillPughSingleton instance = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return InstanceHolder.instance;
    }
}
