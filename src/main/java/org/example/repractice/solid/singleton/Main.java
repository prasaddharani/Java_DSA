package org.example.repractice.solid.singleton;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        EagerSingleton eager1 = EagerSingleton.getInstance();
        EagerSingleton eager2 = EagerSingleton.getInstance();
        log.info("Eager singleton eager1: {}", eager1.hashCode());
        log.info("Eager singleton eager2: {}", eager2.hashCode());

        LazySingleton lazy1 = LazySingleton.getInstance();
        LazySingleton lazy2 = LazySingleton.getInstance();
        log.info("LazySingleton lazy1: {}", lazy1.hashCode());
        log.info("LazySingleton lazy2: {}", lazy2.hashCode());


        Callable<SingleLockSingleton> task1 = SingleLockSingleton::getInstance;
        Callable<SingleLockSingleton> task2 = SingleLockSingleton::getInstance;

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        List<Future<SingleLockSingleton>> futures = fixedThreadPool.invokeAll(List.of(task1, task2));

        for(Future<SingleLockSingleton> future: futures) {
            log.info("SingleLockSingleton: {}", future.get().hashCode());
        }
        fixedThreadPool.shutdown();

        Callable<DoubleLockSingleton> task3 = DoubleLockSingleton::getInstance;
        Callable<DoubleLockSingleton> task4 = DoubleLockSingleton::getInstance;

        ExecutorService fixedThreadPool2 = Executors.newFixedThreadPool(2);
        List<Future<DoubleLockSingleton>> futures2 = fixedThreadPool2.invokeAll(List.of(task3, task4));

        for(Future<DoubleLockSingleton> future: futures2) {
            log.info("DoubleLockSingleton: {}", future.get().hashCode());
        }
        fixedThreadPool2.shutdown();

        BillPughSingleton billPughSingleton1 = BillPughSingleton.getInstance();
        BillPughSingleton billPughSingleton2 = BillPughSingleton.getInstance();
        log.info("BillPughSingleton1: {}", billPughSingleton1.hashCode());
        log.info("BillPughSingleton2: {}", billPughSingleton2.hashCode());

        EnumSingleton enumSingleton1 = EnumSingleton.INSTANCE;
        EnumSingleton enumSingleton2 = EnumSingleton.INSTANCE;

        log.info("EnumSingleton1: {}", enumSingleton1.hashCode());
        log.info("EnumSingleton2: {}", enumSingleton2.hashCode());
    }
}
