package in.codingage.blooms.controller;

import in.codingage.blooms.config.Calculator;
import in.codingage.blooms.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeanService {
    @Autowired
    private Calculator calculator;

    // constructor injection


}
