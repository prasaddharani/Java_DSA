package org.example.spring.dependencyInjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
class Engine {
    String engine () {
        return "Engine started";
    }
}

@Component
class FieldInjectionCar {

    @Autowired
    private Engine engine;

    public void drive() {
        System.out.println(engine.engine()+ " and car is running via FieldInjection");
    }
}

@Component
class ConstructionInjectionCar {
    private final Engine engine;

    @Autowired
    ConstructionInjectionCar (Engine engine) {
        this.engine = engine;
    }

    public void drive() {
        System.out.println(engine.engine() + " and car is running via ConstructorInjection");
    }
}

@Component
class SetterInjectionCar {
    private Engine engine;

    @Autowired
    void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void drive() {
        System.out.println(engine.engine() + " and car is running via SetterInjection");
    }
}

@Configuration
@ComponentScan("org.example.spring.dependencyInjection")
class ApplicationConfig {

}
public class DependencyInjection {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        FieldInjectionCar fieldInjectionCar = applicationContext.getBean(FieldInjectionCar.class);
        ConstructionInjectionCar constructionInjectionCar = applicationContext.getBean(ConstructionInjectionCar.class);
        SetterInjectionCar setterInjectionCar = applicationContext.getBean(SetterInjectionCar.class);

        fieldInjectionCar.drive();
        constructionInjectionCar.drive();
        setterInjectionCar.drive();
    }

}
