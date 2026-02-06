package in.codingage.blooms.config;


import in.codingage.blooms.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class Calculator {

    @Autowired
    private CalculatorService calculatorService;

    Calculator(CalculatorService calculatorService){
        this.calculatorService = calculatorService;
    }

    public int add(int a, int b){
        return a+b;
    }
}
