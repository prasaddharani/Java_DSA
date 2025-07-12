package org.example.collectionFramework;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

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
    }
}
