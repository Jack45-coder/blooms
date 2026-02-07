package in.codingage.blooms.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class TestComponent {
    public TestComponent(){
        System.out.println("TestComponent instantiated");
        // Default constructor
    }

    @PostConstruct
    public void init(){
        System.out.println("TestComponent initialized");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("TestComponent about to be destroyed");
    }
}
