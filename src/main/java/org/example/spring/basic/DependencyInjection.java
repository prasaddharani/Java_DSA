package org.example.spring.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

// Engine Bean
@Component
class Engine {
    public String start() {
        return "Engine started";
    }
}

// Car Bean with field injection
@Component
class FieldInjectionCar {
    @Autowired
    private Engine engine;

    public void drive() {
        System.out.println(engine.start() + " and car is moving through FieldInjection");
    }
}

// Audi Car with Setter injection
@Component
class SetterInjectionCar {
    private Engine engine;

    @Autowired
    private void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void drive() {
        System.out.println(engine.start() + " and car is moving through SetterInjection");
    }
}

@Component
class ConstructorInjectionCar {
    private final Engine engine;

    @Autowired  // Optional in Spring 4.3+ for single constructor
    public ConstructorInjectionCar(Engine engine) {
        this.engine = engine;
    }

    public void drive() {
        System.out.println(engine.start() + " and car is moving through ConstructorInjection");
    }
}


// Spring Configuration Class
@Configuration
@ComponentScan("org.example.spring.basic")
class AppConfig {
}

// Main Class to run the demo
public class DependencyInjection {
    public static void main(String[] args) {
        // Create Spring container
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Get Car bean
        FieldInjectionCar car = context.getBean(FieldInjectionCar.class);

        SetterInjectionCar setterInjectionCar = context.getBean(SetterInjectionCar.class);

        ConstructorInjectionCar constructorInjectionCar = context.getBean(ConstructorInjectionCar.class);

        // Run the method
        car.drive();
        setterInjectionCar.drive();
        constructorInjectionCar.drive();

        // Close context
        context.close();
    }
}
