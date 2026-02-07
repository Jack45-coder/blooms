package in.codingage.blooms.config;

import in.codingage.blooms.service.CalculatorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration indicates that the class can be used by the Spring IOC controller as a source of bean definitions.
@Configuration
public class AppConfig {

    @Bean
    public CalculatorService calculatorService(){
        return new CalculatorService();
    }

    @Bean
    public Calculator calculator(CalculatorService calculatorService){
        return new Calculator(calculatorService);
    }

    // android dependency injection
    // Dagger [http client retrofit [okhttp client] Gson converter, logging interceptor]


}
