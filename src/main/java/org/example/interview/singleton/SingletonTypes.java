package org.example.interview.singleton;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class SingletonTypes {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        EagerSingleton eager1 = EagerSingleton.getInstance();
        EagerSingleton eager2 = EagerSingleton.getInstance();

        log.info("eager1: {}, eager2: {}", eager1.hashCode(), eager2.hashCode());

        LazySingleton lazy1 = LazySingleton.getInstance();
        LazySingleton lazy2 = LazySingleton.getInstance();
        log.info("lazy1: {}, lazy2: {}", lazy1.hashCode(), lazy2.hashCode());

        Callable<ThreadSafeSingleton> task1 = ThreadSafeSingleton::getInstance;
        Callable<ThreadSafeSingleton> task2 = ThreadSafeSingleton::getInstance;

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        List<Future<ThreadSafeSingleton>> futures = fixedThreadPool.invokeAll(List.of(task1, task2));

        for(Future<ThreadSafeSingleton> future: futures) {
            log.info("ThreadSafeSingleton: {}", future.get().hashCode());
        }
        fixedThreadPool.shutdown();

        Callable<DoubleCheckedLockingSingleton> task3 = DoubleCheckedLockingSingleton::getInstance;
        Callable<DoubleCheckedLockingSingleton> task4 = DoubleCheckedLockingSingleton::getInstance;

        ExecutorService fixedThreadPoolForDoubleCheck= Executors.newFixedThreadPool(2);
        List<Future<DoubleCheckedLockingSingleton>> doubleCheckFeatures =
                fixedThreadPoolForDoubleCheck.invokeAll(List.of(task3, task4));

        for(Future<DoubleCheckedLockingSingleton> doubleCheckFeature: doubleCheckFeatures) {
            log.info("DoubleCheckedLockingSingleton: {}", doubleCheckFeature.get().hashCode());
        }
        fixedThreadPoolForDoubleCheck.shutdown();

        BillPughSingleton billPughSingleton1 = BillPughSingleton.getInstance();
        BillPughSingleton billPughSingleton2 = BillPughSingleton.getInstance();
        log.info("billPughSingleton1: {}, billPughSingleton2: {}",
                billPughSingleton1.hashCode(), billPughSingleton2.hashCode());

        EnumSingleton enumSingleton1 = EnumSingleton.INSTANCE;
        EnumSingleton enumSingleton2 = EnumSingleton.INSTANCE;

        log.info("enumSingleton1: {}, enumSingleton2: {}",
                enumSingleton1.hashCode(), enumSingleton2.hashCode());
    }
}
